package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Question;

@Mapper
public interface QuestionMapper {

	@Insert("insert into question(title,description,create_time,modified_time,creator_id,tag) values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
	void insertQuestion(Question question);
	
	@Select("select * from question")
	List<Question> selectAll();
	
}
