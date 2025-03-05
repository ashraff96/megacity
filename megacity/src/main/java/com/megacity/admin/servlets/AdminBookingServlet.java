package com.megacity.admin.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.admin.service.DriverService;
import com.megacity.admin.service.VehicleService;
import com.megacity.model.Booking;
import com.megacity.model.Driver;
import com.megacity.model.User;
import com.megacity.model.Vehicle;
import com.megacity.service.BookingService;
import com.megacity.service.UserService;

/**
 * Servlet implementation class AdminBookingServlet
 */
@WebServlet("/admin/bookings")
public class AdminBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingService bookingService = new BookingService();
	private UserService userService = new UserService();
	private DriverService driverService = new DriverService();
	private VehicleService vehicleService = new VehicleService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

	    if (status == null || status.isEmpty()) {
	        response.sendRedirect(request.getContextPath() + "/admin"); 
	        return;  
	    }
	    
	    status = status.toLowerCase();

	    List<Booking> bookings = new ArrayList<>();
	    
	    if (status.equals("pending") || status.equals("accepted") || status.equals("declined")) {
            bookings = bookingService.getBookingsByStatus(status);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        if (bookings == null) {
            bookings = new ArrayList<>();
        }

        for (Booking booking : bookings) {
            User user = userService.getUserById(booking.getCustomerId());
            booking.setUser(user);

            if (status.equals("accepted")) {
                if (booking.getDriverId() > 0) {
                    Driver driver = driverService.getDriverById(booking.getDriverId());
                    booking.setDriver(driver);
                }
                if (booking.getVehicleId() > 0) {
                    Vehicle vehicle = vehicleService.getVehicleById(booking.getVehicleId());
                    booking.setVehicle(vehicle);
                }
            }
        }

	    request.setAttribute("status", status);
	    request.setAttribute("bookings", bookings);
	    request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("id");

        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            sendErrorMessage(request, response, "Booking ID is missing.");
            return;
        }

        int bookingId = Integer.parseInt(bookingIdStr);

        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            bookingService.declineBooking(bookingId);
            request.setAttribute("messages", List.of("Booking declined successfully!"));
            request.setAttribute("messageType", "success");

            List<Booking> bookings = bookingService.getBookingsByStatus("pending");
            request.setAttribute("bookings", bookings);
        } else {
            sendErrorMessage(request, response, "Booking not found.");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);
    }

    private void sendErrorMessage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");

        List<Booking> bookings = bookingService.getBookingsByStatus("pending");
        request.setAttribute("bookings", bookings);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);
    }
}
