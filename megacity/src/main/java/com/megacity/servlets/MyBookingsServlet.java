package com.megacity.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacity.model.Booking;
import com.megacity.model.User;
import com.megacity.service.BookingService;

/**
 * Servlet implementation class MyBookingsServlet
 */
@WebServlet("/my-bookings")
public class MyBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookingService bookingService = new BookingService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBookingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        
        if (session != null) {
            User user = (User) session.getAttribute("user");
            
            if (user != null) {
                int userId = user.getId();
                List<Booking> booking = bookingService.getBookingsByUserId(userId);
                
                request.setAttribute("bookings", booking);
                request.getRequestDispatcher("/WEB-INF/views/my-bookings.jsp").forward(request, response);
                return;
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/login");
	}
}
