package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Comment;
import com.example.demo.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("/insertOne")
	@ResponseBody 
	public String insertComment(@RequestBody Comment comment) {
		LocalDateTime now = LocalDateTime.now();
		comment.setGmtCreate(now);
		comment.setGmtModified(now);
		commentService.insertOne(comment);
		System.out.println("你好");
		return "你好";
	}
}
