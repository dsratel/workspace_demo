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
	

	<title>reset password page</title>
</head>

<script src="/resources/js/rsa/jsbn.js"></script>
<script src="/resources/js/rsa/rsa.js"></script>
<script src="/resources/js/rsa/prng4.js"></script>
<script src="/resources/js/rsa/rng.js"></script>

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
                                        <h1 class="h4 text-gray-900 mb-2">Reset Password</h1>
                                        <p class="mb-4">We get it, stuff happens. Just enter your email address below
                                            and we'll send you a link to reset your password!</p>
                                    </div>
	                                    <div class="form-group">
	                                    	<input class="form-control mb-2" type="password" id="password" placeholder="Enter password..">
	                                    	<input class="form-control" type="password" id="password2" placeholder="Enter password one more..">
	                                    </div>
	                                    <a id="resetBtn" class="btn btn-primary btn-user btn-block" onclick="editPassword(event)">
	                                        Reset Password
	                                    </a>
                                    <hr>

                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}">
												<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}">
												<input type="hidden" name="link" value="${link}">
	               				<input type="hidden" id="pw" name="pw">
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="/resources/js/custom/passwordReset.js"></script>
</body>
</html>