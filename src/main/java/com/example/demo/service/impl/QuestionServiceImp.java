package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Question;
import com.example.demo.bean.QuestionException;
import com.example.demo.bean.User;
import com.example.demo.bean.DTO.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.QuestionService;
import com.example.demo.util.BaseContext;

@Service
public class QuestionServiceImp implements QuestionService{
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	UserMapper userMapper;
	/**
	 * 发布问题
	 */
	@Override
	public void addQuestion(Question question) {
		if(question.getTitle() == null || " ".equals(question.getTitle()) || "".equals(question.getTitle())) {
			throw new QuestionException("标题不能为空！", question);
		}
		
		if(question.getDescription() ==null || " ".equals(question.getDescription()) || "".equals(question.getDescription())) {
			throw new QuestionException("描述不能为空！", question);
		}
		
		LocalDateTime now = LocalDateTime.now();
		question.setCreateTime(now);
		question.setModifiedTime(now);
		/**
		 * 	不能用这种方式获取id，因为当你登录过一次，前端有了token。关闭浏览器（清除了session），
		 * 此时依然能登录，用户信息存入session那一步就无需执行了
			GitHubUser gUser=(GitHubUser)req.getSession().getAttribute("gitHubUser");
		 */
		question.setCreatorId(BaseContext.getCurrentId());
		
		questionMapper.insertQuestion(question);
	}
	
	
	/**
	 * 根据id返回问题信息
	 */
	@Override
	public QuestionDTO seletDtoById(Integer id) {
		Question question = questionMapper.selectDtoById(id);
		QuestionDTO questionDTO = null;
		if(question!=null) {
			questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			User user = userMapper.selectByID(question.getCreatorId());
			questionDTO.setUser(user);
		}
		return questionDTO;
	}


	/**
	 * 根据id查询question
	 */
	@Override
	public Question seletById(Integer id) {
		return questionMapper.selectOneById(id);
	}

	/**
	 * 更新问题
	 */

	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		questionMapper.updateOne(question);
	}

	/**
	 * 阅读数增加
	 */

	@Override
	public void viewCountIncrease(Integer id) {
		questionMapper.updateByViewCount(id);
	}


	@Override
	public List<Question> selectQuestionByTags(String tag, Integer id) {
		// 你好，a标签
		List<String> tagList = Arrays.asList(tag.split("，"));
		String newTag = tag.replace(",", "|");
		List<Question> questionList=questionMapper.selectQuestionByTags(newTag,id);
		return questionList;
	}
		
}
