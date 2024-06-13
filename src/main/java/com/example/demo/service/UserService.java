package com.example.demo.service;

import com.example.demo.bean.User;

public interface UserService {

	/**
	 * 插入用户信息
	 * @date 2024年5月7日 上午9:42:30
	 * @param user
	 */
	void insert(User user);

	
	/**
	 * 根据id查询用户信息
	 * @date 2024年5月7日 上午10:04:31
	 * @param id
	 * @return
	 */
	User selectByID(String id);


	/**
	 * 根据Token查询用户信息
	 * @date 2024年6月13日 上午9:19:59
	 * @param value
	 */
	User selectByToken(String value);

}
