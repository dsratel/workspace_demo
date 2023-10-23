<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form name="loginForm" method="post" action="member/login.do" id="loginForm">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td><input type="text" class="form-control" name="id" value="${dto.id }"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.name }"></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.nickname }"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.address }"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.phone }"></td>
				</tr>
				<tr>
					<td>회원상태</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.status }"></td>
				</tr>
				<tr>
					<td>파일번호</td>
					<td><input type="text" class="form-control" name="pw" value="${dto.fileno }"></td>
				</tr>
			</table>		
		</form>
	</div>
</body>
</html>