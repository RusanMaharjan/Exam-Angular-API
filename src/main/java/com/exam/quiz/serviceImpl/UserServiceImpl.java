package com.exam.quiz.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.quiz.model.User;
import com.exam.quiz.model.UserRole;
import com.exam.quiz.repository.RoleRepository;
import com.exam.quiz.repository.UserRepository;
import com.exam.quiz.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUserName(user.getUserName());
		if(local != null) {
			System.out.println("User already exist!!");
			throw new Exception("User already present!!");
		} else {
			//user create
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	//get user by user name
	@Override
	public User getUser(String userName) {
		return this.userRepository.findByUserName(userName);
	}

	//delete user
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}
	
}
