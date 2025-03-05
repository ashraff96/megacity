package com.megacity.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class DBConnectionTest {
	
	private static Connection connection;
	
	@BeforeClass
	public static void setUp() throws SQLException {
		connection = DBConnectionFactory.getConnection();
	}
	
	@Test
	public void testDatabaseConnection() {
		assertNotNull("Database connection shouldn't be null", connection);
	}
	
	@Test
	public void testConnectionIsValid() throws Exception {
		assertTrue("Connection should be valid", connection.isValid(2));
	}
}
