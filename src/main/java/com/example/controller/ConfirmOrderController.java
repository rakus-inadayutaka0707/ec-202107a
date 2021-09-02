package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.ConfirmOrderService;

@Controller
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

	@Autowired
	private ConfirmOrderService confirmOrderService;

	@RequestMapping("")
	public String confirmOrder(String orderId, String userId, Model model) {
		if (userId == null) {
			return "/toLogin";
		}
		Order order = confirmOrderService.load(Integer.parseInt(orderId));
		model.addAttribute("order", order);
		return "order_confirm";
	}

}
