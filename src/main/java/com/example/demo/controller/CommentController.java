package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Comment;
import com.example.demo.bean.CommentException;
import com.example.demo.bean.Result;
import com.example.demo.bean.DTO.CommentDTO;
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
	
	@GetMapping("/{id}")
	@ResponseBody 
	public Result<List<CommentDTO>> getComment2(@PathVariable("id") Integer id){
		List<CommentDTO> comments2=commentService.selectCommentDTO(id, 2);
		if(comments2.size()>0) {
			return Result.success(comments2);
		}else {
			return Result.error("没有二级评论");
		}
	}
}
