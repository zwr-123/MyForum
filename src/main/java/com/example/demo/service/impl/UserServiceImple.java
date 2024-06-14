package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	UserMapper um;
	/**
	 * 插入用户
	 */
	@Override
	public void insert(User user) {
		um.insert(user);
	}
	
	/**
	 * 根据id查询用户
	 */
	@Override
	public User selectByID(Integer id) {
		User user=um.selectByID(id);
		return user;
	}

	/**
	 * 根据token查询用户
	 */
	@Override
	public User selectByToken(String value) {
		User user=um.selectByToken(value);
		return user;
	}

}
