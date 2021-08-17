<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="loginView d-flex justify-content-center">
	<div>
		<h3 class="mt-2"><b>회원가입</b></h3>
		<div class="signUpBox d-flex justify-content-center align-items-center">
			<div class="signUpBox1">
				<div>ID</div>
				<div class="d-flex">
					<input type="text" class="form-control col-6" id="loginId" placeholder="ID를 입력해주세요.">
					<input type="button" class="btn btn-primary ml-2" id="idCheck" value="중복확인">
				</div>
				<div class="small text-danger font-weight-bold d-none" id="duplicatedIdCheck">사용중인 아이디입니다.</div>
				<div class="small text-success font-weight-bold d-none" id="idCheckOk">사용가능한 아이디입니다.</div>
				<div class="mt-2 mb-1">password</div>
				<input type="password" id="password" class="form-control col-7">
				<div class="mt-2 mb-1">confirm password</div>
				<input type="password" id="conformPassword" class="form-control col-7">
				<div class="mt-2 mb-1">이름</div>
				<input type="text" id="name" class="form-control col-8">
				<div class="mt-2 mb-1">이메일</div>
				<input type="text" id="email" class="form-control col-8">
				<div class="d-flex justify-content-center mt-3">
					<input type="button" class="btn btn-primary" id="signUpBtn" value="회원가입">
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		// id중복 확인
		$('#idCheck').on('click',function(e){
			let loginId = $('#loginId').val().trim();
			if(loginId==''){
				alert("아이디를 입력해주세요.");
				return;
			}
			
			$.ajax({
				type:'post'
				,url:'/user/is_duplicated_id'
				,data:{'loginId':loginId}
				,success: function(data){
					if(data.result==true){
						$('#duplicatedIdCheck').removeClass('d-none');
						$('#idCheckOk').addClass('d-none');
					}else{
						$('#duplicatedIdCheck').addClass('d-none');
						$('#idCheckOk').removeClass('d-none');
					}
				},error: function(e){
					alert("아이디 중복확인 실패...");
				}
			});
		});
		
		$('#signUpBtn').on('click',function(e){
			
			let loginId = $('#loginId').val().trim();
			if(loginId==''){
				alert("아이디를 입력하세요.");
				return;
			}
			let password = $("#password").val();
			if(password==''){
				alert("비밀번호를 입력하세요.");
				return;
			}
			let confirmPassword = $('#conformPassword').val();
			if(confirmPassword ==''){
				alert("비밀번호 확인을 입력하세요.");
				return;
			}
			if(password!=confirmPassword){
				alert("비밀번호와 확인이 일치하지 않습니다.");
				return;
			}
			let name = $("#name").val().trim();
			if(name==''){
				alert("이름을 입력하세요");
				return;
			}
			let email = $("#email").val().trim();
			if(email==''){
				alert("이메일을 입력하세요.");
				return;
			}
			if($('#idCheckOk').hasClass('d-none')){
				alert("아이디 중복확인.");
				return;
			}
			
			$.ajax({
				type:'post'
				,url:'/user/sign_up'
				,data:{'loginId':loginId,'password':password,'name':name,'email':email}
				,success: function(data){
					alert("회원가입 성공");
					location.href="/user/sign_in_view";
				},error: function(e){
					alert("회원가입 실패");
				}
			});
		});
	});
</script>