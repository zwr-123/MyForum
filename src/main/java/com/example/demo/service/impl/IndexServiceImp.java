package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Question;
import com.example.demo.bean.User;
import com.example.demo.bean.DTO.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IndexService;
import com.github.pagehelper.Page;

@Service
public class IndexServiceImp implements IndexService{
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<QuestionDTO> selectAll() {
		Page<Question> allQuestion = (Page)questionMapper.selectAll();
		Page<QuestionDTO> arrayList = new Page<>();
		QuestionDTO questionDTO;
		if(allQuestion !=null && allQuestion.size()>0) {
			for (Question question : allQuestion) {
				questionDTO = new QuestionDTO();
				BeanUtils.copyProperties(question, questionDTO);
				User user = userMapper.selectByID(question.getCreatorId());
				questionDTO.setUser(user);
				arrayList.add(questionDTO);
			}
		}
		BeanUtils.copyProperties(allQuestion, arrayList);
		return arrayList;
	}

	/**
	 * 测试查询问题信息
	 */
	@Override
	public List<Question> selectAllTest() {
		// TODO Auto-generated method stub
		return questionMapper.selectAll();
	}
}
