<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="timelineBox d-flex justify-content-center">
	<div class="timeline">
		<%-- 선생님 코드 가져옴....textarea안에 button넣기 찾아봤는데 안 나온다...색 씌워보니 밑칸에 붙이기식으로 하면 될듯 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		
	<%-- 여기서부터 게시글 반복. --%>
	<c:forEach var="content" items="${contentList}">
		<div class="contentTitle d-flex justify-content-between align-items-center mt-4">
			<h4 class="ml-3 mt-1">${content.post.userName}</h4>
			<c:if test="${userId eq content.post.userId}">
				<a href="#" class="more-btn" data-toggle="modal" data-target="#moreModal" data-post-id="${content.post.id}">
					<img src="/static/images/more-icon.png" alt="" width="30px" height="30px">
				</a>
			</c:if>
			
		</div>
		<%-- 사진 부분 만약 없으면 출력 안 하는 것으로. --%>
		<c:if test="${not empty content.post.imagePath}">
		<div class="d-flex justify-content-center mt-3">
			<img src="${content.post.imagePath}" alt="" width="300px" height="200px">
		</div>
		</c:if>
		<div class="d-flex justify-content-center mt-2">
			<div class="contentLike d-flex align-items-center">
				<%-- 좋아요 --%>
				
				<%-- 로그인 한 userId와 게시물의 id가 필요하다. --%>
				<a href="#" onclick="return false" class="likeBtn" data-post-id="${content.post.id }" data-user-id="${userId}">
				<%-- return false는 클릭시 창이 위로가는 것을 방지. --%>
				<c:if test="${content.likeClick eq true }">
					<img src="/static/images/heart-icon2.png" alt="" height="20px" width="20px" class="like2">
				</c:if>
				<c:if test="${content.likeClick eq false }">
					<img src="/static/images/heart-icon1.png" alt="" height="20px" width="20px" class="like1">
				</c:if>
				</a>
				<span class="ml-2"><b>좋아요 ${content.like }개</b></span>
				
				
				
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class="contentContent">
				<span><b>${content.post.userName }</b> ${content.post.content}</span>
			</div>
		</div>
		<div class="d-flex justify-content-center align-items-center">
			<div class="contentCommentTitle d-flex align-items-center mt-2">
				<span class="ml-2"><b>댓글</b></span>
			</div>
		</div>
		<%-- 댓글 반복 --%>
		<c:forEach var="comment" items="${content.commentList }">
		<div class="d-flex justify-content-center align-items-center mt-2">
			<div class="contentComment d-flex align-items-center">
				<div class="commentId">${comment.userName }</div>
				<div class="comment">${comment.content}</div>
			</div>
			<c:if test="${userName eq comment.userName }">
				<a href="#" class="commentDelBtn" onclick="return false" data-comment-id="${comment.id}">
					<img src = "/static/images/cancelBtn.png" alt="" width="15px" heigth="15px">
				</a>
			</c:if>
		</div>
		</c:forEach>
		<c:if test="${not empty userId}">
			<div class="d-flex mt-3">
				<input type="text" id="commentText${content.post.id}" class="comment form-control mr-2" placeholder="댓글 내용을 입력해주세요.">
				<input type="button" class="btn commentBtn text-primary font-weight-bold" data-post-id="${content.post.id }" value="게시"> 
			</div>
		</c:if>
	</c:forEach>
	</div>
</div>

