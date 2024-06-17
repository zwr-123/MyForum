package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;

@Mapper
public interface QuestionMapper {

	@Insert("insert into question(title,description,create_time,modified_time,creator_id,tag) values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
	void insertQuestion(Question question);
	
	@Select("select * from question")
	List<Question> selectAll();

	/**
	 * 根据id查询DTO
	 * @date 2024年6月17日 上午10:52:28
	 * @param id
	 * @return
	 */
	@Select("select * from question where id=#{id}")
	Question selectDtoById(Integer id);
	
}
