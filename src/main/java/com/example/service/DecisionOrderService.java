package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.form.DecisionOrderForm;
import com.example.repository.OrderRepository;

/**
 * 注文確定の業務処理を行う.
 * 
 * @author izawamotoki
 *
 */
@Service
public class DecisionOrderService {

	@Autowired
	private OrderRepository orderRepository;
	private MailSender mailSender;

	/**
	 * 注文確認画面で入力された情報をDBに登録する.
	 * 
	 * @param form 入力された宛先情報
	 * 
	 */
	public void DecisionOrder(DecisionOrderForm form) {
		Order order = orderRepository.load(form.getOrderId());
		BeanUtils.copyProperties(form, order);
		if (order.getPaymentMethod() == 1) {
			order.setStatus(1);
		}
		if (order.getPaymentMethod() == 2) {
			order.setStatus(2);
		}
		orderRepository.update(order);

//		mailSender = null;
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom("motoki.izawa@rakus-partners.co.jp");
//		mailMessage.setTo(form.getDestinationEmail());
//		mailMessage.setSubject("ラクラクヌードル注文確定メール");
//		mailMessage.setText("注文が確定しました");
//
//		try {
//			mailSender.send(mailMessage);
//		} catch (MailException e) {
//			System.out.println("エラー");
//		}
	}

	public Order load(Integer orderId) {
		Order order = orderRepository.load(orderId);
		return order;
	}
}
