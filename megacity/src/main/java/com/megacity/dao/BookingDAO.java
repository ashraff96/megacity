package com.megacity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.megacity.db.DBConnectionFactory;
import com.megacity.model.Booking;

public class BookingDAO implements IBookingDAO{

	@Override
	public boolean insertBooking(Booking booking) {
		String query = "INSERT INTO bookings (customer_id, pickup_location, destination, pickup_date_time, no_of_seats, message) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, booking.getCustomerId());
            stmt.setString(2, booking.getPickupLocation());
            stmt.setString(3, booking.getDestination());
            stmt.setObject(4, booking.getPickupDateTime());
            stmt.setInt(5, Integer.parseInt(booking.getNoOfSeats()));
            stmt.setString(6, booking.getMessage());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<Booking> getBookingsByUserId(int id) {
		String query = "SELECT * FROM bookings WHERE customer_id = ? ORDER BY booking_date_time DESC";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { 
                    Booking booking = new Booking(
                        rs.getInt("id"),
                        rs.getString("pickup_location"),
                        rs.getString("destination"),
                        rs.getString("booking_status"),
                        rs.getTimestamp("booking_date_time").toLocalDateTime(),
                        rs.getTimestamp("pickup_date_time"),
                        rs.getString("no_of_seats"),
                        rs.getString("message"),
                        rs.getInt("fare")
                    );
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings; 
	}

}
