package com.megacity.dao;

import java.util.List;

import com.megacity.model.Booking;

public interface IBookingDAO {
	boolean insertBooking(Booking booking);
	List<Booking> getBookingsByUserId(int id);
}
