package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Question;

@Mapper
public interface ProfileMapper {

	@Select("select * from question where creator_id =#{id}")
	List<Question> selectMyQuestion(Integer id);
	
}
