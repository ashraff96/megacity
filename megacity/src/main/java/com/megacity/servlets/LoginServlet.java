package com.megacity.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.model.User;
import com.megacity.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
	    String password = request.getParameter("password");

	    UserService userService = new UserService();
	    
	    if(userService.authenticateUser(username, password)) {
	    	User user = userService.getUserDetails(username);
	        request.getSession().setAttribute("user", user);

	        response.sendRedirect(request.getContextPath() + "/");
	    } else {
	        request.setAttribute("username", username);
	        request.setAttribute("messages", List.of("Invalid Credentials!"));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	    }
	}

}
