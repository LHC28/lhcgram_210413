package com.lhcgram.timeline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhcgram.post.model.Post;
import com.lhcgram.timeline.bo.TimelineBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;

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
		
		Post postList = timelineBO.getPostListByuserId(userId);
		
		model.addAttribute("viewName", "timeline/timeline_list");
		return "template/layout";
	}
}
