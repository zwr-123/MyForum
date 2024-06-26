package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Comment;
import com.example.demo.bean.CommentException;
import com.example.demo.enums.CommentErrorMessage;
import com.example.demo.enums.commentType;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImp implements CommentService{
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public void insertOne(Comment comment) {

		if(comment.getParentId() == null || comment.getParentId() == 0) {
			throw new CommentException(CommentErrorMessage.TARGET_NOT_FOUND);
		}
		
		if(comment.getType() != 1 && comment.getType() !=2) {
			throw new CommentException(CommentErrorMessage.TYPE_NOT_FOUND);
		}
		
		LocalDateTime now = LocalDateTime.now();
		comment.setGmtCreate(now);
		comment.setGmtModified(now);
		commentMapper.insertOne(comment);
	}
	
}
