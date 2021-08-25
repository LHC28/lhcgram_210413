package com.lhcgram.like.dao;

import org.springframework.stereotype.Repository;

import com.lhcgram.like.model.Like;

@Repository
public interface LikeDAO {
	
	public Integer selectLikeCount(int postId);
	
	public Like selectMylike(int userId);
}
