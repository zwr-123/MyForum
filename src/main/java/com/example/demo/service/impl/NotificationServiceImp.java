package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Notification;
import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.NotificationDTO;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.NotificationService;
import com.github.pagehelper.Page;

@Service
public class NotificationServiceImp implements NotificationService {

	@Autowired
	NotificationMapper notificationMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public void insertNotification(Notification notification) {
		notificationMapper.insertOne(notification);
	}

	@Override
	public Page<NotificationDTO> selectUnRead(Integer currentId) {
		Page<Notification> notificationUnread = (Page)notificationMapper.selectUnread(currentId, 0);
		Page<NotificationDTO> arrayList = new Page<>();
		List<NotificationDTO> notificationDTOs = notificationUnread.stream().map(item -> {
			// ==1表示，被回复的是 问题
			if (item.getType() == 1) {
				NotificationDTO<Question> notificationDTO = new NotificationDTO<>();
				BeanUtils.copyProperties(item, notificationDTO);
				notificationDTO.setData(questionMapper.selectOneById(item.getParentId()));
				return notificationDTO;
			} else if (item.getType() == 2) {
				NotificationDTO<Comment> notificationDTO = new NotificationDTO<>();
				BeanUtils.copyProperties(item, notificationDTO);
				notificationDTO.setData(commentMapper.selectById(item.getParentId()));
				return notificationDTO;
			} else {
				NotificationDTO<String> notificationDTO = new NotificationDTO<>();
				BeanUtils.copyProperties(item, notificationDTO);
				notificationDTO.setData("没有数据");
				return notificationDTO;
			}
		}).collect(Collectors.toList());

		//添加通知数据
		arrayList.addAll(notificationDTOs);
		// 复制分页信息到新的对象
		BeanUtils.copyProperties(notificationUnread, arrayList);
		return arrayList;
	}

	@Override
	public void removeOne(Integer id) {
		notificationMapper.deleteOneById(id);
	}

}
