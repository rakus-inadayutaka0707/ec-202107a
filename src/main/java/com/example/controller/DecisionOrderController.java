package com.example.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
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

	@Autowired
	private HttpSession session;

	@RequestMapping("/toConfirmOrder")
	public String toConfirmOrder() {
		return "order_confirm";
	}

	/**
	 * 宛先や支払方法等の情報の登録を行う.
	 * 
	 * @param form 宛先や支払方法等の情報用フォーム.
	 * @return 注文確認画面
	 * @return 注文完了画面
	 */
	@RequestMapping("")
	public String DecisionOrder(@Validated DecisionOrderForm form, BindingResult result, Integer orderId, Model model) {
		if (result.hasErrors()) {
			Order order = decisionOrderService.load(orderId);
			model.addAttribute("order", order);
			return toConfirmOrder();
		}

		String deliveryTime = form.getDeliveryTimeDate().replace("-", "/") + " " + form.getDeliveryTimeHour();
//		System.out.println("deliveryTime" + deliveryTime);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		System.out.println("simpleDateFormat" + simpleDateFormat);
		Timestamp deliveryTimeStamp;
		try {
			deliveryTimeStamp = new Timestamp(simpleDateFormat.parse(deliveryTime).getTime());
//			System.out.println("deliveryTimeStamp" + deliveryTimeStamp);
			form.setDeliveryTime(deliveryTimeStamp);
		} catch (ParseException e) {
			Order order = decisionOrderService.load(orderId);
			model.addAttribute("order", order);
			return "order_confirm";
		}

		long millis = System.currentTimeMillis();
		long deliveryTimeFor3Hourago = 0;
		deliveryTimeFor3Hourago = form.getDeliveryTime().getTime();
		long difference = deliveryTimeFor3Hourago - millis;
//		System.out.println(deliveryTimeFor3Hourago);
//		System.out.println(difference);

		if (difference < 10800000) {
			Order order = decisionOrderService.load(orderId);
			model.addAttribute("order", order);
			result.rejectValue("deliveryTime", "", "今から3時間後の日時をご入力ください");
			return "order_confirm";
		}

		decisionOrderService.DecisionOrder(form);
		return "order_finished";
	}

}
