<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<c:if test="${loginId ne '' }">
			<div class="row">	<!-- 댓글 / 회원인 경우에만 작성 가능 -->
				<div class="col-2"></div>
				<div class="col-8">
					<form name="cmtForm" action="/comment/write.do" method="post">
						<input type="text" name="id" value="${loginId}" placeholder="id" readonly>
						<input type="password" name="pw" placeholder="password" maxlength="20">
						<input type="hidden" name="boardseq" value="${dto.seq}">
						<textarea name="content" id="cmtContent"></textarea>
						<button type="button" id="commentBtn">댓글작성</button>	
					</form>				
				</div>
				<div class="col-2"></div>
			</div>
		</c:if>
		<div class="row" id="cmtDiv">
			<!-- 														if 댓글리스트가 없다면? 있다면? -->
			<c:choose>
				<c:when test="${cmtList eq 'noComment'}">
					<div class="col">
						<span>해당 글에 작성된 댓글이 없습니다.</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-2"></div>
					<div class="col-8 row" id="cmtListDiv">
						<c:forEach var="cmt" items="${cmtList}">
							<div class="row cmtList">
								<div class="col cmtIdDiv">
									<span>${cmt.nickname}</span>
								</div>
								<div class="row">
									<div class="col-10">
										<span>${cmt.content}</span>
									</div>
									<div class="col-2 cmtTime">
										<%-- <span>${cmt.regdate.getTime()}</span> --%>
										<span></span>
										<span><fmt:formatDate value="${cmt.regdate}" pattern="yyyy-MM-dd" /></span>									
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="col-2"></div>				
				</c:otherwise>
			</c:choose>
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
		<div class="col-8 row" id="temp">
			<c:forEach var="cmt" items="${cmtList}">
				<div class="row cmtList">
					<div class="col cmtIdDiv">
						<span>${cmt.nickname}</span>
					</div>
					<div class="row">
						<div class="col-8">
							<span>${cmt.content}</span>
						</div>
						<div class="col-4">
							<span>${cmt.regdate}</span>									
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
		$(function(){
			// css
			$("div.tab, div.cont").css({"border" : "1px solid black", "padding" : "5px"});
			$("button").css({"display" : "inline-block", "width" : "20%", "margin": "5px"});
			$("#btnDiv, .tab, .imgDiv").css("text-align", "center");	
			$(".container").css("margin-top", "20px");
			$("#content").css({"width" : "100%", "height" : "300px", "border" : "none", "resize" : "none"});
			$("textarea").css("outline", "none");
			$(".img-thumbnail").css({"width":"200px", "height":"200px"});
			$(".cmtList").css({"border":"1px black solid", "margin":"5px"});
			$(".cmtIdDiv").css({"margin-bottom":"10px"});
			
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
			
			// 댓글 시간 포맷 변경
/* 			var cmtTimes = $(".cmtTime span");
			for(var i=0; i < cmtTimes.length; i++) {
				cmtTimes.get(i).remove();
				console.log(cvtDateFormat(new Date(parseInt(cmtTimes.get(i).innerText))));
				$(".cmtTime").get(i).append("<span>" + cvtDateFormat(new Date(parseInt(cmtTimes.get(i).innerText))) + "</span>");
				
				
			} */
			
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
			
			// 댓글작성
			$("#commentBtn").on("click", function() {
				// 유효성 검사
				//// 댓글 비밀번호(4자리 이상)
				if($("input[name='pw']").val().length < 4) {
					alert("댓글 비밀번호를 4자리 이상 입력해주세요.");
					return ;
				}
				
				//// 댓글 입력
				var cmtContent = $("#cmtContent").val().trim();
				if(cmtContent.length < 1) {
					alert("댓글 내용을 입력해주세요.");
					return ;
				}
				
				$.ajax({
					type: "post",
					url: "/comment/write.do",
					data: $("form[name='cmtForm']").serialize(),
					success: function(data){
						alert("ajax 성공");
						commentReload("${dto.seq}");
					},
					error: function(data){
						alert("ajax 실패");
						console.log(data);
					}
				});
			});
			
			
			
		})	// function end
		
		// 댓글 작성 후 리로드
		function commentReload(boardseq) {
			$.ajax({
				type: "post",
				url: "/comment/list",
				data: {
					"boardseq" : boardseq	
				},
				success: function(data) {
					alert("리로드 성공");
					console.log(data);
					
					var appendStr = "";
					
					for(var i=0; i < data.length; i++) {
						appendStr = appendStr + "<div class='row cmtList'>"
												+ "<div class='col cmtIdDiv'>"
												+ 	"<span>" + data[i].nickname + "</span>"
												+ "</div>"
												+ "<div class='row'>"
												+	"<div class='col-8'>"
												+		"<span>" + data[i].content + "</span>"
												+	"</div>"
												+	"<div class='col-4'>"
												+		"<span>" + cvtDateFormat(data[i].regdate) + "</span>"
												+	"</div>"
												+ "</div> </div>";
					}
					
					console.log(appendStr);
					
					$("#cmtListDiv").children().remove();
					$("#cmtListDiv").append(appendStr);
					$(".cmtList").css({"border":"1px black solid", "margin":"5px"});
					$(".cmtIdDiv").css({"margin-bottom":"10px"});
				},
				error: function(data) {
					alert("리로드 실패");
					console.log(data);
				}
			});
		}
		
		// 날짜 변환
		function cvtDateFormat(date) {
			date = new Date(date);
			return date.getFullYear() + '년 ' + (date.getUTCMonth()%12+1) + '월 ' + date.getUTCDate() + '일 <br>'
					+ date.getHours() + '시 ' + date.getMinutes() + '분 ' + date.getSeconds() + '초';
		}
	</script>
</body>
</html>