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
                    <div class="row contentRow">
					
							<form action="/member/editMember.do" method="post" name="editMemberForm" id="editMemberForm" enctype="multipart/form-data">
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>프로필 사진</span>
									</div>
									<div class="col-9">
										<img class="img-thumbnail" src="${filePath  }" fn="${dto.fileno }" id="preview"/>
										<input type="file" class="form-control" id="profileInput" name="profilePhoto" onchange="readURL(this)";>
										<c:choose>
											<c:when test="${!filePath.contains('userIcon.png') }">
												<button type="button" class="btn btn-danger" id="delPfPhoto">프로필 사진 삭제</button>
											</c:when>
											<c:otherwise>
												<span>선택한 프로필 사진을 취소하고 싶은 경우 사진을 더블클릭 해주세요.</span>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>ID</span>
									</div>
									<div class="col-9">
										<input type="text" class="form-control" name="id" value="${dto.id }" readonly>
									</div>
								</div>
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>이름</span>
									</div>
									<div class="col-9">
										<input type="text" class="form-control" name="name" value="${dto.name }">
									</div>
								</div>
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>닉네임</span>
									</div>
									<div class="col-9">
										<input type="text" class="form-control" name="nickname" value="${dto.nickname }">
									</div>
								</div>
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>주소</span>
									</div>
									<div class="col-9">
										<input type="text" class="form-control" name="address" value="${dto.address }">
									</div>
								</div>
								<div class="row" style="padding: 5px 0px 5px">
									<div class="col-3 editOption">
										<span>전화번호</span>
									</div>
									<div class="col-9">
										<select class="form-select phone" id="phone1" onchange="changePhone(this)">
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
							</form>
						</div>
						<div class="row btnDiv">
							<button class="btn btn-success" type="button" form="editMemberForm" id="save">수정하기</button>
							<button class="btn btn-dark" type="button" id="showList">목록출력</button>
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
		

    <!-- Custom scripts for all pages-->
    <script src="/resources/template/js/sb-admin-2.min.js"></script>
	<script>
	$(function(){
		
		// 목록출력
		$("#showList").click(function(){
			window.location.replace("/member/list");
		});
		
		// css
		//// ID input 태그 색상 변경
		$("input[name='id']").css({"background-color":"grey", "color":"white"});
		$("#preview").css({"width" : "200px", "height" : "200px"});
		$(".phone").css({"width" : "30%", "display" : "inline"});
		$("#phone4, #phone").hide();
		
		// 프로필 사진 삭제
 		$("#delPfPhoto").click(function(){
			$.ajax({
				type: "get",
				url: "/member/delPfPhoto",
				data: {id : $("input[name='id']").val()},
				success: function(rs){
					alert("프로필 사진 삭제 성공");
					console.log(rs);
					window.location.replace("/member/editMember?id=" + $("input[name='id']").val());
				},
				error: function(rs){
					alert("프로필 사진 삭제 실패");
					console.log(rs);
				}
			});
			
		});
			
		// 휴대폰 번호 세팅
		var phone = "${dto.phone}";
		var p1 = phone.substring(0, phone.length-8);
		var p2 = phone.substring(phone.length-8, phone.length-4);
		var p3 = phone.substring(phone.length-4);
		
		$("#phone1").val(p1);
		$("#phone2").val(p2);
		$("#phone3").val(p3);		
		
		// 수정하기
		$("#save").on("click", function(){
			// 유효성 검사
			//// 1. 비밀번호
			var testPw = $("input[name='pw']").val();
			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			if(testPw.length > 0) {
				if(testPw.length < 8) {
					alert("비밀번호는 8자리 이상이어야 합니다.");
					return;
				} else {
					if(!regex.test(testPw)){
						alert("대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)");
						return;
					} else {
						$("#pw").val(testPw);
					}
				}				
			}
			
			//// 2. 이름
			var testName = $("input[name='name']").val();
			if(testName.length < 2) {
				alert("이름은 2자리 이상이어야 합니다.");
				return;
			} else {
				$("#name").val($("input[name='name']").val());
			}
			
			//// 3. 닉네임
			var testNickname = $("input[name='nickname']").val();
			if(testNickname.length < 2) {
				alert("닉네임은 2자리 이상이어야 합니다.");
				return;
			} else {
				$("#nickname").val($("input[name='nickname']").val());
			}
			
			//// 4. 주소
			var testAddress = $("input[name='address']").val();
			if(testAddress.length < 1) {
				alert("주소를 입력해주세요.");
				return;
			} else {
				$("#address").val($("input[name='address']").val());
			}
			
			//// 5. 전화번호
			var regExp = /^[0-9]{4}$/;
			var testPhone = "";
 			if(!regExp.test($("#phone2").val()) || $("#phone2").val().length < 4 || !regExp.test($("#phone3").val()) || $("#phone3").val().length < 4) {
				// 2번째 3번째 번호가 4자리 보다 작을 때 리턴
				alert("전화번호를 확인하세요. 각 칸마다 4자리 확인.");
				return ;
			} else {
				// 1번째 번호가 기타인 경우
				regExp = /^[0-9]{3,4}$/;
				if($("#phone1").val() == "etc") {
					if(!regExp.test($("#phone4").val()) || $("#phone4").val().length < 3) {
						alert("첫 번째 자리 번호를 확인하세요.");
						return;
					}
					$("#phone").val($("#phone4").val() + $("#phone2").val() + $("#phone3").val());
				} else {
					$("#phone").val($("#phone1").val() + $("#phone2").val() + $("#phone3").val());
				}
			}
			
			
			$("#editMemberForm").submit();
		});
		
		// 사진 더블클릭 시 
		$("#preview").dblclick(function(){
			console.log("더블클릭");
			// 기본이미지로 변경
			$("#preview").attr("src", "/repository/userIcon.png");
			
			// 파일 input 초기화
			$("#profileInput").val("");
		});
		
	}); // function() end
	
	// 사진 미리보기
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();	// FileReader 객체 생성
			reader.onload = function(e) {	// load 이벤트의 핸들러. 읽기 동작이 성공적으로 완료되었을 때마다 발생.
				$("#preview").attr("src", e.target.result);
			};
			reader.readAsDataURL(input.files[0]);	// <input type="file">의 파일을 url로 읽어들인다. 성공적으로 읽혔다면 위의 .onload를 통해 <img>의 src 속성에 url 주입.
		} else {
			$("#preview").attr("src", "");
		}
	}
	
	// 폰 앞자리가 기타인 경우
	function changePhone(input) {
		if(input.value == "etc") {
			$("#phone4").show();
			$("#phone1").css("display", "block");
		} else {
			$("#phone4").val('');
			$("#phone4").hide();
			$("#phone1").css("display", "inline");
		}
	
	}
	</script>
</body>
</html>