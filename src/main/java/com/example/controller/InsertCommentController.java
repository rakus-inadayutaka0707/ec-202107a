package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.InsertCommentForm;
import com.example.service.InsertCommentService;

/**
 * コメント情報を登録するコントローラークラス.
 * 
 * @author kojiro0706
 *
 */
@Controller
@RequestMapping("insert-comment")
public class InsertCommentController {
	
	@Autowired
	private InsertCommentService insertCommentService;
	
	/**
	 * コメント情報を登録する.
	 * 
	 * @param form
	 * @return コメント情報
	 */
	@RequestMapping("/insertComment")
	public String insertComment(InsertCommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setItemId(Integer.parseInt(form.getItemId()));
		insertCommentService.insertComment(comment);

		return "redirect:/show-item-detail";
	}

}
