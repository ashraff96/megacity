package com.megacity.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class BookingServlet
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
		String pickupLocation = request.getParameter("pickup_location").trim();
        String destination = request.getParameter("destination").trim();
        String pickupDate = request.getParameter("pickup_date").trim(); 
        String pickupTime = request.getParameter("pickup_time").trim(); 
        String seats = request.getParameter("seats").trim();  
        String message = request.getParameter("message").trim(); 
        
        Timestamp pickupDateTime = Timestamp.valueOf(LocalDateTime.parse(pickupDate + " " + pickupTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        
    	User loggedInUser = (User) session.getAttribute("user");
        int userId = loggedInUser.getId();
    	Booking booking = new Booking();
        booking.setCustomerId(userId);
        booking.setPickupLocation(pickupLocation);
        booking.setDestination(destination);
        booking.setPickupDateTime(pickupDateTime);
        booking.setNoOfSeats(seats);
        booking.setMessage(message);

        BookingService bookingService = new BookingService();
        boolean bookingSuccess = bookingService.createBooking(booking);

        if (bookingSuccess) {
        	response.sendRedirect(request.getContextPath() + "/my-bookings");
        	return;
        } 
        
    	request.setAttribute("pickup_location", pickupLocation);
    	request.setAttribute("destination", destination);
    	request.setAttribute("pickup_date", pickupDate);
    	request.setAttribute("pickup_time", pickupTime);
    	request.setAttribute("seats", seats);
    	request.setAttribute("message", message);
    	
    	request.setAttribute("messages", "Booking Failed! Try Again");
    	request.setAttribute("messageType", "error");
    	
    	request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
	}

}
