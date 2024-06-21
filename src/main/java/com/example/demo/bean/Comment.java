package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Integer id;
	private Integer parentId;
	private Integer type;
	private Integer commentorId;
	private LocalDateTime gmtCreate;
	private LocalDateTime gmtModified;
	private Integer likeCount;
	private String content;
}
