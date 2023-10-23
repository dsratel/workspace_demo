<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="#">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<title>Login page</title>
</head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<div class="container">
		<form name="loginForm" method="post" action="member/login.do" id="loginForm">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td><input type="text" class="form-control" name="id" id="id"></td>
					<td rowspan="2"><button type="button" class="btn btn-primary" id="loginBtn">로그인</button></td> 
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" class="form-control" name="pw" id="pw"></td>
				</tr>
			</table>		
		</form>
	
		<div class="row" id="signUpBox">
			<div class="col">
				<span>아직 회원이 아니신가요?</span>
				<button type="button" class="btn btn-success" id="signUpBtn">회원가입</button>
			</div>
		
		</div>
	</div>
	<script>
		$(function(){
			// css
			$("table, #signUpBox").css({"text-align" : "center", "vertical-align" : "middle"});
			
			// 로그인 버튼
			$("#loginBtn").click(function(){
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
				
			});
			
			// 회원가입 버튼
			// 회원가입 화면으로 이동
			$("#signUpBtn").click(function(){
				alert("회원가입 화면으로 이동");
				window.location.replace("${pageContext.request.contextPath}/member/signUp");
			});
			
		})	// function() end
	</script>
</body>
</html>