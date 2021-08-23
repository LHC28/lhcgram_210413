package com.lhcgram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lhcgram.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;

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
}
