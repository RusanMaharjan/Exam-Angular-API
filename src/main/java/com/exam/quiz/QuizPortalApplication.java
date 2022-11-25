package com.exam.quiz;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.quiz.model.Role;
import com.exam.quiz.model.User;
import com.exam.quiz.model.UserRole;
import com.exam.quiz.service.UserService;

@SpringBootApplication
public class QuizPortalApplication{
//	
//	@Autowired
//	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(QuizPortalApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Starting code...");
//		
//		User user = new User();
//		user.setFirstName("Rusan");
//		user.setLastName("Maharjan");
//		user.setUserName("rusan69");
//		user.setPassword("12345678");
//		user.setEmail("rusanmhz789@gmail.com");
//		user.setProfile("default.png");
//		
//		
//		Role role1 = new Role();
//		role1.setRoleId(12L);
//		role1.setRoleName("Admin");
//		
//		
//		Set<UserRole> userRoleSet = new HashSet<UserRole>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUserName());
//	}

}
