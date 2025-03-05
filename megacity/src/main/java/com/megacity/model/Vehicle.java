package com.megacity.model;

public class Vehicle {
	private int id;
	private String vehicleNumber;
	private String vehicleType;
	private String noOfSeats;
	
	public Vehicle(String vehicleNumber, String vehicleType, String noOfSeats) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.noOfSeats = noOfSeats;
	}

	public Vehicle(int id, String vehicleNumber, String vehicleType, String noOfSeats) {
		this.id = id;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.noOfSeats = noOfSeats;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(String noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
}
