<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>article list</title>
</head>
<body>
	<h1>글 목록 - 권한에 상관 없이 모두 열람 가능</h1>
	<div class="container">
		<!-- Article List -->
		<div class="searchTab">
			<button class="btn btn-warning" id="writeBtn">글 작성</button>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>게시판 종류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>				
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${dto.category }</td>
						<td><a class="title" href="/board/viewArticle?seq=${dto.seq }">${dto.title }</a></td>
						<td>${dto.author }</td>
						<td>${dto.regdate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- Pagination -->
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>
	<script>
		$(function(){
			// 글 작성 버튼
			$("#writeBtn").click(function(){
				window.location.replace("/board/toWrite");
			});
			
			// css
			$(".title").css({"color":"black", "text-decoration":"none"});
			
		})	// function() end
	</script>
</body>
</html>