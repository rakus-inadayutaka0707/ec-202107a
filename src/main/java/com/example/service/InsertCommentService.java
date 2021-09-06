package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメント情報を操作するサービスクラス.
 * 
 * @author kojiro0706
 *
 */
@Service
public class InsertCommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void insertComment(Comment comment) {
		
		commentRepository.insert(comment);
	}

}
