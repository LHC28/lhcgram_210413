package com.lhcgram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lhcgram.comment.bo.CommentBO;
import com.lhcgram.intercepter.PermissionInterceptor;
import com.lhcgram.like.bo.LikeBO;
import com.lhcgram.post.bo.PostBO;
import com.lhcgram.post.model.Post;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired CommentBO commentBO; 

	@RequestMapping("/create")
	public Map<String,String> postCreate(
			@RequestParam("content") String content
			,@RequestParam(value="file",required=false) MultipartFile file
			,HttpServletRequest request
			) {
		// 유저 아이디값 가져오기
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		postBO.postCreate(userId,userLoginId,content, file);
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	// 좋아요 추가 및 삭제
	@RequestMapping("/like")
	public Map<String, String> likeCheck(
			@RequestParam("postId") int postId
			,HttpServletRequest request
			){
		Map<String, String> result = new HashMap<>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId"); // 로그인 되어있는 user의 id가져오기
		boolean check = likeBO.getLikeCheck(userId, postId);
		// true : 있음 / false : 없음
		if(check==true) { // 있는 경우 없애기
			likeBO.removeMyLike(userId, postId);
			result.put("result", "success");
			return result;
		}else { // 없는 경우 생성
			likeBO.addMyLike(userId, postId);
			result.put("result", "success");
			return result;
		}
	}
	
	// 게시물 삭제
	@RequestMapping("/delete")
	public Map<String, String> delete(
			@RequestParam("postId") int postId
			,HttpServletRequest request
			){
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		Map<String, String> result = new HashMap<>();
		
		Post post = postBO.getPost(postId);
		if(post.getUserId()==userId) {
			likeBO.removeMyLike(userId, postId);
			commentBO.deleteByPostId(postId);
			postBO.delete(userId, postId);
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
				
		return result;
	}
}
