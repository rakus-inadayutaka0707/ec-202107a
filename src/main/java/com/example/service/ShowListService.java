package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービスクラス.
 * 
 * @author kojiro0706
 *
 */
@Service
public class ShowListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品情報を取得する.
	 * 
	 * @return 商品情報
	 */
	public List<Item> showList(String name,Model model) {
		if (name == null) {
			List<Item> itemList = itemRepository.findAll();
			return itemList;
		} else {
			List<Item> itemList = itemRepository.findByName(name);
			if (itemList.size() == 0) {
				model.addAttribute("emptyMessage", "該当する商品がありません");
				return itemRepository.findAll();
			}
			return itemList;
		}
	}

}
