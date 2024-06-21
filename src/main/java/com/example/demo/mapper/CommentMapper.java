package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.Comment;

@Mapper
public interface CommentMapper {

	@Insert("insert into comment(parent_id,type,commentor_id,gmt_create,gmt_modified,content) values(#{parentId},#{type},#{commentorId},#{gmtCreate},#{gmtModified},#{content})")
	void insertOne(Comment comment);
	
}	
