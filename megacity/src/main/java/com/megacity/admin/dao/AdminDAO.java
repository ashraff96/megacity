package com.megacity.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.megacity.db.DBConnectionFactory;
import com.megacity.model.Admin;
import com.megacity.utils.PasswordHasher;

public class AdminDAO implements IAdminDAO {

	@Override
	public boolean authenticateAdmin(String username, String password) {
		String query = "SELECT password FROM admins WHERE username = ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return PasswordHasher.verifyPassword(password, hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public Admin getAdminDetails(String username) {
		String query = "SELECT * FROM admins WHERE username = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                	rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;    
	}

}
