package com.lhcgram.timeline.dao;

import org.springframework.stereotype.Repository;

import com.lhcgram.post.model.Post;

@Repository
public interface TimelineDAO {

	public Post selectPostListByuserId(int userId);
}
