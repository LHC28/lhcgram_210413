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
	
	public void create(int userId, int postId, String userName, String content) {
		commentDAO.create(userId, postId, userName, content);
	}
	
	public void delete(int commentId) {
		commentDAO.delete(commentId);
	}
}
