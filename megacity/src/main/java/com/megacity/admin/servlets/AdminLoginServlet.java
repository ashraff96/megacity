package com.megacity.admin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.admin.service.AdminService;
import com.megacity.model.Admin;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
	    String password = request.getParameter("password");

	    AdminService adminService = new AdminService();
	    
	    if(adminService.authenticateAdmin(username, password)) {
	    	Admin admin = adminService.getAdminDetails(username);
	        request.getSession().setAttribute("admin", admin);

	        response.sendRedirect(request.getContextPath() + "/admin");
	    } else {
	        request.setAttribute("username", username);
	        request.setAttribute("messages", List.of("Invalid Credentials!"));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	    }
	}
}

