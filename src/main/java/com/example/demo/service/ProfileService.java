package com.example.demo.service;

import com.example.demo.bean.Question;
import com.github.pagehelper.Page;

public interface ProfileService {
	public Page<Question> selectMyQuestion();
}
