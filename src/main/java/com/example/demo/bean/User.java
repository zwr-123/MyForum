package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String login;
	private String token;
	private String picUrl;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}	
