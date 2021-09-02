package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class RegisterUserService {

	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
