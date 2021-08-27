package com.lhcgram.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lhcgram.comment.model.Comment;

@Repository
public interface CommentDAO {

	public List<Comment> selectCommentList(int postId);
	
	public void create(
			@Param("userId") int userId
			,@Param("postId") int postId
			,@Param("userName") String userName
			,@Param("content") String content);
	
	public void delete(int conmmentId);
}
