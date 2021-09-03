package com.example.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCheckService {

	@Autowired
	private HttpSession session;
	
	public String loginCheck(HttpServletRequest request) {
		System.out.println(request);
		if(session.getAttribute("user") == null) {
			String url = request.getRequestURI();
			session.setAttribute("url", url);
			return "redirect:/login/toLogin";
		}
		session.removeAttribute("url");
		return null;
	}
}
