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
                    <form method="post" name="editBoardForm" action="/board/edit.do" enctype="multipart/form-data">
										<c:choose>
											<c:when test="${dto.pid > 0}">
												<input type="hidden" name="pid" value="${dto.pid }">
												<input type="hidden" name="category" value="${dto.category }">					
											</c:when>
											<c:otherwise>
												<div class="row">	<!-- 카테고리 -->
													<div class="col-2"></div>
													<div class="col-1 tab">
														<span>카테고리</span>		
													</div>
													<div class="col-7 cont">
														<select class="form-control" name="category" id="category">
															<option value="free">자유게시판</option>
															<option value="notice">공지사항</option>
														</select>		
													</div>
													<div class="col-2"></div>
												</div>				
											</c:otherwise>
										</c:choose>
										
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
    <link rel="stylesheet" href="/resources/css/custom/writeBoard.css">
    <script type="text/javascript" src="/resources/js/custom/board/editBoard.js"></script>
</body>
</html>