package com.example.demo.bean.DTO;

import java.time.LocalDateTime;

import com.example.demo.bean.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO<T> {
	private int id;
	private int senderId;
	private String senderName;
	private int recipientId;
	private int parentId;
	private int relQuestionId;
	private int type;
	private int status;
	private LocalDateTime gmtCreate;
	private T data;
}
