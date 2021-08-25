package com.lhcgram.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhcgram.comment.bo.CommentBO;
import com.lhcgram.comment.model.Comment;
import com.lhcgram.like.bo.LikeBO;
import com.lhcgram.post.bo.PostBO;
import com.lhcgram.post.model.Post;
import com.lhcgram.timeline.domain.Content;

@Service
public class ContentBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 게시물 가져오기
	public List<Content> getContentList(){
		// 한번에 리턴할 항목
		List<Content> contentList = new ArrayList<>();
		
		// 게시물 가져오기
		List<Post> postList = new ArrayList<>();
		postList = postBO.getPostList(); 
		
		//반복 (post 갯수만큼)
		for(Post post : postList) {
			Content content = new Content(); // content객체를 만들어 post, 좋아요, 댓글을 가져와서 content에 집어넣고 리스트에 넣는다.
			content.setPost(post); // 게시글 넣기
			
			// 좋아요 가져오기
			Integer likeCount = likeBO.getLikeCount(post.getId()); // post의 id를 가지고 게시글과 관련된 모든 좋아요의 갯수를 가져온다.
			if(likeCount==null) {
				likeCount = 0;
			}
			content.setLike(likeCount);
			
			// 내가 좋아요 눌렀는지 아닌지 확인하기
			// 접속한 내 아이디를 가지고 좋아요를 눌렀는지 확인하는 과정.
			boolean likeCheck = likeBO.getMyLike(post.getUserId());
			content.setLikeClick(likeCheck); // 있으면 true 없으면 false
			// 댓글 가져오기
			List<Comment> commentList = new ArrayList<>();
			commentList = commentBO.getCommentList(post.getId());
			
			content.setCommentList(commentList);
			
			contentList.add(content);
			
		}
		
		return contentList;
		
	}
}