<div class="modal" id="moreModal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm modal-dialog-centered" role="document">
		<div class="modal-content">
			<%-- Modal 창 안에 내용 넣기 --%>
			<div class="w-100">
				<div class="my-3 text-center">
					<a href="#" class="del-post d-block">삭제하기</a><%-- 클릭할 수 있는 영역을 넓히기 위해 d-block --%>
				</div>
				<div class="border-top py-3 text-center">
					<%-- data-dismiss: 모달창 닫힘 --%>
					<a href="#" class="cancel d-block" data-dismiss="modal">취소</a> <%-- 클릭할 수 있는 영역을 넓히기 위해 d-block --%>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		
		// 파일 업로드 이미지 클릭 -> 사용자가 파일 업로드를 할 수 있게 해줌. 
		$('#fileUploadBtn').on('click',function(e){
			e.preventDefault(); // a 태그의 기본 동작을 중단
			$('#file').click(); // input file태그를 클릭한 것과 같은 동작
		});
		
		// 유효성 확인
		$('#file').on('change',function(e){
			let name = e.target.files[0].name; // 이미지 이름찍기
			
			// 이미지 확장자 validation
			let extention = name.split('.');
			if(extention.length<2 || (extention[extention.length-1] != 'gif'
					&& extention[extention.length-1] != 'png'
					&& extention[extention.length-1] != 'jpg'
					&& extention[extention.length-1] != 'jpeg')){
				alert("이미지 파일만 업로드할 수 있습니다.");
				$(this).val(''); // input에 들어간 파일을 없애주는 역할.
				return;
			}
			$('#fileName').text(name); // 이미지 파일 등록시 div안에 name을 가져온다.
		});
		
		
		// 게시글 업로드 하기
		$('#writeBtn').on('click', function(e){
			let content = $('#writeTextArea').val();
			if(content.length<1){ // 1보다 작은건 내용이 없다는 것.
				alert("내용을 입력해주세요.");
				return;
			}
			
			var formData = new FormData();
			// ajax data가져오는 방식과 같은듯.
			// 파일 가져오기
			formData.append('file',$('#file')[0].files[0]);
			// 내용 가져오기
			formData.append('content', content);
			
			$.ajax({
				type: 'post'
				,url: '/post/create'
				,data: formData
				,enctype: 'multipart/form-data'
				,processData: false
				,contentType: false // 파일 업로드를 위한 필수 설정
				,success: function(data){
					if(data.result=='success'){
						alert("게시물 등록 성공");
						location.reload(); // 새로고침해서 게시물 올린거 보이게
					}
				},error: function(e){
					alert("error : " +e);
				}
			});
			
		});
		
		// 좋아요 버튼 눌렀을 때
		$('.likeBtn').on('click',function(e){
			let userId = $(this).data('user-id');
			let postId = $(this).data('post-id');
			// 취소시 해당 like DB 삭제 반영, 좋아요시 등록해야 한다.
			// 좋아요 취소.
			$.ajax({
				type:'post'
				,url: '/post/like'
				,data: {"userId":userId, "postId":postId}
				,success: function(data){
					if(data.result=="success"){
						location.reload();
					}else{
						alert("좋아요 실패... 관리자에게 문의해주세요.");
					}
				},error: function(e){
					alert("###########error : "+error);
				}
			});
		})
		
		// 댓글 달기
		$('.commentBtn').on('click',function(e){
			e.preventDefault();
			let postId = $(this).data('post-id');
			// 좋아요처럼 클래스로 값이 가져와지지 않아 id값을 가지고 댓글칸의 내용을 가져오도록 함..
			let comment = $('#commentText'+postId).val();
			// 댓글 추가시 필요한 항목 : userId, postId, userName, content
			$.ajax({
				type: 'post'
				,data: {"postId":postId, "content":comment}
				,url: '/comment/create'
				,success: function(data){
					if(data.result=='success'){
						location.reload();
					}else{
						alert("댓글 등록 실패... 관리자에게 문의해주세요.");
					}
				},error: function(e){
					alert("에러발생 : "+e);
				}
			});
			
		});
		
		//댓글 삭제
		$('.commentDelBtn').on('click',function(e){
			let commentId = $(this).data('comment-id');
			
			$.ajax({
				type: 'post'
				,url: '/comment/delete'
				,data: {"commentId":commentId}
				,success: function(data){
					location.reload();
				},error: function(e){
					alert("에러 발생 : "+e);
				}
			});
		});
		
		// modal 창의 삭제하기 클릭시
		// 더보기 > 글삭제 클릭
		$('#moreModal .del-post').on('click', function(e) {
			e.preventDefault();
			
			let postId = $('.more-btn').data('post-id');
			
			$.ajax({
				type:'post'
				,url:'/post/delete'
				,data: {"postId":postId}
				,success: function(data) {
					if (data.result == 'success') {
						location.reload();
					}else{
						alert("자신의 게시물만 삭제 가능합니다.");
					}
				},error: function(e) {
					alert("삭제하기 에러 : "+e)
				}
			});
		});
		
	});
</script>