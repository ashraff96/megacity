package com.megacity.admin.service;

import java.util.List;

import com.megacity.model.Driver;

public interface IDriverService {
	boolean addDriver(Driver driver);
    List<Driver> getAllDrivers();
    Driver getDriverById(int id);
}
