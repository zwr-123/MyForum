package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Notification;
import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.NotificationDTO;
import com.example.demo.bean.DTO.QuestionDTO;
import com.example.demo.mapper.NotificationMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ProfileService;
import com.example.demo.util.BaseContext;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	NotificationMapper notificationMapper ;
	
	@Autowired
	NotificationService notificationService;
	
	/**
	 * 访问用户中心首页 profile
	 * @date 2024年6月16日 上午9:06:30
	 * @return
	 */
	@GetMapping("/main")
	public String toMainOfProile(Model model) {
		List<Notification> notifications=notificationMapper.selectUnread(BaseContext.getCurrentId(),0);
		model.addAttribute("notifications",notifications);
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
		//返回未读通知
		List<Notification> notifications=notificationMapper.selectUnread(BaseContext.getCurrentId(),0);
		m.addAttribute("notifications",notifications);
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
	
	
	/**
	 * 访问我的通知 页面
	 * @date 2024年6月16日 上午9:06:30
	 * @return
	 */
	@GetMapping("/MyNotification")
	public String toMyNotification(@RequestParam(defaultValue = "1") Integer pn,Model m) {
		PageHelper.startPage(pn, 3);
		Page<NotificationDTO> list=notificationService.selectUnRead(BaseContext.getCurrentId());
		PageInfo<NotificationDTO> pageInfo=new PageInfo<>(list,2);
		m.addAttribute("notifications",list);
		m.addAttribute("pageInfo",pageInfo);
		return "MyNotification";
	}

}
