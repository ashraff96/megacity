package com.megacity.admin.service;

import java.util.List;

import com.megacity.model.Vehicle;

public interface IVehicleService {
	boolean addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesWithMinSeats(int seats);
}
