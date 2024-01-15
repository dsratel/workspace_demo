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
                
								<form action="/member/editMember.do" method="post" name="editMemberForm" id="editMemberForm" enctype="multipart/form-data">
                    <!-- Content Row -->
                    <div class="contentRow">
								<div class="row">
									<div class="col-2 editOption">
										<span>프로필 사진</span>
									</div>
									<div class="col-7">
										<img class="img-thumbnail view" src="${filePath}" />
										<img class="img-thumbnail edit" src="${filePath}" fn="${dto.fileno }" id="preview"/>
										<input type="file" class="form-control edit" id="profileInput" name="profilePhoto" onchange="readURL(this)";>
<%-- 										<c:choose>
											<c:when test="${!filePath.contains('userIcon.png') }">
												<button type="button" class="btn btn-danger edit" id="delPfPhoto">프로필 사진 삭제</button>
											</c:when>
											<c:otherwise>
												<span class="edit">선택한 프로필 사진을 취소하고 싶은 경우 사진을 더블클릭 해주세요.</span>
											</c:otherwise>
										</c:choose> --%>
									</div>
									<div class="col-3">
										<c:choose>
											<c:when test="${!filePath.contains('userIcon.png') }">
												<button type="button" class="btn btn-danger edit" id="delPfPhoto">프로필 사진 삭제</button>
											</c:when>
											<c:otherwise>
												<span class="edit">선택한 프로필 사진을 취소하고 싶은 경우 사진을 더블클릭 해주세요.</span>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="row">
									<div class="col-2 editOption">
										<span>ID</span>
									</div>
									<div class="col-7">
										<span class="view">${dto.id}</span>
										<input type="text" class="form-control edit" name="id" value="${dto.id }">
									</div>
									<div class="col-3"></div>
								</div>
								<div class="row">
									<div class="col-2 editOption">
										<span>이름</span>
									</div>
									<div class="col-7">
										<span class="view">${dto.name}</span>
										<input type="text" class="form-control edit" name="name" value="${dto.name }">
									</div>
									<div class="col-3"></div>
								</div>
								<div class="row">
									<div class="col-2 editOption">
										<span>닉네임</span>
									</div>
									<div class="col-7">
										<span class="view">${dto.nickname}</span>
										<input type="text" class="form-control edit" name="nickname" value="${dto.nickname }">
									</div>
									<div class="col-3"></div>
								</div>
								<div class="row">
									<div class="col-2 editOption">
										<span>주소</span>
									</div>
									<div class="col-7">
										<span class="view">${dto.address}</span>
										<input type="text" class="form-control edit" name="address" value="${dto.address }">
									</div>
									<div class="col-3"></div>
								</div>
								<div class="row">
									<div class="col-2 editOption">
										<span>이메일</span>
									</div>
									<div class="col-7">
										<span class="view">${dto.email}</span>
										<input type="text" class="form-control edit" name="email" value="${dto.email }">
										<input type="hidden" id="sa" value="${dto.socialAccount }">
									</div>
									<div class="col-3">
										<c:if test="${dto.socialAccount eq '' or dto.socialAccount eq null}">
											<button type="button" class="btn btn-info" id="emailCheckBtn">이메일 중복검사</button>									
										</c:if>
									</div>
								</div>								
								<div class="row">
									<div class="col-2 editOption">
										<span>전화번호</span>
									</div>
									<div class="col-7 view">
										<span>${dto.phone.substring(0,3)} - ${dto.phone.substring(3,7)} - ${dto.phone.substring(7) }</span>
										<input type="hidden" id="phone_init" value="${dto.phone }">
									</div>
									<div class="col-7 edit">
										<select class="form-control phone" id="phone1" onchange="changePhone(this)">
											<option value="010">010</option>
											<option value="070">070</option>
											<option value="030">030</option>
											<option value="050">050</option>
											<option value="etc">기타</option>
										</select>
										<input type="text" class="form-control phone" id="phone4" maxlength="4">							
										-
										<input type="text" class="form-control phone" id="phone2" maxlength="4">
										-
										<input type="text" class="form-control phone" id="phone3" maxlength="4">
										<input type="text" class="form-control" name="phone" id="phone" value="">
									</div>
								</div>
								<div class="col-3 btnDiv"></div>
						</div>
						<div class="row btnDiv">
							<c:if test="${dto.status != 2 }">
								<button class="btn btn-success view" type="button" id="toEdit">수정하기</button>	
							</c:if>
							<c:if test="${loginId.equals('devvv')}">
								<button class="btn btn-dark view" type="button" id="showList">목록출력</button>						
								<input type="hidden" id="status" value="${dto.status}">
							</c:if>
							<button class="btn btn-info edit" type="button" form="editMemberForm" id="save">수정요청</button>
							<button class="btn btn-secondary edit" type="button" id="editCancel">취소</button>
						</div>
					
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
    <link rel="stylesheet" href="/resources/css/custom/member.css">
		<script type="text/javascript" src="/resources/js/custom/member/memberView.js"></script>
</body>
</html>