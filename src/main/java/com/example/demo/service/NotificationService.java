package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Notification;
import com.example.demo.bean.DTO.NotificationDTO;
import com.github.pagehelper.Page;


public interface NotificationService {

	void insertNotification(Notification notification);

	Page<NotificationDTO> selectUnRead(Integer currentId);

	void removeOne(Integer id);

}
