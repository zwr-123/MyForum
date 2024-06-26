package com.example.demo.bean;

import com.example.demo.enums.CommentErrorMessage;

public class CommentException extends RuntimeException{
	private String message;
	
	public CommentException(CommentErrorMessage commentErrorMessage) {
		this.message=commentErrorMessage.getMessage();
	}

	public String getMessage() {
		return message;
	}
	
}
