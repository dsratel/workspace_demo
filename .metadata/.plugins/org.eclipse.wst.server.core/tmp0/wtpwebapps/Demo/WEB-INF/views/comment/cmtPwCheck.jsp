<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="data:image/x-icon;">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>comment password check</title>
</head>
<body>
	<table class="table">
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" class="input-form" id="pw">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="pwInit()">댓글 비밀번호 입력</button>
			</td>
		</tr>
	</table>

  <!-- Custom for this page -->
  <script type="text/javascript" src="/resources/js/custom/comment/comment.js"></script>

</body>
</html>