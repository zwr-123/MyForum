package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.QuestionMapper;

@SpringBootTest
class ForumApplicationTests {
	@Autowired
	QuestionMapper questionMapper;

	@Test
	void contextLoads() {
	}

}
