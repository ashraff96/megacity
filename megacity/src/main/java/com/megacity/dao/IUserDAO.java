package com.megacity.dao;

import com.megacity.model.User;

public interface IUserDAO {
	boolean insertUser(User user);
    boolean validateCredentials(String username, String password);
    User findUserByUsername(String username);
    boolean isUsernameTaken(String username);
    User getUserById(int id);
}
