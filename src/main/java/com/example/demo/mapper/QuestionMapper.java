package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.Question;

@Mapper
public interface QuestionMapper {

	@Insert("insert into question(title,description,create_time,modified_time,creator_id,tag) values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
	void insertQuestion(Question question);
	
}
