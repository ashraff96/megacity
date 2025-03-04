package com.megacity.service;

import java.util.List;

import com.megacity.model.Booking;

public interface IBookingService {
	boolean createBooking(Booking booking);
	List<Booking> getBookingsByUserId(int id);
}
