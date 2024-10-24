package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.User;

@Mapper
public interface UserMapper {

	/**
	 * 插入用户
	 * @date 2024年5月7日 上午9:57:07
	 * @param user
	 */
	@Insert("insert into user(id,login,token,createTime,updateTime) values(#{id},#{login},#{token},#{createTime},#{updateTime})")
	void insert(User user);

	/**
	 * 根据id查询用户
	 * @date 2024年5月7日 上午10:05:25
	 * @param id
	 */
	@Select("select * from user where id=#{id}")
	User selectByID(Integer id);

	
	/**
	 * 根据token查询用户
	 * @date 2024年6月13日 上午9:22:26
	 * @param value
	 * @return
	 */
	@Select("select * from user where token=#{value}")
	User selectByToken(String value);
	
	
	List<User> selectUsersByIds(List<Integer> ids);

}
