package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Notification;

@Mapper
public interface NotificationMapper {

	@Insert("insert into notification(sender_id,sender_name,recipient_id,type,parent_id,rel_question_id,gmt_create) values(#{senderId},#{senderName},#{recipientId},#{type},#{parentId},#{relQuestionId},#{gmtCreate})")
	void insertOne(Notification notification);

	@Select("select * from notification where recipient_id=#{currentId} and status=#{i}")
	List<Notification> selectUnread(Integer currentId, int i);

	@Delete("delete from notification where id=#{id}")
	void deleteOneById(Integer id);
	

}
