package com.lhcgram.timeline.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.post.model.Post;
import com.lhcgram.timeline.dao.TimelineDAO;

@Service
public class TimelineBO {
	
	@Autowired
	private TimelineDAO timelineDAO;

	public Post getPostListByuserId(int userId){
		return timelineDAO.selectPostListByuserId(userId);
	}
}
