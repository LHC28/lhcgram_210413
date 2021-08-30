package com.lhcgram.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhcgram.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;

	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("postId") int postId
			,@RequestParam("content") String content
			,HttpServletRequest request
			){
		// 댓글 추가시 필요한 항목 : userId, postId, userName, content
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		int userId = (int)session.getAttribute("userId");
		// session과 request를 헷갈리지 말자...
		commentBO.create(userId, postId, userName, content);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}
	
	@PostMapping("/delete")
	Map<String, String> delete(
			@RequestParam("commentId") int commentId
			){
		commentBO.deleteById(commentId);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}
}
