package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.demo.service.ProfileService;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	/**
	 * 访问用户中心首页 profile
	 * @date 2024年6月16日 上午9:06:30
	 * @return
	 */
	@GetMapping("/main")
	public String toMainOfProile() {
		return "mainOfProfile";
	}
	
	
	/**
	 * 访问我的问题 页面
	 * @date 2024年6月16日 上午9:06:30
	 * @return
	 */
	@GetMapping("/MyQuestion")
	public String toMyQuestionOfProfile(@RequestParam(defaultValue = "1") Integer pn,Model m) {
		PageHelper.startPage(pn, 3);
		Page<Question> pageList= profileService.selectMyQuestion();
		PageInfo<Question> pageInfo = new PageInfo<>(pageList,2);
		m.addAttribute("pageInfo",pageInfo);
		return "MyQuestionOfProfile";
	}
	
	
	/**
	 * 访问我的回复 页面
	 * @date 2024年6月16日 上午9:06:30
	 * @return
	 */
	@GetMapping("/MyReply")
	public String toReplyOfProfile() {
		return "MyReplyOfProfile";
	}

}
