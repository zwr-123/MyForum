package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Notification;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.service.NotificationService;

@Service
public class NotificationServiceImp implements NotificationService {
	
	@Autowired
	NotificationMapper notificationMapper;

	@Override
	public void insertNotification(Notification notification) {
		notificationMapper.insertOne(notification);
	}
	
}
