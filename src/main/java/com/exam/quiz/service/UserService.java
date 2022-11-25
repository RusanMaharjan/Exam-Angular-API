package com.exam.quiz.service;

import java.util.Set;

import com.exam.quiz.model.User;
import com.exam.quiz.model.UserRole;

public interface UserService {

	//creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public User getUser(String userName);
	
	//delete user by id
	public void deleteUser(Long userId);
	
}
