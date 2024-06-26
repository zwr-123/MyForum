package com.example.demo.enums;

import lombok.Data;

/**
 * 自身是 问题回复 还是 评论回复
 */

public enum commentType {
	replyForQuestion(1),replyForComment(2);
	
	private final Integer type;
	private commentType(Integer type) {
		this.type=type;
	}
	
	public Integer getType() {
		return type;
	}
}
