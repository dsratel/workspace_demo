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
  
  <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.js"></script>
    
	<!-- Custom template -->
	<link rel="stylesheet" href="/resources/template/css/sb-admin-2.min.css">
	<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="/resources/template/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	

	<title>login page</title>
</head>
<body class="bg-gradient-light">
	<form name="passwordResetForm" method="post" id="passwordResetForm">
	    <div class="container">
	
	        <!-- Outer Row -->
	        <div class="row justify-content-center">
	
	            <div class="col-xl-10 col-lg-12 col-md-9">
	
	                <div class="card o-hidden border-0 shadow-lg my-5">
	                    <div class="card-body p-0">
	                        <!-- Nested Row within Card Body -->
													<div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                        <p class="mb-4">We get it, stuff happens. Just enter your email address below
                                            and we'll send you a link to reset your password!</p>
                                    </div>
	                                    <div class="form-group">
																				<input type="text" class="form-control mb-2" id="id" name="id" placeholder="Enter Id...">
																				<input type="email" class="form-control" id="email" name="email" placeholder="Enter Email Address...">
	                                    </div>
	                                    <a id="resetBtn" class="btn btn-primary btn-user btn-block">
	                                        Reset Password
	                                    </a>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="/member/signUp">Create an Account!</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="/">Already have an account? Login!</a>
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
	
	<script>
		$(function(){
			// 비밀번호 초기화 버튼
			$("#resetBtn").click(function(){
				if($("#id").val().trim().length > 4 && new RegExp(/^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,3}$/).test($("#email").val().trim())) {
					$.ajax({
						type: "post", 
						url: "/member/userByIdEmail",
						data: $("form[name='passwordResetForm']").serialize(),
						success: function(rs){
							if(rs > 0) {
//								window.location.replace("/password/resetPassword");
									sendEmail();
							} else {
								alert("일치하는 회원 정보가 없습니다. 입력하신 ID와 EMAIL을 다시 확인해주시기 바랍니다.");
							}
						},
						error: function(rs){
							alert("통신 오류가 발생하였습니다. 다시 시도해 주시기 바랍니다.");
						}
					}) 
				} else {
					alert("check your id and email address please");
				}
				
			});
		});
		
		function sendEmail() {
			$("#passwordResetForm").attr("action", "/password/sendURL");
			alert($("#email").val().trim() + " 로 비밀번호 변경 링크를 전송합니다.").
			$("#passwordResetForm").submit();
		}


	</script>
</body>
</html>