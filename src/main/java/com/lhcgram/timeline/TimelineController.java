package com.lhcgram.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhcgram.timeline.model.Post;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

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
		
		List<Post> postList = postBO.getPostListByuserId(userId);
		
		model.addAttribute("viewName", "timeline/timeline_list");
		return "template/layout";
	}
}
