package com.example.demo.enums;

/**
 * 评论错误信息
 */
public enum CommentErrorMessage {
	QUESTION_NOT_FOUND("问题没有找到"),
	COMMENT_NOT_FOUND("评论没有找到"),
	TARGET_NOT_FOUND("没有选中问题或评论"),
	TYPE_NOT_FOUND("评论类型错误"),
	USER_NOT_LOGIN("用户未登录"),
	CONTENT_IS_NULL("评论内容不能为空");
	
	
	private final String message;
	
	private CommentErrorMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
