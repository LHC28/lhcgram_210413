package com.lhcgram.post.bo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lhcgram.common.FileManagerService;
import com.lhcgram.post.dao.PostDAO;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;

	public void postCreate(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		if(file!=null) {
			try {
				imagePath = fileManagerService.saveFile(userLoginId, file);
			} catch (IOException e) {
				logger.error("######## 에러 확인 : " + e.getMessage());
			}
		}
		
		postDAO.postCreate(userId, userLoginId, content, imagePath);
	}
}
