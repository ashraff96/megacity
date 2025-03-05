package com.megacity.admin.dao;

import java.util.List;

import com.megacity.model.Vehicle;

public interface IVehicleDAO {
	boolean addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesWithMinSeats(int seats);
}
