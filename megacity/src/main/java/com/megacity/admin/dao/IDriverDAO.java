package com.megacity.admin.dao;

import com.megacity.model.Driver;
import java.util.List;

public interface IDriverDAO {
    boolean insertDriver(Driver driver);
    List<Driver> getAllDrivers();
    Driver getDriverById(int id);  
}
