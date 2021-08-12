package com.lhcgram.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhcgram.test.bo.TestBO;

@Controller
public class test {

	@Autowired
	private TestBO testBO;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "Hello world";
	}
	
	@RequestMapping("/testDB")
	public Map<String, Object> testDB(){
		Map<String, Object> result = testBO.getDB();
		return result;
	}
}
