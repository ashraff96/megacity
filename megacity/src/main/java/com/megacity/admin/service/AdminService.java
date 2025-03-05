package com.megacity.admin.service;

import com.megacity.admin.dao.AdminDAO;
import com.megacity.model.Admin;

public class AdminService implements IAdminService {
	
	private AdminDAO adminDAO;

    public AdminService() {
    	this.adminDAO = new AdminDAO();
    }

	@Override
	public boolean authenticateAdmin(String username, String password) {
		return adminDAO.authenticateAdmin(username, password);
	}

	@Override
	public Admin getAdminDetails(String username) {
		return adminDAO.getAdminDetails(username);
	}

}
