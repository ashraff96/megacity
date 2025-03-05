package com.megacity.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Booking {
	private int id;                       
    private int customerId;               
    private Integer driverId;             
    private Integer vehicleId;            
    private String pickupLocation;        
    private String destination;           
    private String bookingStatus;         
    private LocalDateTime bookingDateTime;
    private Timestamp pickupDateTime; 
    private String seats;
    private String message;
    
    private User user;
    private int fare;
    private Driver driver;
    private Vehicle vehicle;
    
    public Booking() {}
    
	public Booking(int id, int customerId, Integer driverId, Integer vehicleId, String pickupLocation,
			String destination, String bookingStatus, LocalDateTime bookingDateTime, Timestamp pickupDateTime,
			String seats, String message, int fare) {
		this.id = id;
		this.customerId = customerId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.pickupLocation = pickupLocation;
		this.destination = destination;
		this.bookingStatus = bookingStatus;
		this.bookingDateTime = bookingDateTime;
		this.pickupDateTime = pickupDateTime;
		this.seats = seats;
		this.message = message;
		this.fare = fare;
	}
	
	public Booking(int id, String pickupLocation,
			String destination, String bookingStatus, LocalDateTime bookingDateTime, Timestamp pickupDateTime,
			String seats, String message, int fare) {
		this.id = id;
		this.pickupLocation = pickupLocation;
		this.destination = destination;
		this.bookingStatus = bookingStatus;
		this.bookingDateTime = bookingDateTime;
		this.pickupDateTime = pickupDateTime;
		this.seats = seats;
		this.message = message;
		this.fare = fare;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	public Timestamp getPickupDateTime() {
		return pickupDateTime;
	}
	public void setPickupDateTime(Timestamp pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}
	public String getNoOfSeats() {
		return seats;
	}
	public void setNoOfSeats(String seats) {
		this.seats = seats;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}               
}
