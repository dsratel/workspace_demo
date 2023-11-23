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
	<title>test view</title>
</head>
<body>
		<div class="row" id="cmtDiv">
			<c:choose>
				<c:when test="${cmtList eq 'noComment'}">
					<div class="col">
						<span>해당 글에 작성된 댓글이 없습니다.</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-1"></div>
					<div class="col-10 row" id="cmtListDiv">
						<c:forEach var="cmt" items="${cmtList}">
							<div class="row cmtList">
								<div class="row cmtSeq">
									<span>${cmt.seq}</span>
 								</div>		
								<div class="row cmtContDiv">
									<div class="col-1 cmtRootSeq">
										<span>${cmt.rootseq}</span>
									</div>
									<div class="col-1 cmtPid">
										<span>${cmt.pid}</span>					
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="col-1"></div>
				</c:otherwise>
			</c:choose>
		</div>
		<script>
			$("div.cmtList").css({"border":"1px solid black"});
		</script>
</body>
</html>