<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>edit board page</title>
</head>
<body>
	<h1>글 수정 페이지 - 본인의 글만 수정 가능</h1>
	<div class="container">
		<form method="post" name="editBoardForm" action="/board/edit.do" enctype="multipart/form-data">
			<div class="row">	<!-- 카테고리 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>카테고리</span>
				</div>
				<div class="col-7 cont">
					<select class="form-select" name="category" id="category">
						<option value="free">자유게시판</option>
						<option value="notice">공지사항</option>
					</select>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 제목 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>제목</span>
				</div>
				<div class="col-7 cont">
					<input class="form-control" type="text" name="title" id="title" value="${dto.title}">
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 작성자 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>작성자</span>
				</div>
				<div class="col-7 cont">
					<input class="form-control" type="text" name= "author" id="author" value="${loginId}" readonly>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 내용 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>내용</span>
				</div>
				<div class="col-7 cont">
					<textarea class="form-control" id="content" name="content">${dto.content }</textarea>
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row">	<!-- 첨부파일 -->
				<div class="col-2"></div>
				<div class="col-1 tab">
					<span>첨부파일</span>
				</div>
				<div class="col-7 cont attachDiv">
					<c:forEach var="file" items="${files }" varStatus="status">
						<img class="img-thumbnail" src="${file.filePath}" conFile="attachfile${status.count }">
						<input type="hidden" name="prevImg" value="${file.seq}">
					</c:forEach>
					<input class="form-control" type="file" id="attachfile1">
					<input class="form-control" type="file" id="attachfile2">
					<input class="form-control" type="file" id="attachfile3">
					<input type="hidden" name="attachfile" id="attachfile">	<!-- 첨부파일 유무 -->
					<input type="hidden" name="fileCnt" id="fileCnt">		<!-- 첨부파일 개수 -->
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row" id="btnDiv">
				<div class="col">
			    	<button type="button" class="btn btn-primary" id="editBtn">글 수정하기</button>
		    		<button type="button" class="btn btn-secondary" id="listBtn">글 목록으로</button>				
				</div>
			</div>
			<input type="hidden" name="seq" value="${dto.seq }">
		</form>
	</div>
	<script>
		$(function(){
			// css
			$("div.tab, div.cont").css({"border" : "1px solid black", "padding" : "5px"});
			$("button").css({"display" : "inline-block", "width" : "20%", "margin": "5px"});
			$("#btnDiv, .tab").css("text-align", "center");	
			$(".container").css("margin-top", "20px");
			$("#content").css({"width" : "100%", "height" : "300px", "border" : "none", "resize" : "none"});
			$(".img-thumbnail").css({"width":"200px", "height":"200px"});
			
			// 첨부파일 개수만큼 <input>보이기
 			for(var i = 0; i < "${fileCnt}"; i++) {
				$("#attachfile" + (i+1)).hide();
			}
			
			// 사진 더블클릭 시 삭제
			$("img").dblclick(function(el){
				$($("#"+$(this).attr("conFile"))).show();
				$(this).next().remove();	// prevImg input 태그 삭제
				$(this).remove();			// 이전 이미지 삭제
			});
			
			// 글 수정 버튼
			$("#editBtn").click(function(){
				// 파일 개수 체크
				var fileCnt = 0;
				var files = $("input[type='file']");
				for(var i = 0; i < files.length; i++) {
					if(files.get(i).files.length > 0) {
						fileCnt++;
						files.get(i).name= 'upfile';
					} else {
						files.get(i).name = '';
					}
				}
				
				// 파일이 있는 경우 attachfile에 y값 입력
				var prevImg = $("input[name='prevImg']");
				if(prevImg.length == 0 && fileCnt == 0) {
					$("#attachfile").val('n');					
				} else {
					$("#attachfile").val('y');					
				}
				
				// 파일 개수 입력
				$("#fileCnt").val(fileCnt);
				
				$("form[name='editBoardForm']").submit();
			});
			
			// 글 목록 버튼
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});
		})	// function() end
	</script>
</body>
</html>