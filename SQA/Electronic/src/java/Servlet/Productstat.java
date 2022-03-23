/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.billDAO;
import dao.statDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Bill;
import model.Stat;

/**
 *
 * @author pvand
 */
public class Productstat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/admin/productstatistic.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("sdate") != null && request.getParameter("edate") != null) {
            String sdate = request.getParameter("sdate");
            String edate = request.getParameter("edate");
            statDAO sd = new statDAO();
            ArrayList<Stat> listp = sd.getstat(sdate, edate);
            PrintWriter out = response.getWriter();
            for (Stat s : listp) {
                out.println(
                        "                                <tr>\n"
                        + "                                    <th scope=\"row\"> <a class=\"link-page\" style=\"color: #7D5A50 !important;\" href=\"DetailStat?edate=" + edate + "&sdate=" + sdate + "&name=" + s.getName() + "&pid=" + s.getId() + "\">" + s.getSku() + "</a></th>\n"
                        + "                                    <td>" + s.getName() + "</td>\n"
                        + "                                    <td>" + s.getTotal_quantity() + "</td>\n"
                        + "                                    <td>$" + s.getRevenue() + "</td>\n"
                );
            }
            out.print("            <script>\n"
                    + "                google.charts.load('current', {packages: ['corechart', 'bar']});\n"
                    + "                google.charts.setOnLoadCallback(drawBasic);\n"
                    + "\n"
                    + "                function drawBasic() {\n"
                    + "\n"
                    + "                    var data = new google.visualization.arrayToDataTable([\n"
                    + "                        ['Product', '$', {role: 'style'}],\n");
            for (Stat s : listp) {
                out.print("['" + s.getName() + "'," + s.getRevenue() + ", 'color: #E86137'],\n");
            }
            out.print(
                    "]);"
                    + "                    var options = {\n"
                    + "\n"
                    + "                        hAxis: {\n"
                    + "                            title: \"Product's name\",\n"
                    + "                        },\n"
                    + "                        vAxis: {\n"
                    + "                            title: 'Revenue'\n"
                    + "                        },\n"
                    + "                        backgroundColor: '#E5B299',\n"
                    + "                        fontSize: '18',\n"
                    + "                        fontName: 'Poppins',\n"
                    + "                        legend: 'none'\n"
                    + "\n"
                    + "                    };\n"
                    + "\n"
                    + "                    var chart = new google.visualization.ColumnChart(\n"
                    + "                            document.getElementById('chart_div'));\n"
                    + "\n"
                    + "                    chart.draw(data, options);\n"
                    + "                }\n"
                    + "            </script>"
            );
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
