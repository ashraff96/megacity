package com.megacity.service;

import java.util.List;

import com.megacity.dao.BookingDAO;
import com.megacity.model.Booking;

public class BookingService implements IBookingService{

	private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }
	
    @Override
    public boolean createBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }

    @Override
	public List<Booking> getBookingsByUserId(int id) {
		return bookingDAO.getBookingsByUserId(id);
	}

	@Override
	public List<Booking> getBookingsByStatus(String status) {
		return bookingDAO.getBookingByStatus(status);
	}

	@Override
	public void declineBooking(int id) {
		bookingDAO.declineBooking(id);
	}

	@Override
	public boolean assignBooking(Booking booking) {
		return bookingDAO.assignBooking(booking);
	}

	@Override
	public Booking getBookingById(int id) {
		return bookingDAO.getBookingById(id); 	
	}
}
