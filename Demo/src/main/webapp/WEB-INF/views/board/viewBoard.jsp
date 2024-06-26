<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
	
		<!-- Sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="main_content">

                <!-- Topbar -->
                <%@ include file="/WEB-INF/views/common/topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">
                
                    <!-- Content Row -->
                    <div class="">
											<div class="row">	<!-- 카테고리 -->
												<div class="col-1"></div>
												<div class="col-1 tab">
													<span>카테고리</span>
												</div>
												<div class="col-9 cont">
													<span id="category"></span>
												</div>
												<div class="col-1"></div>
											</div>
											<div class="row">	<!-- 제목 -->
												<div class="col-1"></div>
												<div class="col-1 tab">
													<span>제목</span>
												</div>
												<div class="col-9 cont">
													<span name="title" id="title">${dto.title}</span>
												</div>
												<div class="col-1"></div>
											</div>
											<div class="row">	<!-- 작성자 -->
												<div class="col-1"></div>
												<div class="col-1 tab">
													<span>작성자</span>
												</div>
												<div class="col-9 cont">
													<span id="author" readonly>${dto.author}</span>
												</div>
												<div class="col-1"></div>
											</div>
											<div class="row">	<!-- 내용 -->
												<div class="col-1"></div>
												<div class="col-1 tab">
													<span>내용</span>
												</div>
												<div class="col-9 cont">
													<textarea id="content" readonly>${dto.content }</textarea>
												</div>
												<div class="col-1"></div>
											</div>
											<c:if test="${files ne null}">
												<div class="row">	<!-- 첨부파일 -->
													<div class="col-1"></div>
													<div class="col-1 tab">
														<span>첨부파일</span>
													</div>
													<div class="col-9 cont imgDiv">
														<c:forEach var="filePath" items="${files}">
															<img class="img-thumbnail" src="${filePath}">
														</c:forEach>			
													</div>
													<div class="col-1"></div>
												</div>											
											</c:if>
											<c:if test="${loginId ne '' && loginId ne null}">
												<div class="row" id="commentDiv">	<!-- 댓글 / 회원인 경우에만 작성 가능 -->
													<div class="col-1"></div>
													<div class="col-10">
														<div class="row" >
															<input type="text" value="${loginId}" id="cmtId" readonly>
															<input type="password" placeholder="password" id="cmtPw" maxlength="20">
															<textarea id="cmtContent"></textarea>
															<button type="button" id="commentBtn" class="btn btn-primary">댓글작성</button>						
														</div>
													</div>
													<div class="col-1"></div>
													<form name="cmtForm" method="post" id="cmtForm">
														<input type="hidden" name="id" value="${loginId}">
														<input type="hidden" name="pw">
														<input type="hidden" name="content">
														<input type="hidden" name="boardseq" value="${dto.seq}">
														<input type="hidden" name="replyboardseq" value="-1">
														<input type="hidden" name="seq" value="0">
														<input type="hidden" name="rootseq" value="0">
														<input type="hidden" name="depth" value="0">
														<input type="hidden" name="re_order" value="0">
														<input type="hidden" name="pid" value="0">
													</form>
												</div>
											</c:if>
											<div class="row" id="cmtDiv">
												<c:choose>
													<c:when test="${cmtList eq 'noComment'}">
														<div class="col">
															<span>해당 글에 작성된 댓글이 없습니다.</span>
														</div>
													</c:when>
													<c:otherwise>
														<div class="col-1"></div>
														<div class="col-10" id="cmtListDiv">
															<c:forEach var="cmt" items="${cmtList}">
																<div class="cmtList">
																	<div class="row cmtIdDiv">
																		<div class="col">
																			<span>${cmt.nickname}</span>
																		</div>
									 								</div>		
																	<div class="row cmtContDiv">
																<div class="col-8 cmtContent">
																			<span>${cmt.content}</span>
																			<input type="hidden" seq="${cmt.seq }">
																			<input type="hidden" boardseq="${cmt.boardseq}">
																		</div>
																		<div class="col-2 cmtTime">
																			<span><fmt:formatDate value="${cmt.regdate}" pattern="yyyy년 MM월 dd일" /></span><br/>
																			<span><fmt:formatDate value="${cmt.regdate}" pattern="k시 m분 s초" /></span>						
																		</div>
																		<div class="col-2 cmtBtnDiv">
																		<c:if test="${loginId ne '' && loginId ne null}">
																			<button type="button" class="btn btn-info" onclick="replyCmtForm(${cmt.seq}, ${cmt.depth}, this);" id="replyCmtBtn_${cmt.seq}">답글</button><br/>									
																		</c:if>
																		<c:if test="${loginId == cmt.id}">
																			<button type="button" class="btn btn-danger delCmtBtn" onclick="delPwPop(${cmt.seq}, 0)">삭제</button>
																			<button type="button" class="btn btn-success editCmtBtn" onclick="editCmt(${cmt.seq}, 0, this)">수정</button>											
																		</c:if>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
														<div class="col-1"></div>
													</c:otherwise>
												</c:choose>
											</div>
									
											<div class="row" id="btnDiv">
												<div class="col">
													<c:if test="${loginId == dto.author}">
												    	<button type="button" class="btn btn-success" id="editBtn">글 수정하기</button>
												    	<button type="button" class="btn btn-danger" id="delBtn">글 삭제하기</button>				
													</c:if>
													<c:if test="${loginId ne '' && loginId ne null && dto.pid == 0}">
														<button type="button" class="btn btn-info" id="replyBtn">답글 달기</button>
													</c:if>
										    		<button type="button" class="btn btn-secondary" id="listBtn">글 목록으로</button>				
												</div>
											</div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
		
	<!-- Core plugin JavaScript-->
    <script src="/resources/template/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/template/js/sb-admin-2.min.js"></script>
	
	  <!-- Custom for this page -->
    <link rel="stylesheet" href="/resources/css/custom/viewBoard.css">
    <script type="text/javascript" src="/resources/js/custom/board/viewBoard.js"></script>
	
</body>
</html>