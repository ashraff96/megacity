package com.megacity.service;

import com.megacity.model.User;

public interface IUserService {
	boolean createUser(User user);
    boolean authenticateUser(String username, String password);
    User getUserDetails(String username);
    boolean isUsernameTaken(String username);
    User getUserById(int id);
}
