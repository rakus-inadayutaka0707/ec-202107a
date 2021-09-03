package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowListService;

/**
 * 商品情報を操作するコントローラークラス.
 * 
 * @author kojiro0706
 *
 */
@Controller
@RequestMapping("/show-list")
public class ShowListController {

	@Autowired
	private ShowListService showListService;

	/**
	 * 商品情報を出力する.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品情報
	 */
	@RequestMapping("")
	public String showList(Model model, String name) {
		List<Item> itemList = showListService.showList(name, model);
		List<List<Item>> itemListRow = new ArrayList<>();
		List<Item> takeItemList = new ArrayList<>();
		for (int i = 0; i < itemList.size(); i++) {
			if (i % 3 == 0) {
			  takeItemList = new ArrayList<>();
			}
			itemList.get(i);
			takeItemList.add(itemList.get(i));
			if(takeItemList.size() == 3) {
				itemListRow.add(takeItemList);
			}else if(itemList.size() == i && takeItemList.size() != 3) {
				itemListRow.add(takeItemList);
			}
		}
		model.addAttribute("itemListRow", itemListRow);
		return "item_list_noodle";
	}
}
