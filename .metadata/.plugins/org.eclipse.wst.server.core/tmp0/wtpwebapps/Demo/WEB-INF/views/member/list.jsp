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
                
                    <!-- Content Row -->
                    <div class="row">
						<div class="col-9">
							<form name="searchForm" action="/member/list" method="get" id="searchForm">
				 				<select name="searchType" class="custom-select" id="searchType">
				 					<option value="0">ID</option>
									<option value="1">NICKNAME</option>
									<option value="2">NAME</option>
									<option value="3">ADDRESS</option>
									<option value="4">PHONE</option>
									<option value="5">전체</option>
				 				</select>
				 				<input type="text" name="searchKeyword" class="form-control" id="searchKeyword" value="${srchInfo.searchKeyword }"> 	
				 				<button type="button" class="btn btn-dark" id="searchBtn">검색</button>
				 				<label for="pageSize">페이지 당 row 수</label>
				 				<select name="pageSize" class="custom-select" id="pageSize">
				 					<option value="2">2</option>
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
				 				</select>
							</form>
			 			</div>
			 			<div class="col-3" style="text-align: right">
				 			<c:if test="${masteryn eq 'y'.charAt(0) && delList ne 'y'}">
				 				<button class="btn btn-danger" id="selDelMember">선택 회원 삭제</button>
			 				</c:if>
						</div>
						<input type="hidden" id="searchType_init" value="${srchInfo.searchType == null ? 0 : srchInfo.searchType}">
						<input type="hidden" id="pageSize_init" value="${srchInfo.pagination.pageSize == null ? 10 : srchInfo.pagination.pageSize}">
					</div>
		
					<table class="table">
						<thead>
							<tr>
								<c:if test="${masteryn eq 'y'.charAt(0)}">
									<th scope="col">선택</th>					
								</c:if>
								<th scope="col">#</th>
								<th scope="col">ID</th>
								<th scope="col">닉네임</th>
								<th scope="col">이름</th>
								<th scope="col">주소</th>
								<th scope="col">전화번호</th>
								<th scope="col">가입일자</th>
								<c:if test="${masteryn eq 'y'.charAt(0)}">
									<th scope="col">보기</th>
									<c:if test="${delList ne 'y'}">
										<th scope="col">삭제</th>									
									</c:if>
								</c:if>
							</tr>
						</thead>
				
						<tbody>
							<c:forEach var="member" items="${list }" varStatus="status">
							<tr>
								<c:if test="${masteryn eq 'y'.charAt(0)}">
									<td><input type="checkbox" class="memCheckBox form-check-input" name="memCheckBox" value="${member.id }"></td>					
								</c:if>
								<th scope="row">${srchInfo.pagination.startIndex+status.count}</th>
								<td><c:out value="${member.id }"/></td>
								<td><c:out value="${member.nickname }"/></td>
								<td><c:out value="${member.name }"/></td>
								<td><c:out value="${member.address }"/></td>
								<td><c:out value="${member.phone }"/></td>
								<td>
									<%-- <c:out value="${member.regdate }"/> --%>
									<span><fmt:formatDate value="${member.regdate }" pattern="yyyy년 MM월 dd일" /></span><br/>
									<span><fmt:formatDate value="${member.regdate }" pattern="k시 m분 s초" /></span>		
								</td>
								<c:if test="${masteryn eq 'y'.charAt(0)}">
									<td><button type="button" class="btn btn-secondary BtnEditMember" id="BtnEditMember" value="${member.id }">보기</button></td>
									<c:if test="${delList ne 'y'}">
										<td><button type="button" class="btn btn-danger BtnDelMember" id="BtnDelMember" value="${member.id }">삭제</button></td>									
									</c:if>
								</c:if>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
					  	<c:if test="${srchInfo.pagination.curRange > 1 }">
					    	<li class="page-item"><a class="page-link" id="prevPage">Previous</a></li>
					    </c:if>
					    <c:forEach var="i" begin="${srchInfo.pagination.startPage }" end="${srchInfo.pagination.endPage }">
					    	<li class="page-item"><a class="page-link toPage" value="${i }" >${i }</a></li>
					    </c:forEach>
					    <c:if test="${srchInfo.pagination.rangeCnt > srchInfo.pagination.curRange}">
					    	<li class="page-item"><a class="page-link" id="nextPage">Next</a></li>
					    </c:if>
					    <input type="hidden" id="curPage_init" value="${srchInfo.pagination.curPage}">
					    <input type="hidden" id="curRange_init" value="${srchInfo.pagination.curRange}">
					    <input type="hidden" id="rangeSize_init" value="${srchInfo.pagination.rangeSize}">
					  </ul>
 					</nav>
                    
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
    <link rel="stylesheet" href="/resources/css/custom/member.css">
    <script type="text/javascript" src="/resources/js/custom/member/list.js"></script>
</body>
</html>