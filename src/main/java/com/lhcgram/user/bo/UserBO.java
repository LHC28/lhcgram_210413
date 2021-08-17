package com.lhcgram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.user.dao.UserDAO;
import com.lhcgram.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public User getUserById(String loginId) {
		return userDAO.getUserById(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserByloginIdAndPassword(String loginId, String password) {
		return userDAO.selectUserByloginIdAndPassword(loginId, password);
	}
}
