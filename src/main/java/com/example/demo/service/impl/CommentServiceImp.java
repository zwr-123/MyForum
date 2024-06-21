package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Comment;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImp implements CommentService{
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public void insertOne(Comment comment) {
		commentMapper.insertOne(comment);
	}
	
}
