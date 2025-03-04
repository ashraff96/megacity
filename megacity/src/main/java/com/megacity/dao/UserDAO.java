package com.megacity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.megacity.db.DBConnectionFactory;
import com.megacity.model.User;
import com.megacity.utils.PasswordHasher;

public class UserDAO implements IUserDAO{

	@Override
	public boolean insertUser(User user) {
		String query = "INSERT INTO users (name, address, nic, telephone, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getNic());
            stmt.setString(4, user.getTelephone());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
	}

	@Override
	public boolean validateCredentials(String username, String password) {
		String query = "SELECT password FROM users WHERE username = ?";
        
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
	public User findUserByUsername(String username) {
		String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                	rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("nic"),
                    rs.getString("telephone"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean isUsernameTaken(String username) {
		String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
