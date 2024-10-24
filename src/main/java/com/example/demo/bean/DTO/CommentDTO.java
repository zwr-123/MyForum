package com.example.demo.bean.DTO;

import java.time.LocalDateTime;

import com.example.demo.bean.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	private Integer id;
	private Integer parentId;
	private Integer type;
	private Integer commentorId;
	private LocalDateTime gmtCreate;
	private LocalDateTime gmtModified;
	private Integer likeCount;
	private String content;
	User user;
}
