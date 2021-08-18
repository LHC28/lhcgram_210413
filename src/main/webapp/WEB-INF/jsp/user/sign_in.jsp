<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="loginView d-flex justify-content-center">
	<div>
		<h3 class="mt-2"><b>로그인</b></h3>
		<div class="loginBox d-flex justify-content-center">
			<div class="d-flex align-items-center justify-content-center">
				<div class="ml-5">
					<div class="input-group mb-3">
					  <span class="input-group-text">@</span>
					  <input type="text" class="form-control col-8" id="loginId" placeholder="Username">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">@</span>
						<input type="password" class="form-control col-8" id="password" placeholder="ㆍㆍㆍㆍ">
					</div>
					<a href="/user/sign_up_view" class="btn btn-dark">회원가입</a>
					<input type="button" class="btn btn-primary ml-3" id="loginBtn" value="로그인">
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		$('#loginBtn').on('click',function(e){
			
			// validation
			let loginId = $('#loginId').val().trim();
			if(loginId==''){
				alert("아이디를 입력하세요.");
				return;
			}
			
			let password = $('#password').val().trim();
			if(password==''){
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			$.ajax({
				type:'post'
				,url:'/user/sign_in'
				,data:{'loginId':loginId,'password':password}
				,success: function(data){
					if(data.result=="success"){
						alert("성공");
						location.href="/post/post_list_view"	
					}else{
						alert("아이디 또는 비밀번호가 틀렸습니다.");
					}
				},error: function(e){
					alert("로그인에 실패하였습니다.");
				}
			});
			
		});
	});
</script>