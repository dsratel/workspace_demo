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
							<div class="container-fluid">
                
							<!-- Article List -->
							<div class="searchTab" style="text-align:right;">
								<c:if test="${loginId ne ''}">
									<div class="col">
										<button type="button" class="btn btn-warning" id="writeBtn">글 작성</button>
										<c:if test="${masteryn eq 'y'.charAt(0)}">
											<button type="button" class="btn btn-danger" id="selDelBtn">선택한 게시글 삭제</button>
										</c:if>
									</div>
								</c:if>
							</div>
							<form method="get" action="/board/toList" name="searchForm" id="searchForm">
				 				<div class="row">
									<div class="col">
										<select class="form-control" name="category" onchange="changeCategory(this)" init="${srchInfo.category}">
											<option value="free">자유게시판</option>
											<option value="notice">공지사항</option>
										</select>
										<select class="form-control" name="searchType" id="searchType" init="${srchInfo.searchType}">
											<option value="0">제목</option>
											<option value="1">작성자</option>
										</select>					
										<input type="text" class="form-control" name="searchKeyword" init="${srchInfo.searchKeyword}">
										<button type="button" class="btn btn-primary" id="searchBtn">검색</button>
										<span>페이지 수</span>
										<select class="form-control" name="pageSize" id="pageSize" onchange="changePageSize(this)" init="${srchInfo.pagination.pageSize}">
											<option value="10">10</option>
											<option value="20">20</option>
											<option value="50">50</option>
											<option value="100">100</option>
										</select>
										<input type="hidden" name="curPage" id="curPage" value="${srchInfo.pagination.curPage }">
									</div>
								</div>
                <table class="table">
                    <thead>
                        <tr>
                            <c:if test="${masteryn eq 'y'.charAt(0)}">
                                <th scope="col">선택</th>					
                            </c:if>
                            <th>#</th>
                            <th>게시판 종류</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>첨부파일</th>
                            <th>조회수</th>
                            <c:if test="${masteryn eq 'y'.charAt(0)}">
                                <th scope="col">삭제</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${list ne null }">
                                <c:forEach var="dto" items="${list}" varStatus="status">
                                    <tr>
                                        <%-- <td>${status.count + srchInfo.pagination.startIndex}</td> --%>
                                        <c:if test="${masteryn eq 'y'.charAt(0)}">
                                            <td><input type="checkbox" name="delList" value="${dto.seq}#${dto.pid}#${dto.attachfile}#${dto.commentcnt > 0 ? 'y' : 'n'}"></td>
                                        </c:if>
                                        <td>${srchInfo.pagination.listCnt - (status.count + srchInfo.pagination.startIndex -1)}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${dto.category eq 'free'}">자유게시판</c:when>
                                                <c:when test="${dto.category eq 'notice'}">공지사항</c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${dto.pid > 0 }">
                                                <img src="/resources/image/replyArrow.png" style="height:15px">						
                                            </c:if>
                                            <a class="title" href="/board/viewArticle?seq=${dto.seq}&pid=${dto.pid}">${dto.title }
                                            <span class="commentCnt">[${dto.commentcnt}]</span></a>
                                        </td>
                                        <td class="authors" author="${dto.etc}" nickname="${dto.author }">${dto.author }</td>
                                        <td><fmt:formatDate value="${dto.regdate}" pattern="yyyy년 MM월 dd일 k시 m분 s초" /></td>
                                        <td>
                                        	<c:if test="${dto.attachfile == 'y'}">
											                      <i class="fa fa-solid fa-paperclip"></i>                  	
                                        	</c:if>
                                        </td>
                                        <td>${dto.viewcnt }</td>
                                        <c:if test="${masteryn eq 'y'.charAt(0)}">		
                                            <td><button type="button" class="btn btn-danger" onclick="delArticle(${dto.seq},${dto.pid},'${dto.attachfile}','${dto.commentcnt > 0 ? 'y' : 'n'}')">삭제</button></td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="6">조건에 맞는 게시글이 없습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
                <div class="layer_popup">
                    <button type="button" class="btn btn-info" id="toMemberArticle">사용자 게시글 보기</button>
                </div>
                <c:if test="${srchInfo ne null}">
                    <!-- Pagination -->
                    <ul class="pagination">
                        <c:if test="${srchInfo.pagination.curRange > 1}">
                            <li class="page-item"><a class="page-link" onclick="toPage(${(srchInfo.pagination.curRange-1) * srchInfo.pagination.rangeSize})">Previous</a></li>			
                        </c:if>
                        <c:forEach var="i" begin="${srchInfo.pagination.startPage}" end="${srchInfo.pagination.endPage}">
                            <li class="page-item"><a class="page-link" onclick="toPage(${i})" value="${i }">${i}</a></li>
                        </c:forEach>
                        <c:if test="${srchInfo.pagination.rangeCnt > srchInfo.pagination.curRange}">
                            <li class="page-item"><a class="page-link" onclick="toPage(${(srchInfo.pagination.curPage*srchInfo.pagination.rangeSize)+1})">Next</a></li>			
                        </c:if>
                    </ul>
                    <input type="hidden" id="curPage_init" value="${srchInfo.pagination.curPage}">		
                </c:if>
							</form>
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

    <!-- Custom scripts for all pages-->
    <script src="/resources/template/js/sb-admin-2.min.js"></script>
    
    <!-- Custom for this page -->
		<script type="text/javascript" src="/resources/js/custom/board/listArticle.js"></script>
	
</body>
</html>