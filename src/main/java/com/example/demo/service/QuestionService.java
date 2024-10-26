package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;

public interface QuestionService {

	void addQuestion(Question question);

	/**
	 * 根据id查询questionDTO
	 * @date 2024年6月17日 上午10:49:44
	 * @param id
	 * @return
	 */
	QuestionDTO seletDtoById(Integer id);

	/**
	 * 根据id查询question
	 * @date 2024年6月18日 上午8:24:55
	 * @param id
	 * @return
	 */
	Question seletById(Integer id);

	/**
	 * 更新问题
	 * @date 2024年6月18日 上午9:18:39
	 * @param question
	 */
	void updateQuestion(Question question);

	/**
	 * 阅读数增加
	 * @date 2024年6月20日 上午8:46:03
	 */
	void viewCountIncrease(Integer id);

	/**
	 * 根据tag查询相关问题
	 * @date 2024年10月26日 下午12:15:39
	 * @param tag
	 * @param id
	 * @return
	 */
	List<Question> selectQuestionByTags(String tag, Integer id);

}
