package com.lhcgram.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lhcgram.common.FileManagerService;
import com.lhcgram.post.dao.PostDAO;
import com.lhcgram.post.model.Post;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// 게시물 하나 가져오기
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	// 게시물 여러 개 가져오기
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}

	// 게시물 추가하기
	public void postCreate(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		if(file!=null) {
			try {
				imagePath = fileManagerService.saveFile(userLoginId, file);
				System.out.println(imagePath);
			} catch (IOException e) {
				logger.error("######## 에러 확인 : " + e.getMessage());
			}
		}
		
		postDAO.postCreate(userId, userLoginId, content, imagePath);
	}
	
	// 게시물 삭제하기
	public void delete(int userId, int postId) {
		postDAO.delete(userId, postId);
	}
}
