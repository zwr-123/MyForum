package com.example.demo.bean;

import lombok.Data;

@Data
public class QuestionException extends RuntimeException {
	Question question;

	public QuestionException() {
	}

	public QuestionException(String message, Question question) {
		super(message);
		this.question = question;
	}

}
