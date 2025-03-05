package com.megacity.admin.service;

import com.megacity.admin.dao.DriverDAO;
import com.megacity.model.Driver;
import java.util.List;

public class DriverService implements IDriverService {
    
    private DriverDAO driverDAO;
    
    public DriverService() {
    	this.driverDAO = new DriverDAO();
    }
    
    @Override
    public boolean addDriver(Driver driver) {
        return driverDAO.insertDriver(driver);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }

	@Override
	public Driver getDriverById(int id) {
		return driverDAO.getDriverById(id);
	}
}
