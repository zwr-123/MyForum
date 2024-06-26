package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Comment;
import com.example.demo.bean.CommentException;
import com.example.demo.enums.CommentErrorMessage;
import com.example.demo.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("/insertOne")
	@ResponseBody 
	public String insertComment(@RequestBody Comment comment,HttpServletRequest request) {
//		这里应该用token判断，但是为了简单用session判断
		if(request.getSession().getAttribute("gitHubUser") ==null) {
			throw new CommentException(CommentErrorMessage.USER_NOT_LOGIN);
		}
		commentService.insertOne(comment);
		System.out.println("你好");
		return "你好";
	}
}
