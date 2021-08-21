package com.lhcgram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhcgram.common.EncryptUtils;
import com.lhcgram.user.bo.UserBO;
import com.lhcgram.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	@PostMapping("/is_duplicated_id")
	public Map<String, Boolean> idDuplicatedId(
			@RequestParam("loginId") String loginId
			){
		User user = userBO.getUserById(loginId);
		Map<String, Boolean> result = new HashMap<>();
		if(user==null) {
			result.put("result", false);
		}else {
			result.put("result", true);
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			){
		String encryptPassword = EncryptUtils.md5(password);
		userBO.addUser(loginId, encryptPassword, name, email);
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpServletRequest request
			){
		String encryptPassword = EncryptUtils.md5(password);
		Map<String, String> result = new HashMap<>();
		User user = userBO.getUserByloginIdAndPassword(loginId, encryptPassword);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId",user.getLoginId());
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	

	

}
