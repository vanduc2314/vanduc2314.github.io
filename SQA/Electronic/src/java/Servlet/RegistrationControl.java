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
import model.Customer;
import model.User;

/**
 *
 * @author pvand
 */
public class RegistrationControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/general/registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO ud = new userDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpass = request.getParameter("cpass");
        RequestDispatcher dispatcher;
        if (password.equals(cpass)) {
            if (ud.check_duplicate(tel)) {
                User c = new User(name, tel, address, password, email, username);
                if (ud.add_client(c)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("position", "Silver");
                    session.setAttribute("name", c.getName());
                    response.sendRedirect("Home");
                } else {
                    //báo lỗi thêm khách hàng không thành công
                    if (request.getParameter("message") != null) {
                        request.setAttribute("message", "Sorry, the server can not save your registration right now");
                    }
                    dispatcher = this.getServletContext().getRequestDispatcher("/general/registration.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                //báo lỗi đã tồn tại người dùng với số điện thoại đó, thử lại
                request.setAttribute("message", "The phone number already existed");
                dispatcher = this.getServletContext().getRequestDispatcher("/general/registration.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            //báo lỗi mật khẩu không trùng khớp
            request.setAttribute("message", "Password not match");
            dispatcher = this.getServletContext().getRequestDispatcher("/general/registration.jsp");
            dispatcher.forward(request, response);
        }

    }
}
