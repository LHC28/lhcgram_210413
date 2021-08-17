package com.lhcgram.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lhcgram.user.model.User;

@Repository
public interface UserDAO {

	public User getUserById(String loginId);
	
	public void insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email
			);
	
	public User selectUserByloginIdAndPassword(
			@Param("loginId") String loginId
			,@Param("password") String password);
}
