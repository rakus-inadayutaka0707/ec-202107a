package com.example.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCheckService {

	@Autowired
	private HttpSession session;
	
	public Boolean loginCheck(String url) {
		if(session.getAttribute("user") == null) {
			session.setAttribute("url", url);
			return true;
		}
		session.removeAttribute("url");
		session.removeAttribute("temporaryId");
		return false;
	}
}
