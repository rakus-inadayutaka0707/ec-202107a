package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー情報を操作するサービスクラス.
 * 
 * @author kojiro0706
 *
 */
@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * メールアドレスとパスワードからユーザー情報を取得します.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return　ユーザー情報
	 */
	public User login(String email, String password) {

		return userRepository.findByEmailAndPassword(email, password);

	}

}
