package com.example.demo.bean.DTO;

import java.time.LocalDateTime;

import com.example.demo.bean.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
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
	private User user;
}
