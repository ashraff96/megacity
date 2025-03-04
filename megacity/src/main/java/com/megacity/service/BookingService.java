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

}
