package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
	private Integer id;
	private String title;
	private String description;
	private LocalDateTime createTime;
	private LocalDateTime modifiedTime;
	private Integer  creatorId;
	private Integer  commentCount;
	private Integer viewCount;
	private Integer likeCount;
	private String tag;
}
