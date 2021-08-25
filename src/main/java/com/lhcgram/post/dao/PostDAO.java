package com.lhcgram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lhcgram.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Post> selectPostList();

	public void postCreate(
			@Param("userId") int userId
			,@Param("userLoginId") String userLoginId
			,@Param("content") String content
			,@Param("imagePath") String imagePath);
}
