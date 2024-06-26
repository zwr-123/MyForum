package com.example.demo.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.CommentException;
import com.example.demo.bean.QuestionException;
import com.example.demo.bean.Result;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public String QuestionHandler(QuestionException qe,Model m) {
		log.info("当前表单错误为：{}",qe.getMessage());
		m.addAttribute("questionException", qe);
		return "publish";
	} 
	
	@ExceptionHandler
	@ResponseBody
	public Result CommentHandler(CommentException ce) {
		log.info("当前评论错误为：{}",ce.getMessage());
		return Result.error(ce.getMessage());
	} 
}
