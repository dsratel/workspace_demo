<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- jquery -->
    <script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>

	<!-- bootstrap -->
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
    <script src="/resources/js/bootstrap/bootstrap.min.js"></script>

	<!-- summernote -->
    <link rel="stylesheet" href="/resources/css/summernote/summernote-lite.css">
    <script src="/resources/js/summernote/summernote-lite.js"></script>
    <script src="/resources/js/summernote/summernote-ko-KR.js"></script>
	<title>Article View Page</title>
</head>
<body>
	<h1>글 상세보기 페이지 입니다.</h1>
	<h3>글 번호 ${dto.seq }</h3>
	
	<div class="container">
		<form method="post" action="/board/edit.do" name="editArticleForm" id="editArticleForm">
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
					<span id="title">${dto.title }</span>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 작성자 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>작성자</span>
				</div>
				<div class="col-7 cont">
					<span id="author">${dto.author }</span>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 내용 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>내용</span>
				</div>
				<div class="col-7 cont">
					<div id="content" name="content"></div>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row" id="btnDiv">
				<div class="col">
					<c:if test="${loginId == dto.author}">
			    		<button type="button" class="btn btn-primary" id="editBtn">글 수정하기</button>
			    		<button type="button" class="btn btn-danger" id="delBtn">글 삭제하기</button>					
					</c:if>
		    		<button type="button" class="btn btn-secondary" id="listBtn">글 목록으로</button>				
				</div>
			</div>
		</form>
	</div>
	<script>
		$(function(){
			var cate = "${dto.category}";
			// 글 정보 세팅
			$("category").val('${dto.category}');
 			$("#content").append('${dto.content}');
 			if(cate == "free") {
 				$("#category").text("자유게시판");
 			};
 			
 			// 글 목록으로
 			$("#listBtn").click(function(){
 				window.location.replace("/board/toList");
 			});
 			
 			// 글 삭제하기
 			$("#delBtn").click(function(){
 				if(confirm("정말 삭제하시겠습니까?")) {
	 				$.ajax({
	 					url: "/board/delArticle",
	 					method: "get",
	 					data: {
	 						"seq" : "${dto.seq}",
	 						"attachfile" : "${dto.attachfile}"
	 					},
	 					success:function(data){
	 						window.location.replace("/board/" + data);
	 					},
	 					error: function(data){
	 						alert("오류 발생");
	 					}
	 				}); 					
 				}
 			});
 			
 			// 글 수정하기 페이지 이동
 			$("#editBtn").click(function(){
 				window.location.replace("/board/editArticle?seq=${dto.seq}");
 			});
 			
 			// css
 			$("div.tab, div.cont").css("border", "1px solid black");
 			$("button").css({"display" : "inline-block", "width" : "20%", "margin": "5px"});
 			$("#btnDiv, .tab").css("text-align", "center");
 			
		})	// function() end
	</script>
</body>
</html>