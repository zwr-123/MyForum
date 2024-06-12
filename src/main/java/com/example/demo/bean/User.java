package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String id;
	private String login;
	private String token;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}	
