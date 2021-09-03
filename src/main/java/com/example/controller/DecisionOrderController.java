package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.DecisionOrderForm;
import com.example.service.DecisionOrderService;

/**
 * 宛先や支払方法を操作するコントローラークラス.
 * 
 * @author izawamotoki
 *
 */
@Controller
@RequestMapping("/decisionorder")
public class DecisionOrderController {

	@Autowired
	private DecisionOrderService decisionOrderService;

	@ModelAttribute
	public DecisionOrderForm setUpDecisionOrderForm() {
		return new DecisionOrderForm();
	}

	/**
	 * 宛先や支払方法等の情報の登録を行う.
	 * 
	 * @param form 宛先や支払方法等の情報用フォーム.
	 * @return 注文確認画面
	 * @return 注文完了画面
	 */
	@RequestMapping("")
	public String DecisionOrder(@Validated DecisionOrderForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "order_confirm";
		}
		long millis = System.currentTimeMillis();
		long deliveryTime = 0;
		deliveryTime = form.getDeliveryTime().getTime();
		long difference = deliveryTime - millis;

		if (difference < 10800000) {
			result.rejectValue("deliveryTime", "", "今から3時間後の日時をご入力ください");
			return "order_confirm";
		}

		decisionOrderService.DecisionOrder(form);
		return "order_finished";
	}

}
