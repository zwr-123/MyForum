package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Comment;

@Mapper
public interface CommentMapper {

	@Insert("insert into comment(parent_id,type,commentor_id,gmt_create,gmt_modified,content) values(#{parentId},#{type},#{commentorId},#{gmtCreate},#{gmtModified},#{content})")
	void insertOne(Comment comment);

	@Select("select * from comment where parent_id=#{id} and type=#{type}")
	List<Comment> selectByQIdAndType(int id, int type);
	
}	
