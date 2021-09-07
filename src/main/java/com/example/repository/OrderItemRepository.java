package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * order_itemsテーブルを操作するRepositoryクラス
 * 
 * @author inada
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * OrderItemsオブジェクトを生成するローマッパー.
	 *
	 */
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setCount(rs.getInt("count"));
		return orderItem;
	};

	/**
	 * 注文商品をショッピングカートに追加する.
	 * 
	 * @param orderItem 追加したい商品
	 * @return 追加した商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "INSERT INTO order_items (item_id,order_id,quantity,size) VALUES (:itemId,:orderId,:quantity,:size);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = { "id" };
		template.update(sql, param, keyHolder, keyColumnName);
		orderItem.setId(keyHolder.getKey().intValue());
		return orderItem;
	}

	/**
	 * 注文商品と注文商品のトッピングを削除する.
	 * 
	 * @param orderItemId 削除したい注文商品
	 */
	public void deleteOrderItemWithOrderTopping(int orderItemId) {
		String sql = "WITH deleted AS (DELETE FROM order_items WHERE id = :id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(sql, param);
	}

	/**
	 * 仮IDの注文をUserIDの注文に変更.
	 * 
	 * @param orderItem 変更したい注文
	 */
	public void update(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "UPDATE order_items SET item_id=:itemId,order_id=:orderId,quantity=:quantity,size=:size WHERE id=:id;";
		template.update(sql, param);
	}

	public List<OrderItem> count() {
		String sql = "SELECT item_id,COUNT(item_id) FROM order_items GROUP BY item_id ORDER BY count(item_id) DESC;";
		List<OrderItem> orderItemList = template.query(sql, ORDER_ITEM_ROW_MAPPER);
		return orderItemList;
	}
}
