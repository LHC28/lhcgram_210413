<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header" class="d-flex align-items-center justify-content-between">
	<h3 class="ml-4">Lhcgram</h3>
	<div class="d-flex">
		<c:if test="${not empty userId }">
			<div class="mr-5">가나다 님 안녕하세요!</div>
			<a href="/user/sign_out" class="mr-4">로그아웃</a>
		</c:if>
		<c:if test="${empty userId }">
			<a href="/user/sign_in_view" class="mr-4">로그인</a>
		</c:if>
	</div>
</header>