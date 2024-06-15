package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;

public interface IndexService {

	/**
	 * 首页获取所有问题信息
	 * @date 2024年6月15日 上午9:33:19
	 * @return
	 */
	List<QuestionDTO> selectAll();

	/**
	 * 测试查询问题信息
	 * @date 2024年6月15日 下午7:31:33
	 * @return
	 */
	List<Question> selectAllTest();

}
