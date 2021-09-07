package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;

@Service
public class RankingService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> ranking() {
		List<OrderItem> orderItemList = orderItemRepository.count();
		return orderItemList;
	}

}
