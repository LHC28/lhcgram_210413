package com.lhcgram.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lhcgram.like.model.Like;

@Repository
public interface LikeDAO {
	
	public Integer selectLikeCount(int postId);
	
	public Like selectMylike(int userId);
	
	public Like selectLikeCheck(
			@Param("userId") int userId
			,@Param("postId") int postId);
	
	public void removeMyLike(
			@Param("userId") int userId
			,@Param("postId") int postId);
	
	public void addMyLike(
			@Param("userId") int userId
			,@Param("postId") int postId);
}
