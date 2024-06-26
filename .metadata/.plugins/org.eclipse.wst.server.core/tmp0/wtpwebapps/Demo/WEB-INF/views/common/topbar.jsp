<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Topbar Search -->
                    <!-- <form method="get" action="/board/toList" name="searchForm" id="searchForm"f >
                        <div class="search">
                    	
	                        <select class="form-control" name="category" id="category" onchange="changeCategory(this)">
								<option value="free">자유게시판</option>
								<option value="notice">공지사항</option>
							</select>
							<select class="form-control" name="searchType" id="searchType">
								<option value="0">제목</option>
								<option value="1">작성자</option>
							</select>	
                    	
                    	
                            <input type="text" name="searchKeyword" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                                <button class="btn btn-primary" type="button" id="searchBtn">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            <span>페이지 수</span>
									<select class="form-control" name="pageSize" id="pageSize" onchange="changePageSize(this)">
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select>
                        </div>
                    </form> -->

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
												<c:if test="${loginId ne '' && loginId ne null}">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${loginId }</span>
                                <img class="img-profile rounded-circle"
                                    src="${pfPath.equals('') || pfPath == null ? '/resources/template/img/undraw_profile.svg' : pfPath} ">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <c:if test="${!loginId.equals('devvv')}">
	                                <a class="dropdown-item" href="/member/toViewMember?id=${loginId}">
	                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
	                                    Profile
	                                </a>
                                <div class="dropdown-divider"></div>                                
                                </c:if>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
												</c:if>
                    </ul>

                </nav>
                <!-- End of Topbar -->
                
                <!-- Logout Modal-->
			    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel"
			        aria-hidden="true">
			        <div class="modal-dialog" role="document">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <h5 class="modal-title" id="ModalLabel">Ready to Leave?</h5>
			                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
			                        <span aria-hidden="true">×</span>
			                    </button>
			                </div>
			                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
			                <div class="modal-footer">
			                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
			                    <a class="btn btn-primary" href="/common/logout">Logout</a>
			                </div>
			            </div>
			        </div>
			    </div>