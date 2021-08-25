package com.lhcgram.timeline.domain;

import java.util.List;

import com.lhcgram.comment.model.Comment;
import com.lhcgram.post.model.Post;

public class Content {
	// post 1개
	private Post post;
	// post - 좋아요 N개
	private int like;
	// post - 댓글 N개
	private boolean likeClick;
	
	public boolean isLikeClick() {
		return likeClick;
	}
	public void setLikeClick(boolean likeClick) {
		this.likeClick = likeClick;
	}
	private List<Comment> commentList;
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}
