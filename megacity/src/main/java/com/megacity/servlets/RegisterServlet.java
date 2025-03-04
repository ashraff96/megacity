package com.megacity.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.model.User;
import com.megacity.service.UserService;
import com.megacity.utils.PasswordHasher;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name").trim();
        String address = request.getParameter("address").trim();
        String nic = request.getParameter("nic").trim();
        String telephone = request.getParameter("telephone").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");
        
    	String hashedPassword = PasswordHasher.hashPassword(password);

    	UserService userService = new UserService();
    	if(userService.isUsernameTaken(username)) {
    		request.setAttribute("messages", List.of("Username already taken!"));
        	request.setAttribute("messageType", "error");
        	
        	request.setAttribute("name", name);
        	request.setAttribute("address", address);
        	request.setAttribute("nic", nic);
        	request.setAttribute("telephone", telephone);
        	
        	request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        	return;
    	}
    	
        User user = new User(name, address, nic, telephone, username, hashedPassword);
        
        boolean registrationSuccessful = userService.createUser(user);

        if (registrationSuccessful) {
        	User userWithID = userService.getUserDetails(username);
        	request.getSession().setAttribute("user", userWithID);
        	
            response.sendRedirect(request.getContextPath() + "/");
            return;
        } else {
        	request.setAttribute("name", name);
        	request.setAttribute("username", username);
        	request.setAttribute("address", address);
        	request.setAttribute("nic", nic);
        	request.setAttribute("telephone", telephone);
        	
        	request.setAttribute("messages", "Registration Failed! Try Again");
        	request.setAttribute("messageType", "error");
        	
        	request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    	
	}
}
