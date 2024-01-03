<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" style="cursor:default;">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-grin-squint"></i>
                </div>
                <div class="sidebar-brand-text mx-3">NOLBU <sup>v1</sup></div>
                
            </a>
            <c:if test="${loginId eq '' || loginId eq null }">
            	<a class="sidebar-brand d-flex align-items-center justify-content-center"  style="cursor:pointer;">
               <div class="sidebar-brand-text mx-3 sideHome" onclick="moveToHome();">Move to home</div>
              </a>
            </c:if>

						<c:if test="${loginId ne '' && loginId ne null }">
            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            
           	<c:choose>
           		<c:when test="${masteryn eq 'y'.charAt(0)}">
           			<!-- Nav Item - Dashboard -->
		            <li class="nav-item active">
						<a class="nav-link" href="/master/home">
		                    <i class='fas fa-stream'></i>
		                    <span>MASTER MENU</span>
						</a>            		
		            </li>
           		</c:when>
          	</c:choose>

            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Heading -->
            <div class="sidebar-heading">
                USER
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-solid fa-user"></i>
                    <span>${masteryn eq 'y'.charAt(0) ? 'List' : 'MyPage'}</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">USER INFO:</h6>
                        <c:choose>
                        	<c:when test="${masteryn eq 'y'.charAt(0)}">
                        		<a class="collapse-item" href="/master/toMemberList">Member</a>	
                        	</c:when>
                        	<c:otherwise>
                        		<a class="collapse-item" href="/member/toViewMember?id=${loginId}">Profile</a>
                        	</c:otherwise>
                        </c:choose>
                        <a class="collapse-item" id="signOut">Sign out</a>
                    </div>
                </div>
            </li>
            
			<!-- Nav Item - Reset Password -->
            <li class="nav-item">
                <a class="nav-link" href="/member/changePassword?id=${loginId}" onclick="window.open(this.href, 'password change popup', 'width=540, height=350, left=610, top=340'); return false;">
                    <i class="fas fa-solid fa-key"></i>
                    <span> Reset Password</span>
				</a>
            </li>

			<!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Board
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-regular fa-clipboard"></i>
                    <span>My Board</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Article:</h6>
                        <a class="collapse-item" href="/board/toList">board list</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Comment:</h6>
                        <a class="collapse-item" href="/comment/myList?id=${loginId}">comment list</a>
                    </div>
                </div>
            </li>

						
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
            
            <%-- <c:if test="${masteryn eq 'y'.charAt(0)}">
	            <!-- Nav Item - attachfile -->
	            <li class="nav-item">
	                <a class="nav-link">
	                    <i class="fas fa-solid fa-file"></i>
	                    <span>files</span>
                    </a>
	            </li>
			</c:if> --%>

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline" style="padding-top:20px;">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
						
						</c:if>
        </ul>
        <!-- End of Sidebar -->