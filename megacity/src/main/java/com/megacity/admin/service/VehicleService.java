package com.megacity.admin.service;

import java.util.List;

import com.megacity.admin.dao.VehicleDAO;
import com.megacity.model.Vehicle;

public class VehicleService implements IVehicleService {
	
	private VehicleDAO vehicleDAO = new VehicleDAO();

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

	@Override
	public List<Vehicle> getVehiclesWithMinSeats(int seats) {
		return vehicleDAO.getVehiclesWithMinSeats(seats);
	}
}
