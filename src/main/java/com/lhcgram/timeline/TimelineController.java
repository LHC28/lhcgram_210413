package com.lhcgram.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhcgram.timeline.bo.ContentBO;
import com.lhcgram.timeline.domain.Content;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	// 임시용으로 하나만 띄우기
	
	@Autowired
	private ContentBO contentBO;

	@RequestMapping("/timeline_list_view")
	public String postListView(
			Model model
			,HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId==null) {
			return "redirect:/user/user_in_view";
		}
		
		// 게시글 관련 contentList 가져오기
		List<Content> contentList = contentBO.getContentList();
		
		// jsp에서 가져다 스기 위해 model 객체에 넣기.
		model.addAttribute("contentList",contentList);
		model.addAttribute("viewName", "timeline/timeline_list");
		return "template/layout";
	}
}
