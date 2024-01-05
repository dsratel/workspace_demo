<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="data:image/x-icon;">
	<!-- Bootstrap core JavaScript-->
	<script src="/resources/template/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/template/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
	<!-- Custom template -->
	<link rel="stylesheet" href="/resources/template/css/sb-admin-2.min.css">
	<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="/resources/template/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	
	<!-- Encryption -->
	<script src="/resources/js/rsa/jsbn.js"></script>
	<script src="/resources/js/rsa/rsa.js"></script>
	<script src="/resources/js/rsa/prng4.js"></script>
	<script src="/resources/js/rsa/rng.js"></script>
	<title>login page</title>
</head>
<body class="bg-gradient-primary">
	<form name="loginForm" method="post" action="/member/login.do" id="loginForm">
	    <div class="container">
	
	        <!-- Outer Row -->
	        <div class="row justify-content-center">
	
	            <div class="col-xl-10 col-lg-12 col-md-9">
	
	                <div class="card o-hidden border-0 shadow-lg my-5">
	                    <div class="card-body p-0">
	                        <!-- Nested Row within Card Body -->
	                        <div class="row">
	                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
	                            <div class="col-lg-6">
	                                <div class="p-5">
	                                    <div class="text-center">
	                                        <h1 class="h4 text-gray-900 mb-4">Welcome to NOLBU!</h1>
	                                    </div>
	                                        <div class="form-group">
	                                            <input type="text" class="form-control form-control-user"
	                                                id="id" name="id" aria-describedby="emailHelp"
	                                                placeholder="Enter id...">
	                                        </div>
	                                        <div class="form-group">
	                                            <input type="password" class="form-control form-control-user" id="password" placeholder="Password" onkeyup="enterkey()">
																							<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}">
																							<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}">
																							<input type="hidden" name="pw" id="pw"> 
	                                        </div>
	                                        <c:if test="${!requestURI.equals('') }">
												<input type="hidden" name="requestURI" value="${requestURI }">
											</c:if>	
	                                        <div class="form-group">
	                                            <div class="custom-control custom-checkbox small">
	                                                <input type="checkbox" name="rememberId" class="custom-control-input" id="customCheck" value="rememberId">
	                                                <label class="custom-control-label" for="customCheck">Remember Me</label>
	                                            </div>
	                                        </div>
	                                        
	                                        <button type="button" id="loginBtn" class="btn btn-primary btn-user btn-block" onclick="login();">
	                                            Login
	                                        </button>
	                                        <hr>
	                                        <button type="button" class="btn btn-red btn-user btn-block" id="loginGoogle">
	                                            <i class="fa af-google" aria-hidden="true"></i>&nbsp;&nbsp;Google
	                                        </button>	                                        
	                                        <button type="button" class="btn btn-red btn-user btn-block" id="signUpBtn">
	                                            <i class="fa fa-id-badge" aria-hidden="true"></i>&nbsp;&nbsp;SignUp
	                                        </button>
	                                        <button type="button" class="btn btn-blue btn-user btn-block" id="listBtn">
	                                            <i class="fa fa-list-alt" aria-hidden="true"></i>&nbsp;&nbsp;Board List
	                                        </button>
	                                    <hr>
	                                    <div class="text-center">
	                                        <a class="small" href="/member/forgotPassword">Forgot Password?</a>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	
	            </div>
	
	        </div>
	
	    </div>
	</form>
	
	<!-- validation Modal-->
	
	<!-- validation Modal-->
    <div class="modal fade" id="validationModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalLabel">Check your password</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body" id="modalContent">You need to write more than 7 letters on password</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Try Again</button>
                </div>
            </div>
        </div>
    </div>
	
	
	
	<script>
		$(function(){
			// css
			$("div.container, table, #BtnBox").css({"text-align" : "center", "vertical-align" : "middle"});
			
			// 회원가입 버튼
			$("#signUpBtn").click(function(){
				window.location.replace("/member/signUp");
			});
			
			// 글 미리보기 버튼
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});
			
			// remember Me 체크 한 경우
			var cookie = document.cookie;
			var ckName = "rememberId=";
			var ckVal  = cookie.substr(cookie.lastIndexOf(ckName) + ckName.length);
			if(ckVal.length > 0) {
				$("#id").val(ckVal);
				$("#customCheck").prop("checked", true);
			}
			
			$("#customCheck").change(function(){
				if(!$("#customCheck").is(":checked")){
					var date = new Date();
					date.setTime(date.getTime() + (-1 * 24 * 60 * 60 * 1000));
					
					document.cookie = 'rememberId=; domain=.demo.com; expires=' + date.toUTCString() + '; path=/;'
					$("#id").val("");
				}
			});
			
			// googleLogin 버튼
			$("#loginGoogle").click(function(){
				$.ajax({
					type : "post",
					url : "/api/google-login",
					data : {},
					success : function(rs){
						loginGoogle(rs);
					},
					error : function(rs){
						alert("URL 받아오기 실패");
					}
				})
				
				function loginGoogle(url){
					var width = 470;
					var height = 620;
					var popupX = (window.screen.width/2) - (width/2);
					var popupY = (window.screen.height/2) - (height/2);
					console.log(width + ' / ' + height + ' / ' + popupX + ' / ' + popupY);
					window.open(url, 'google login popup', 'width=' + width + ',height=' + height + ',left=' + popupX + ',top=' + popupY);
				}
			});
		})	// function() end
		
		// 비밀번호에서 엔터
		function enterkey() {
			if(window.event.keyCode == 13) login();
		}
		
		// 로그인 버튼
		function login() {
			
			// ID 유효성 검사
			if($("#id").val().length < 5) {
				$("#ModalLabel").text("Check your ID");
				$("#modalContent").text("Required 4 or more letters for ID");
				$("#validationModal").modal("show");
				return ;
			}
			
			// 비밀번호 유효성 검사
			var testPw = $("#password").val();
			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			if(testPw.length < 8) {
				//alert("비밀번호는 8자리 이상이어야 합니다.");
				$("#ModalLabel").text("Check your password");
				$("#modalContent").text("Required 8 or more letters for password");
				$("#validationModal").modal("show");
				return;
			} else {
				if(!regex.test(testPw)){
					//alert("대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)");
					$("#ModalLabel").text("Check your password");
					$("#modalContent").text("Required one of each upper, lower, special characters and number for password");
					$("#validationModal").modal("show");
					return;
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
					
					$("#pw").val(securedPassword);
				}
			}
			
			$("#loginForm").submit();	
		}
	</script>
</body>
</html>