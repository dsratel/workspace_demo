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
	<title>Article Edit Page</title>
</head>
<body>
    <h1>글 작성 페이지</h1>
    <div class="container">
    	<form method="post" action="/board/edit.do" name="editArticleForm" id="editArticleForm" encType="multipart/form-data">
    		<input type="text" name="title" value="${dto.title }">
    		<input type="text" name="author" value="${loginId }" readonly>
    		<select name="category" class="custom-select" id="category">
    			<option value="free">자유게시판</option>
    		</select>
    		<br><br>
    		<textarea id="summernote" name="content"></textarea>
    		<button type="button" class="btn btn-success" id="editBtn">글 수정하기</button>
    		<button type="button" class="btn btn-secondary" id="listBtn">글 목록으로</button>
    	
    	</form>
    </div>
    
	<script>
	    $(function(){
			//섬머노트 실행
 			$('#summernote').summernote({
				tabsize: 2,
				height: 100,
				
				// 콜백함수
  				callbacks : {
					onImageUpload : function(files, editor) {
						// 파일 업로드(다중 업로드를 위해 반복문 사용)
 						 for(var i = 0; i < files.length; i++) {
							sendFile(files[i], this);
						}  
					}
				} 
			});
 			$("button[aria-label='Video']").hide();	// 비디오 버튼 숨김
 			
 			// 썸머노트 content 입력
 			$(".note-editable").append('${dto.content}');
 			
 			// 글 목록으로
 			$("#listBtn").click(function(){
 				window.location.replace("/board/toList");
 			});
			
			// 글 저장
			$("#editBtn").click(function(){
				$("#editArticleForm").submit();
			});
	    }) // function() end
	    
	    
	    // 썸머노트 이미지 업로드 콜백함수
	    function sendFile(file, editor) {
	    	data = new FormData();
	    	data.append("file", file);
	    	data.append("author", "${loginId}");
	    	$.ajax({
	    		data: data,
	    		type: "post",
	    		url: "/board/summernoteImgSave",
	    		contentType: false,
	    		processData: false,
	    		enctype: "multipart/form-data",
	    		success: function(data) {
	    			console.log("썸머노트 url 변경 성공");
	    			console.log(data);
	    			$(editor).summernote('editor.insertImage', data);
	    			//editor.insertImage(data);
	    		} ,
	    		error: function(data) {
	    			console.log("썸머노트 url 변경 실패");
	    			console.log(data);
	    		}
	    	});
	    }
	</script>
</body>
</html>