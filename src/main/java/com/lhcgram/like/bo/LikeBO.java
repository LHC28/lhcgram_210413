package com.lhcgram.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.like.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	public Integer getLikeCount(int postId) {
		return likeDAO.selectLikeCount(postId);
	}
	
	public boolean getMyLike(int userId) {
		 if(likeDAO.selectMylike(userId)==null) {
			 return false;
		 }else {
			 return true;
		 }
	}
}
