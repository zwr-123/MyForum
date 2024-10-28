package com.example.demo.controller;


import java.time.LocalDateTime;
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
import com.example.demo.bean.Notification;
import com.example.demo.bean.Question;
import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import com.example.demo.bean.DTO.CommentDTO;
import com.example.demo.enums.CommentErrorMessage;
import com.example.demo.service.CommentService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/insertOne")
	@ResponseBody 
	public String insertComment(@RequestBody Comment comment,HttpServletRequest request) {
//		这里应该用token判断，但是为了简单用session判断
		if(request.getSession().getAttribute("gitHubUser") ==null) {
			throw new CommentException(CommentErrorMessage.USER_NOT_LOGIN);
		}
		//插入评论
		commentService.insertOne(comment);
		
//		插入通知
		Notification notification=new Notification();
		User user=userService.selectByID(comment.getCommentorId());
		
		//需要获取接受者（被评论人）的id，理论上要在comment中设置此字段。但我之前没设置，只能这样查询获得
		if(comment.getType() == 1) {
			Question question = questionService.seletById(comment.getParentId());
			notification.setRecipientId(question.getCreatorId());
		}else if(comment.getType() == 2) {
			Comment com=commentService.selectComment(comment.getParentId());
			notification.setRecipientId(com.getCommentorId());
		}
		
		
		notification.setSenderId(comment.getCommentorId());
		notification.setSenderName(user.getLogin());
		notification.setParentId(comment.getParentId());
		notification.setType(comment.getType());
		notification.setGmtCreate(LocalDateTime.now());
		
		notificationService.insertNotification(notification);


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
