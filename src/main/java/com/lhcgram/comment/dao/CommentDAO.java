package com.lhcgram.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lhcgram.comment.model.Comment;

@Repository
public interface CommentDAO {

	public List<Comment> selectCommentList(int postId);
}
