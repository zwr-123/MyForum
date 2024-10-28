package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	private int id;
	private int senderId;
	private String senderName;
	private int recipientId;
	private int parentId;
	private int type;
	private int status;
	private LocalDateTime gmtCreate;
}
