package com.megacity.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.megacity.model.User;

public class UserServiceTest {

    private UserService userService;
    private String hashedPassword = "$2a$10$twyotpZJdezyah0UfA5LcO9VjJy/pL1QY4uq8/SOp/.jHdbosytZ6"; // Hashed value for the word 'ashra'

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testCreateUser() {
        User user = new User("Ashra", "Anuradhapura", "961233448", "0777123456", "ashra", hashedPassword);
        boolean result = userService.createUser(user);
        assertTrue(result);
    }

    @Test
    public void testAuthenticateUserSuccess() {
        String username = "ashra";
        String password = "ashra";
        boolean isAuthenticated = userService.authenticateUser(username, password);
        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticateUserFailure() {
        String username = "ashra";
        String password = "abcdefg";
        boolean isAuthenticated = userService.authenticateUser(username, password);
        assertFalse(isAuthenticated);
    }

    @Test
    public void testGetUserDetails() {
        String username = "ashra";
        User expectedUser = new User("Ashra", "Anuradhapura", "961233448", "0777123456", "ashra", hashedPassword);
        User result = userService.getUserDetails(username);
        assertNotNull(result);
        assertEquals(expectedUser.getUsername(), result.getUsername());
    }

    @Test
    public void testIsUsernameTaken() {
        String username = "ashra";
        boolean result = userService.isUsernameTaken(username);
        assertTrue(result);
    }
}
