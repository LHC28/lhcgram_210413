package com.lhcgram.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {

	public void postCreate(
			@Param("userId") int userId
			,@Param("userLoginId") String userLoginId
			,@Param("content") String content
			,@Param("imagePath") String imagePath);
}
