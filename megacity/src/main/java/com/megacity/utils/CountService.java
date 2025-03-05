package com.megacity.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.megacity.db.DBConnectionFactory;

public class CountService {
	public int getNoOfRecords(String tableName) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM " + tableName;

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        	return -1;
        }

        return count;
    }
}
