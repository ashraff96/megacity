package com.megacity.admin.dao;

import com.megacity.model.Admin;

public interface IAdminDAO {
	boolean authenticateAdmin(String username, String password);
    Admin getAdminDetails(String username);
}
