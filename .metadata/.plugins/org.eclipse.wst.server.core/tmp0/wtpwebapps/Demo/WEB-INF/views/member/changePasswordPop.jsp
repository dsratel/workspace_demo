<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script src="/resources/js/rsa/jsbn.js"></script>
<script src="/resources/js/rsa/rsa.js"></script>
<script src="/resources/js/rsa/prng4.js"></script>
<script src="/resources/js/rsa/rng.js"></script>
	
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
	
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Begin Page Content -->
                <div class="container-fluid">
                
                    <!-- Content Row -->
                    <div class="row" style="justify-content:center;">
                		<h1>change password</h1>
                    </div>
                    <div class="row" style="margin-top:10px;justify-content:center;">
                    	<form name="chgPwForm" action="/member/changePassword.do" method="post">
                    		<div class="row">
                				<div class="col">현재 비밀번호</div>
                				<div class="col"><input class="form-control" type="password" id="curPassword"></div>
                			</div>
                			<div class="row" style="margin-top:10px;">
                				<div class="col">수정 비밀번호</div>
                				<div class="col"><input class="form-control" type="password" id="password"></div>
                			</div>
                			<div class="row" style="margin-top:10px;">
                				<div class="col">수정 비밀번호 확인</div>
                				<div class="col"><input class="form-control" type="password" id="password2" onKeyup="editPassword(event);"></div>
                			</div>
	                		<input type="hidden" id="id" name="id" value="${id}">
	                		<input type="hidden" id="pw" name="pw">
	                		<input type="hidden" id="curPw" name="curPw">
							<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}">
							<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}">    		
                		</form>
                    </div>
                    <div class="row" style="margin:20px 0px 10px 0px;justify-content:center;">
                    	<button type="button" class="btn btn-primary" id="editBtn" onclick="editPassword(event)">수정</button>
                		<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="window.close()">취소</button>
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
    
    <script>
	    const getByteLengthOfString = function (s, b, i, c) {
		  for (b = i = 0; (c = s.charCodeAt(i++)); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
		  return b;
		};
		
		// 유효성 검사
		function validCheck(testPw, kind) {
			// 비밀번호 유효성 검사
			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			
			if(testPw.length < 8) {
				alert("비밀번호는 8자리 이상이어야 합니다.");
				return false;
			} else {
					if(!regex.test(testPw)) {
						alert("대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)");
						return false;
					} else {
						// 비밀번호 RSA 암호화
						//// RSAKey 객체 생성
						const rsa = new RSAKey();
						//// 공개키 설정 값 가져오기
						const rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
						const rsaPublicKeyExponent = $("#rsaPublicKeyExponent").val();
						//// RSAKey 객체에 공개키 설정
						rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
						//// RSAKey 객체의 encrypt메서드 이용하여 비밀번호 암호화
						const securedPassword = rsa.encrypt(testPw);
/* 						console.log("입력 비밀번호 : " + testPw);
						console.log("암호화 비밀번호 : " + securedPassword);
						console.log("암호화 길이 : " + getByteLengthOfString(securedPassword) + "Bytes"); */
						
						$("#"+kind).val(securedPassword);
						return true;
					}
				
			}
		}
		
		// 저장할 때 모든 데이터 유효성 검사 후 input hidden에 넣고 서버로 요청
		function editPassword(e) {
			if(e.keyCode == 13 || e.type == "click") {
				var curPw = $("#curPassword").val();
				var pw1 = $("#password").val();
				var pw2 = $("#password2").val();
				
				// 1. 현재 비밀번호 유효성 검사
				if(!validCheck(curPw, "curPw")) {
					alert("현재 비밀번호를 다시 입력해 주세요.");
					return ;
				} else {
					// 2. 수정 비밀번호와 비밀번호 확인 일치 여부 검사
					if(!new RegExp(pw1).test(pw2)) {
						alert("비밀번호와 비밀번호 확인이 일치해야 합니다.");
						return false;
					} else {
						// 3. 비밀번호 유효성 검사
						if(validCheck(pw1, "pw")) {
							console.log("비밀번호 수정");
							if(confirm("비밀번호를 수정하시겠습니까?")) $("form[name='chgPwForm']").submit();							
						}
					}
				}
			}
		};
    </script>
	
</body>
</html>