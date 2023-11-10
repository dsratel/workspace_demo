<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>view board page</title>
</head>
<body>
	<div class="container">
		<div class="row">	<!-- 카테고리 -->
			<div class="col-2"></div>
			<div class="col-1 tab">
				<span>카테고리</span>
			</div>
			<div class="col-7 cont">
				<span id="category"></span>
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row">	<!-- 제목 -->
			<div class="col-2"></div>
			<div class="col-1 tab">
				<span>제목</span>
			</div>
			<div class="col-7 cont">
				<span name="title" id="title">${dto.title}</span>
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row">	<!-- 작성자 -->
			<div class="col-2"></div>
			<div class="col-1 tab">
				<span>작성자</span>
			</div>
			<div class="col-7 cont">
				<span id="author" readonly>${dto.author}</span>
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row">	<!-- 내용 -->
			<div class="col-2"></div>
			<div class="col-1 tab">
				<span>내용</span>
			</div>
			<div class="col-7 cont">
				<textarea id="content" readonly>${dto.content }</textarea>
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row">	<!-- 첨부파일 -->
			<div class="col-2"></div>
			<div class="col-1 tab">
				<span>첨부파일</span>
			</div>
			<div class="col-7 cont imgDiv">
				<c:forEach var="filePath" items="${files }">
					<img class="img-thumbnail" src="${filePath}">
				</c:forEach>			
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row" id="btnDiv">
			<div class="col">
				<c:if test="${loginId == dto.author}">
			    	<button type="button" class="btn btn-success" id="editBtn">글 수정하기</button>
			    	<button type="button" class="btn btn-danger" id="delBtn">글 삭제하기</button>				
				</c:if>
	    		<button type="button" class="btn btn-secondary" id="listBtn">글 목록으로</button>				
			</div>
		</div>
		
	</div>
	<script>
		// css
		$("div.tab, div.cont").css({"border" : "1px solid black", "padding" : "5px"});
		$("button").css({"display" : "inline-block", "width" : "20%", "margin": "5px"});
		$("#btnDiv, .tab, .imgDiv").css("text-align", "center");	
		$(".container").css("margin-top", "20px");
		$("#content").css({"width" : "100%", "height" : "300px", "border" : "none", "resize" : "none"});
		$("textarea").css("outline", "none");
		$(".img-thumbnail").css({"width":"200px", "height":"200px"});
		
		// 초기 세팅
		//// 카테고리
		switch("${dto.category}") {
			case 'free' :
				$("#category").text("자유게시판");
			break;
			case 'notice' :
				$("#category").text("공지사항");
			break;
		}
		
		// 글 수정하기
		$("#editBtn").click(function(){
			window.location.replace("/board/editArticle?seq=${dto.seq}");
		});
		
		// 글 삭제하기
		$("#delBtn").click(function(){
			window.location.replace("/board/delArticle?seq=${dto.seq}&attachfile=${dto.attachfile}");
		});
		
		// 글 목록으로
		$("#listBtn").click(function(){
			window.location.replace("/board/toList");
		});
		
		
	
	</script>
</body>
</html>