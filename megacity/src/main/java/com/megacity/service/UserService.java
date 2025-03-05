package com.megacity.service;

import com.megacity.dao.UserDAO;
import com.megacity.model.User;

public class UserService implements IUserService{
	
	private UserDAO userDAO;
	
	public UserService() {
        this.userDAO = new UserDAO();
    }

	@Override
	public boolean createUser(User user) {
		return userDAO.insertUser(user);
	}

	@Override
	public boolean authenticateUser(String username, String password) {
		return userDAO.validateCredentials(username, password);
	}

	@Override
	public User getUserDetails(String username) {
		return userDAO.findUserByUsername(username);
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return userDAO.isUsernameTaken(username);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}
}
