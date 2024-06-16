package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Question;
import com.example.demo.mapper.ProfileMapper;
import com.example.demo.service.ProfileService;
import com.example.demo.util.BaseContext;
import com.github.pagehelper.Page;

@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	ProfileMapper profileMapper;
	
	@Override
	public Page<Question> selectMyQuestion() {
		// TODO Auto-generated method stub
		return (Page)profileMapper.selectMyQuestion(BaseContext.getCurrentId());
	}
	
}
