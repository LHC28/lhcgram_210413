<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="contentTitle d-flex justify-content-between align-items-center mt-4">
			<h4 class="ml-3">lhcgram</h4>
			<a href="#" class="mr-3"><img src="/static/images/more-icon.png" alt="" width="30px" height="30px"></a>
		</div>
		<div class="d-flex justify-content-center mt-3">
			<img src="https://cdn.pixabay.com/photo/2021/08/13/14/23/animal-6543231_960_720.jpg" alt="" width="300px" height="200px">
		</div>
		<div class="d-flex justify-content-center mt-2">
			<div class="contentLike d-flex align-items-center">
				<img src="/static/images/heart-icon1.png" alt="" height="20px" width="20px">
				<span class="ml-2"><b>좋아요 11개</b></span>
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class="contentContent">
				<span><b>lhc</b> 동물이 한 마리 있습니다.아주 멋진 돼지 한 마리에요. 마치 저를 보는 것 같군요</span>
			</div>
		</div>
		<div class="d-flex justify-content-center align-items-center">
			<div class="contentCommentTitle d-flex align-items-center mt-2">
				<span><b>댓글</b></span>
			</div>
		</div>
		<div class="d-flex justify-content-center align-items-center mt-2">
			<div class="contentComment d-flex align-items-center">
				<div class="commentId">abcdefg</div>
				<div class="comment">이야 정말 당신을 닮은 동물이군요!!!</div>
			</div>
		</div>
		<div class="d-flex mt-3">
			<input type="text" id="comment" class="form-control" placeholder="댓글 내용을 입력해주세요.">
			<input type="button" class="btn commentBtn text-primary font-weight-bold" value="게시"> 
		</div>
	</div>
</div>