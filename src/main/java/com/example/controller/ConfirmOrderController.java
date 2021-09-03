package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.DecisionOrderForm;
import com.example.service.ConfirmOrderService;
import com.example.service.LoginCheckService;

/**
 * 注文確認画面表示時に使用するControllerクラス.
 * 
 * @author izawa
 *
 */

@Controller
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

	@Autowired
	private ConfirmOrderService confirmOrderService;

	@Autowired
	private LoginCheckService loginCheckService;

	@ModelAttribute
	public DecisionOrderForm setUpDecisionOrderForm() {
		return new DecisionOrderForm();
	}

	/**
	 * 注文確認画面時に表示するショッピングカートの商品を取得する.
	 * 
	 * @param userId ショッピングカートを取得したい注文のID
	 * @param userId ショッピングカートを取得したい人のID
	 * @return 注文確認画面
	 */
	@RequestMapping("")
	public String confirmOrder(String orderId, String userId, Model model, HttpServletRequest request) {
		String url = request.getRequestURI();
		if (loginCheckService.loginCheck(url)) {
			return "redirect:/login/toLogin";
		}
		Order order = confirmOrderService.confirmOrder(Integer.parseInt(orderId));
		model.addAttribute("order", order);
		return "order_confirm";
	}

}
