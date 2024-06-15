package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.GitHubUser;
import com.example.demo.bean.Question;
import com.example.demo.service.QuestionService;
import com.example.demo.util.BaseContext;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	
	/**
	 * 去问题发布页
	 * @date 2024年6月13日 下午5:19:26
	 * @return
	 */
	@GetMapping("/publish")
	public String ToPublish() {
		return "publish";
	}
	
	
	@PostMapping("/publish")
	public String addQuestion(Question question,HttpServletRequest req) {
		
		questionService.addQuestion(question);
		return "redirect:/";
	}
}
