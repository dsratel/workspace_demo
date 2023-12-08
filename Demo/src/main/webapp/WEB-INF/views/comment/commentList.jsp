<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="data:image/x-icon;">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>comment list page</title>
</head>
<body>
	<form name="searchForm" action="/comment/search" method="post">
		<div class="container">
			<div class="row">
				<div class="col">
					<button type="button" class="btn btn-secondary" id="logoutBtn">로그아웃</button>
					<button type="button" class="btn btn-info" id="masterMenu">관리자 메뉴</button>
					<button type="button" class="btn btn-danger" id="selDelBtn">선택한 댓글 삭제</button>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<select class="form-select" name="searchType" id="searchType">
						<option value="0">제목</option>
						<option value="1">작성자</option>
					</select>					
					<input type="text" class="form-control" name="searchKeyword">
					<button type="button" class="btn btn-primary" id="searchBtn">검색</button>
					<span>페이지 수</span>
					<select class="form-select" name="pageSize" id="pageSize" onchange="changePageSize(this)">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="50">50</option>
						<option value="100">100</option>
					</select>
					
					<input type="hidden" name="curPage" id="curPage" value="${srchInfo.pagination.curPage }">
				</div>
			</div>
			<div>
				<input type="hidden" name="seq">
				<input type="hidden" name="pid">
				<input type="hidden" name="boardseq">
				<input type="hidden" name="replyyn">
			</div>
		
			<table class="table">
					<thead>
						<tr>
							<th>선택</th>
							<th>#</th>
							<th>상위 댓글</th>
							<th>댓글 내용</th>
							<th>작성자</th>
							<th>게시글 번호</th>
							<th>작성일</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${list.size() > 0}">
								<c:forEach var="dto" items="${list}" varStatus="status">
									<tr>
										<td><input type="checkbox" name="delList" value="${dto.seq}#${dto.pid}#${dto.boardseq > 0 ? dto.boardseq : dto.replyboardseq}#${dto.pid > 0 ? 'y' : 'n'}"></td>
										<td>${dto.seq}</td>
										<td>${dto.pid}</td>
										<td>${dto.content}</td>
										<td>${dto.id}</td>
										<td>${dto.boardseq > 0 ? dto.boardseq : dto.replyboardseq}</td>
										<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy년 MM월 dd일 k시 m분 s초" /></td>
										<td><button type="button" class="btn btn-danger" onclick="delcomment(${dto.seq}, ${dto.pid}, ${dto.boardseq > 0 ? dto.boardseq : dto.replyboardseq}, '${dto.pid > 0 ? 'y' : 'n'}')">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="8">조건에 맞는 댓글이 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<c:if test="${srchInfo ne null}">
				<!-- Pagination -->
				<ul class="pagination">
					<c:if test="${srchInfo.pagination.curRange > 1}">
						<li class="page-item"><a class="page-link" onclick="toPrevPage(${(srchInfo.pagination.curRange-1) * srchInfo.pagination.rangeSize})">Previous</a></li>			
					</c:if>
					<c:forEach var="i" begin="${srchInfo.pagination.startPage}" end="${srchInfo.pagination.endPage}">
						<li class="page-item"><a class="page-link" onclick="toPage(${i})">${i}</a></li>
					</c:forEach>
					<c:if test="${srchInfo.pagination.rangeCnt > srchInfo.pagination.curRange}">
						<li class="page-item"><a class="page-link" onclick="toNextPage(${(srchInfo.pagination.curPage*srchInfo.pagination.rangeSize)+1})">Next</a></li>			
					</c:if>
				</ul>		
			</c:if>
		</div>
	</form>
	<script>
		// css
		$("input[name='searchKeyword'], select").css({"width" : "20%", "display" : "inline", "margin" : "5px"});
		$("#searchBtn").css("margin-right", "20px");
		$("form, table").css({"margin-top":"20px"});
		
			
		// 로그아웃 버튼
		$("#logoutBtn").click(function(){
			window.location.replace("/common/logout?id=${loginId}");
		});
		
		// 선택 게시물 삭제
		$("#selDelBtn").click(function(){
			var frm = $("form[name='searchForm']");
			frm.attr("action", "/comment/selDelComment");
			frm.attr("method", "post");
			frm.submit();
		});
		
		// 관리자 메뉴
		$("#masterMenu").click(function(){
			window.location.replace("/master/home");
		});
		
		// 댓글 삭제
		function delcomment(seq, pid, boardseq, replyyn) {
			// form input 값 대입
			$("input[name='seq']").val(seq);
			$("input[name='pid']").val(pid);
			$("input[name='boardseq']").val(boardseq);
			$("input[name='replyyn']").val(replyyn);
			
			// ajax
		    $.ajax({
		        type: "get",
		        url: "/comment/delete.do",
		        data: $("form[name='searchForm']").serialize(),
		        success: function(data){
		        	alert("댓글 삭제 성공");
		            location.reload();
		        },
		        error: function(data){
		            console.log(data);
		            alert("댓글 삭제에 실패하였습니다.");
		        }
		    });
		}
	</script>
</body>
</html>