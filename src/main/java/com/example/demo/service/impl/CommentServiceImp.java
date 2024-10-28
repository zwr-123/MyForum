package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.Comment;
import com.example.demo.bean.CommentException;
import com.example.demo.bean.User;
import com.example.demo.bean.DTO.CommentDTO;
import com.example.demo.enums.CommentErrorMessage;
import com.example.demo.enums.commentType;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CommentService;
import com.example.demo.util.BaseContext;

@Service
public class CommentServiceImp implements CommentService{
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	@Transactional
	public void insertOne(Comment comment) {

		if(comment.getParentId() == null || comment.getParentId() == 0) {
			throw new CommentException(CommentErrorMessage.TARGET_NOT_FOUND);
		}
		
		if(comment.getType() != 1 && comment.getType() !=2) {
			throw new CommentException(CommentErrorMessage.TYPE_NOT_FOUND);
		}
		
		
		if(comment.getContent() == null || "".equals(comment.getContent()) || " ".equals(comment.getContent())) {
			throw new CommentException(CommentErrorMessage.CONTENT_IS_NULL);
		}
		
		LocalDateTime now = LocalDateTime.now();
		comment.setGmtCreate(now);
		comment.setGmtModified(now);
		comment.setCommentorId(BaseContext.getCurrentId());
		commentMapper.insertOne(comment);
		questionMapper.updateComment(comment.getParentId());
			
	}

	/**
	 * 查询评论
	 * @date 2024年10月22日 下午4:14:52
	 */
	@Override
	public List<Comment> selectByQIdAndType(int id, int type) {
		return commentMapper.selectByQIdAndType(id,type);
		
	}

	
	@Override
	public List<CommentDTO> selectCommentDTO(Integer id, int type) {
		List<Comment> comments = commentMapper.selectByQIdAndType(id,type);
		if(comments.size()==0) {
			return new ArrayList<>();
		}
		
		//获取所有用户id，并从set放到list中
		Set<Integer> CommentorsId = comments.stream().map(comment -> comment.getCommentorId()).collect(Collectors.toSet());
		List<Integer> userIds=new ArrayList<>();
		userIds.addAll(CommentorsId);
		
		//把用户信息存入map，下面的代码根据id查找用户
		List<User> users = userMapper.selectUsersByIds(userIds);
		Map<Integer,User> usersMap=users.stream().collect(Collectors.toMap(user -> user.getId() , user -> user));
		
		//组装DTO对象
		List<CommentDTO> commentsDTOList=comments.stream().map(comment -> {
			CommentDTO commentDTO=new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			commentDTO.setUser(usersMap.get(comment.getCommentorId()));
			return commentDTO;
		}).collect(Collectors.toList());
		
		
		commentsDTOList.forEach(System.out :: println);
		return commentsDTOList;
	}

	
	/**
	 * 查询二级评论
	 */
	@Override
	public List<CommentDTO> selectComment2(int i, int j) {
		commentMapper.selectByQIdAndType(i, j);
		return null;
	}

	@Override
	public Comment selectComment(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.selectById(id);
	}
	
}
