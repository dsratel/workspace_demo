<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="data:image/x-icon;">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>login page</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<div class="container">
		<form name="loginForm" method="post" action="/member/login.do" id="loginForm">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td><input type="text" class="form-control" name="id" id="id"></td>
					<td rowspan="2"><button type="button" class="btn btn-primary" id="loginBtn" onclick="login()">로그인</button></td> 
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" class="form-control" name="pw" id="pw" onkeyup="enterkey()"></td>
				</tr>
			</table>	
			<c:if test="${!requestURI.equals('') }">
				<input type="hidden" name="requestURI" value="${requestURI }">
			</c:if>	
		</form>
		<div class="row" id="BtnBox">
			<div class="col">
				<span>아직 회원이 아니신가요?</span>
				<button type="button" class="btn btn-success" id="signUpBtn">회원가입</button>
			</div>
			<div class="col">
				<button type="button" class="btn btn-warning" id="listBtn">글 미리보기</button>
			</div>
		</div>
	</div>
	<script>
		$(function(){
			// css
			$("table, #BtnBox").css({"text-align" : "center", "vertical-align" : "middle"});
			
			// 회원가입 버튼
			$("#signUpBtn").click(function(){
				alert("회원가입 화면으로 이동");
				window.location.replace("/member/signUp");
			});
			
			// 글 미리보기 버튼
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});
		})	// function() end
		
		// 비밀번호에서 엔터
		function enterkey() {
			if(window.event.keyCode == 13) login();
		}
		
		// 로그인 버튼
		function login() {
			// ID 유효성 검사
			if($("#id").val().length < 5) {
				alert("ID를 5자 이상 입력해주세요.");
				return ;
			}
			
			if($("#pw").val().length < 8) {
				alert("비밀번호를 8자리 이상 입력해주세요.");
				return ;
			}
			
			$("#loginForm").submit();	
		}
		
	</script>
</body>
</html>