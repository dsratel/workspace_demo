<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="data:image/x-icon;">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>master home page</title>
</head>
<body>
	<div class="container">
		<h1>[관리자 페이지]</h1>
		<div class="row">
			<div class="col section" id="memberDiv">
				<h2>회원 관리</h2>
				<img src="/resources/image/member.png" onclick="toPage('member')">
			</div>
			<div class="col section" id="boardDiv">
				<h2>게시글 관리</h2>
				<img src="/resources/image/board.png" onclick="toPage('board')">
			</div>
			<div class="col section" id="commentDiv">
				<h2>댓글 관리</h2>
				<img src="/resources/image/comment.png" onclick="toPage('comment')">
			</div>
		</div>
	</div>
	<script>
		$(function(){
			// css
			$("div.container").css({"margin-top":"20px"});
			$("div.section").css({"text-align":"center","margin-top":"20px"});
			$("img").css({"width":"100%", "cursor":"pointer"});
		/* 	$("#memberDiv").css({"background-image":"url('/resources/image/member.png')","background-size":"cover"});
			$("#boardDiv").css({"background-image":"url('/resources/image/board.png')","background-size":"cover"});
			$("#commentDiv").css({"background-image":"url('/resources/image/comment.png')","background-size":"cover"}); */
		
		})	// function() end
		
		// 페이지 이동
		function toPage(opt) {
			switch (opt) {
			case 'member':
				window.location.replace("/master/toMemberList");
				break;
			case 'board':
				window.location.replace("/master/toBoardList");
				break;
			case 'comment':
				window.location.replace("/master/toCommentList");
				break;
			}
		}
	</script>
</body>
</html>