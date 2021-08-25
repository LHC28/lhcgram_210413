package com.lhcgram.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.comment.dao.CommentDAO;
import com.lhcgram.comment.model.Comment;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> getCommentList(int postId){
		return commentDAO.selectCommentList(postId);
	}
}
