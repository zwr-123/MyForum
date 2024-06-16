package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;
import com.example.demo.service.IndexService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	IndexService indexService;
	
	
	/**
	 * 获取所有信息并分页,再返回首页
	 * @date 2024年6月15日 上午9:33:46
	 * @return
	 */
	@GetMapping()
	public String pageIndex(@RequestParam(defaultValue = "1") Integer pn,Model m) {
		PageHelper.startPage(pn, 3);
		Page<QuestionDTO> listOfQuestionDTO=(Page)indexService.selectAll();
		PageInfo<QuestionDTO> pageInfo = new PageInfo<>(listOfQuestionDTO,2);
		
		
		m.addAttribute("pageInfo",pageInfo);
		return "index";
	}
	
}
