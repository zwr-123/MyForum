package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Comment;
import com.example.demo.bean.DTO.CommentDTO;

public interface CommentService {

	/**
	 * 插入一条评论
	 * @date 2024年6月20日 上午9:47:52
	 * @param comment
	 */
	void insertOne(Comment comment);

	
	/**
	 * 查询评论
	 * @date 2024年10月22日 下午4:14:52
	 */
	List<Comment>  selectByQIdAndType(int id,int type);


	List<CommentDTO> selectCommentDTO(Integer id, int type);


	/**
	 * 查询二级评论
	 * @date 2024年10月22日 下午4:14:52
	 */
	List<CommentDTO> selectComment2(int i, int j);

}
