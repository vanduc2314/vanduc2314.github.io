/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Staff;

/**
 *
 * @author pvand
 */
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/general/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO ud = new userDAO();
        RequestDispatcher dispatcher;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Staff s = ud.check_user(username, password);
        System.out.println();
        if (s.getName() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("position", s.getPosition());
            session.setAttribute("name", s.getName());
            response.sendRedirect("AdminHome");
        } else {
            request.setAttribute("message", "Wrong");
            dispatcher = this.getServletContext().getRequestDispatcher("/general/login.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
