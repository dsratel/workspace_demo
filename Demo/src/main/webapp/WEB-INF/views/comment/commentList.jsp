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
            <div id="content">

                <!-- Topbar -->
                <%@ include file="/WEB-INF/views/common/topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid" style="padding: 0% 5%; text-align:center;">
                	<div class="searchTab" style="text-align:right;">
										<c:if test="${loginId ne ''}">
											<div class="col">
												<c:if test="${masteryn eq 'y'.charAt(0)}">
													<button type="button" class="btn btn-danger" id="selDelBtn">선택한 댓글 삭제</button>
												</c:if>
											</div>
										</c:if>
									</div>
									<form method="get" action="/comment/myList" name="searchForm" id="searchForm">
						 				<div class="row" style="margin-bottom: 20px;">
											<div class="col">
												<select class="form-control" name="searchType" id="searchType">
													<option value="0">내용</option>
													<option value="1">게시글 번호</option>
												</select>					
												<input type="text" class="form-control" name="searchKeyword" value="${srchInfo.searchKeyword}">
												<button type="button" class="btn btn-primary" id="searchBtn">검색</button>
												<span>페이지 수</span>
												<select class="form-control" name="pageSize" id="pageSize" onchange="changePageSize(this)">
													<option value="10">10</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="100">100</option>
												</select>
												<input type="hidden" name="curPage" id="curPage" value="${srchInfo.pagination.curPage }">
												<input type="hidden" id="searchType_init" value="${srchInfo.searchType}">
												<input type="hidden" id="pageSize_init" value="${srchInfo.pagination.pageSize}">
											</div>
										</div>
				                
                    <!-- Content Row -->
                    <div class="row">
                    	<div class="col-1"></div>
                    	<div class="col-1">작성자</div>
                    	<div class="col-6">내용</div>
                    	<div class="col-1">게시글 번호</div>
                    	<div class="col-1">댓글/답글</div>
                    	<div class="col-2">작성일</div>
                    </div>
                    <c:choose>
                    	<c:when test="${list eq null}">
                    		<div class="row">
                    			<div class="col"><span>작성한 댓글이 없습니다.</span></div>
                    		</div>
                    	</c:when>
                    	<c:otherwise>
                    		<c:forEach var="dto" items="${list}" varStatus="status">
			                    <div class="row">
			                    	<div class="col-1"><input type="checkbox" name="delList" value="${dto.seq}#${dto.pid}#${dto.boardseq}#${dto.replyboardseq > 0 ? 'y' : 'n'}"></div>
				               			<div class="col-1">${dto.id }</div>
				               			<div class="col-6" style="text-align:left;">${dto.content }</div>
				               			<div class="col-1">${dto.boardseq}</div>
				               			<div class="col-1">${dto.pid > 0 ? "답글" : "댓글"}</div>
				               			<div class="col-2">
				               				<fmt:formatDate value="${dto.regdate}" pattern="yyyy년 MM월 dd일" /><br/>
				               				<fmt:formatDate value="${dto.regdate}" pattern="k시 m분 s초" />
				               			</div>
			                    </div>
		               			</c:forEach>
                    	</c:otherwise>
                    </c:choose>
	               	</form>
               		
					<c:if test="${srchInfo ne null}">
	                    <!-- Pagination -->
	                    <ul class="pagination">
	                        <c:if test="${srchInfo.pagination.curRange > 1}">
	                            <li class="page-item"><a class="page-link" onclick="toPage(${(srchInfo.pagination.curRange-1) * srchInfo.pagination.rangeSize})">Previous</a></li>			
	                        </c:if>
	                        <c:forEach var="i" begin="${srchInfo.pagination.startPage}" end="${srchInfo.pagination.endPage}">
	                            <li class="page-item"><a class="page-link" onclick="toPage(${i})" value="${i }" >${i}</a></li>
	                        </c:forEach>
	                        <c:if test="${srchInfo.pagination.rangeCnt > srchInfo.pagination.curRange}">
	                            <li class="page-item"><a class="page-link" onclick="toPage(${(srchInfo.pagination.curPage*srchInfo.pagination.rangeSize)+1})">Next</a></li>			
	                        </c:if>
	                    </ul>		
	                </c:if>
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
    <script type="text/javascript" src="/resources/js/custom/comment/comment.js"></script>
	
</body>
</html>