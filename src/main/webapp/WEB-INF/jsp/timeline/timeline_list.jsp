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
				<a href="#" onclick="return false" id="likeBtn">
					<img src="/static/images/heart-icon1.png" alt="" height="20px" width="20px" id="like1">
					<img src="/static/images/heart-icon2.png" alt="" height="20px" width="20px" id="like2" class="d-none">
				</a>
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
		
		
		// 업로드 하기
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
		$('#likeBtn').on('click',function(e){
			if($('#like1').hasClass('d-none')){
				$('#like1').removeClass('d-none');
				$('#like2').addClass('d-none');
			}else{
				$('#like1').addClass('d-none');
				$('#like2').removeClass('d-none');
			}
		})
		
		
		
		
		
	});
</script>