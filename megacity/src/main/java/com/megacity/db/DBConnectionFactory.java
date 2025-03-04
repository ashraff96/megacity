package com.megacity.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }
}
