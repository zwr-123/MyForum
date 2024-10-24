package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.bean.Question;
import com.example.demo.bean.DTO.QuestionDTO;

@Mapper
public interface QuestionMapper {

	@Insert("insert into question(title,description,create_time,modified_time,creator_id,tag) values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
	void insertQuestion(Question question);
	
	@Select("select * from question")
	List<Question> selectAll();

	/**
	 * 根据id查询DTO
	 * @date 2024年6月17日 上午10:52:28
	 * @param id
	 * @return
	 */
	@Select("select * from question where id=#{id}")
	Question selectDtoById(Integer id);

	
	/**
	 * 根据id查询question
	 * @date 2024年6月18日 上午8:26:49
	 * @return
	 */
	@Select("select * from question where id=#{id}")
	Question selectOneById(Integer id);

	/**
	 * 更新一个问题
	 * @date 2024年6月18日 上午9:19:20
	 */
	@Update("update question set title=#{title},description=#{description},tag=#{tag},modified_time=#{modifiedTime} where id=#{id}")
	void updateOne(Question question);

	@Update("update question set view_count = view_count + 1 where id = #{id}")
	void updateByViewCount(Integer id);
	
	@Update("update question set comment_count = comment_count + 1 where id = #{id}")
	void updateComment(Integer id);
	
}
