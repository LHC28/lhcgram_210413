package com.lhcgram.like.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.intercepter.PermissionInterceptor;
import com.lhcgram.like.dao.LikeDAO;
import com.lhcgram.like.model.Like;

@Service
public class LikeBO {

	private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 개수 가져오기
	public Integer getLikeCount(int postId) {
		return likeDAO.selectLikeCount(postId);
	}
	
	// 내가 좋아요 눌렀는지 확인하기
	public boolean getMyLike(int userId, int postId) {
		 if(likeDAO.selectMylike(userId, postId)==null) {
			 return false; // 안 누른 상태
		 }else {
			 return true; // 누른 상태
		 }
	}
	
	
	public boolean getLikeCheck(int userId, int postId) {
		Like check = likeDAO.selectLikeCheck(userId, postId);
		if(check==null) {
			return false;
		}else {
			return true;
		} 
	}
	
	// 나의 좋아요 지우기
	public void removeMyLike(int userId, int postId) {
		likeDAO.removeMyLike(userId, postId);
	}
	
	// 나의 좋아요 추가하기
	public void addMyLike(int userId, int postId) {
		likeDAO.addMyLike(userId, postId);
	}
}
