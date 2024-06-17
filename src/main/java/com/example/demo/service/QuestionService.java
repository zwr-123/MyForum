package com.example.demo.service;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;

public interface QuestionService {

	void addQuestion(Question question);

	/**
	 * 根据id查询
	 * @date 2024年6月17日 上午10:49:44
	 * @param id
	 * @return
	 */
	QuestionDTO seletById(Integer id);

}
