package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	}

	/**
	 * 注文情報を主キー検索する.
	 * 
	 * @param orderId 注文ID
	 * @return 取得された注文情報
	 * 
	 */
	public Order load(Integer orderId) {
		Order order = orderRepository.load(orderId);
		return order;
	}
}
