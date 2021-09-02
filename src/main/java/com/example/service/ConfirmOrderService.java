package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文確認画面の業務処理を行う.
 * 
 * @author izawamotoki
 *
 */
@Service
public class ConfirmOrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文確認画面に表示する商品一覧を取得する.
	 * 
	 * @param orderId を取得したい注文のID
	 * @return 取得した注文情報
	 */
	public Order load(Integer orderId) {
		Order order = orderRepository.load(orderId);
		return order;
	}
}
