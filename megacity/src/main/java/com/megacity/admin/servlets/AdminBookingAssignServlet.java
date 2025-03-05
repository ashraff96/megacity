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
import com.megacity.dao.BookingDAO;
import com.megacity.model.Booking;
import com.megacity.model.Driver;
import com.megacity.model.Vehicle;
import com.megacity.service.BookingService;

/**
 * Servlet implementation class BookingAssignServlet
 */
@WebServlet("/admin/bookings/assign")
public class AdminBookingAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService = new VehicleService();
	private DriverService driverService = new DriverService();
	private BookingService bookingService = new BookingService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingAssignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingIdStr = request.getParameter("id");

        if (bookingIdStr == null) {
        	response.sendRedirect(request.getContextPath() + "/admin/bookings?status=pending");
            return;
        }

        try {
            int bookingId = Integer.parseInt(bookingIdStr);
            Booking booking = new BookingDAO().getBookingById(bookingId);

            if (booking == null || !"pending".equalsIgnoreCase(booking.getBookingStatus())) {
            	response.sendRedirect(request.getContextPath() + "/admin/bookings?status=pending");
                return;
            }
            
            int requiredSeats = Integer.parseInt(booking.getNoOfSeats());

            forwardToBookingAssign(request, response, bookingId, requiredSeats, null);
            
        } catch (NumberFormatException e) {
        	response.sendRedirect(request.getContextPath() + "/admin/bookings?status=pending");
            return;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vehicleIdStr = request.getParameter("vehicle_id");
	    String driverIdStr = request.getParameter("driver_id");
	    String distanceStr = request.getParameter("distance");
	    String ratePerKmStr = request.getParameter("rate_per_km");
	    
	    double baseFare = Double.parseDouble(request.getParameter("base-fare"));
	    double taxRate = Double.parseDouble(request.getParameter("tax-rate"));
	    double discountRate = Double.parseDouble(request.getParameter("discount-rate"));
	    
	    int bookingId = Integer.parseInt(request.getParameter("booking_id"));
	    int requiredSeats = Integer.parseInt(request.getParameter("required_seats"));

	    boolean hasErrors = false;
	    List<String> errorMessages = new ArrayList<String>();

        try {
            int vehicleId = Integer.parseInt(vehicleIdStr);
            int driverId = Integer.parseInt(driverIdStr);
            double distance = Double.parseDouble(distanceStr);
            double ratePerKm = Double.parseDouble(ratePerKmStr);

            if (distance <= 0) {
                hasErrors = true;
                errorMessages.add("Distance must be greater than zero");
            } else if (ratePerKm < 10) {
                hasErrors = true;
                errorMessages.add("Rate per kilometer must be greater than 10");
            }

            if (!hasErrors) {
            	int fare = getNetFare(distance, ratePerKm, baseFare, taxRate, discountRate);
                Booking booking = new Booking();
                booking.setId(bookingId);
                booking.setVehicleId(vehicleId);
                booking.setDriverId(driverId);
                booking.setFare(fare);
                
                boolean bookingAssigned = bookingService.assignBooking(booking);

                if (bookingAssigned) {
                	response.sendRedirect(request.getContextPath() + "/admin/bookings?status=accepted");
                    return;
                } else {
                    hasErrors = true;
                    errorMessages.add("Failed to assign the booking. Please try again later");
                }
            }
        } catch (NumberFormatException e) {
            hasErrors = true;
            errorMessages.add("Invalid input format. Please check your inputs");
        }
	    
	    if(hasErrors){
	        forwardToBookingAssign(request, response, bookingId, requiredSeats, errorMessages);
	    }
	}
	
	private int getNetFare(double distance, double ratePerKm, double baseFare, double taxRate, double discountRate) {
        double grossFare = baseFare + (distance * ratePerKm);
        double taxAmount = (grossFare * taxRate) / 100;
        double discountAmount = (grossFare * discountRate) / 100;
        double netFare = (grossFare + taxAmount) - discountAmount;
        netFare = Math.floor(netFare / 10) * 10;
        
        return (int)netFare;
	}
	
	private void forwardToBookingAssign(HttpServletRequest request, HttpServletResponse response, int bookingId, int requiredSeats, List<String> errorMessages) throws ServletException, IOException {
		List<Vehicle> availableVehicles = vehicleService.getVehiclesWithMinSeats(requiredSeats);
        List<Driver> availableDrivers = driverService.getAllDrivers();
        
		request.setAttribute("requiredSeats", requiredSeats);
        request.setAttribute("bookingId", bookingId);
        request.setAttribute("availableVehicles", availableVehicles);
        request.setAttribute("availableDrivers", availableDrivers);
        
        request.setAttribute("messages", errorMessages);
        request.setAttribute("messageType", errorMessages != null ? "error" : "");

        request.getRequestDispatcher("/WEB-INF/views/admin/booking-assign.jsp").forward(request, response);
	}
}
