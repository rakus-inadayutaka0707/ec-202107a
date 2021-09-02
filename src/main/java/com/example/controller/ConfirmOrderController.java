package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.ConfirmOrderService;

@Controller
@RequestMapping("confirm-order")
public class ConfirmOrderController {

	@Autowired
	private ConfirmOrderService confirmOrderService;

	@RequestMapping("")
	public String confirmOrder(Integer orderId, Model model) {
		Order order = confirmOrderService.load(orderId);
		model.addAttribute("order", order);
		return "order_confirm";
	}

}
