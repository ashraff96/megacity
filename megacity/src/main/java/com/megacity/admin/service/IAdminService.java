package com.megacity.admin.service;

import com.megacity.model.Admin;

public interface IAdminService {
	boolean authenticateAdmin(String username, String password);
    Admin getAdminDetails(String username);
}
