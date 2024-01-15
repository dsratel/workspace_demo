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
<body class="bg-gradient-light">
	<form action="/member/insertMember.do" method="post" name="insertMember" id="insertMemberForm" enctype="multipart/form-data">
	    <div class="container">
	
	        <!-- Outer Row -->
	        <div class="row justify-content-center">
	
	            <div class="col-xl-12 col-lg-12 col-md-9">
	
	                <div class="card o-hidden border-0 shadow-lg my-5">
	                    <div class="card-body p-0">
	                        <!-- Nested Row within Card Body -->
	                        <div class="row">
														<div class="col-12" style="padding:20px;">
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>프로필 사진</span>
																	</div>
																	<div class="col-6">
																		<input type="file" class="form-control" id="m_pf" onchange="readURL(this)">
																	</div>
																	<div class="col-3">
																		<img class="img-thumbnail" src="/repository/userIcon.png" id="preview"/ style="width:50px;height:50px;">
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>ID</span>
																	</div>
																	<div class="col-6">
																		<input type="text" class="form-control" id="m_id">
																	</div>
																	<div class="col-3">
																		<button type="button" class="btn btn-secondary" id="idCheckBtn">중복검사</button>
																		<span></span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>비밀번호</span>
																	</div>
																	<div class="col-6">
																		<input type="password" class="form-control" id="m_pw">
																		<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}">
																		<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}">
																	</div>
																	<div class="col-3">
																		<span>8자리 이상 - ??(대문자, 숫자, 특수문자 포함)</span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>이름</span>
																	</div>
																	<div class="col-6">
																		<input type="text" class="form-control" id="m_name">
																	</div>
																	<div class="col-3">
																		<span>두 글자 이상</span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>닉네임</span>
																	</div>
																	<div class="col-6">
																		<input type="text" class="form-control" id="m_nickname">
																	</div>
																	<div class="col-3">
																		<span>두 글자 이상</span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>주소</span>
																	</div>
																	<div class="col-6">
																		<input type="text" class="form-control" id="m_address">
																	</div>
																	<div class="col-3">
																		<span></span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>이메일</span>
																	</div>
																	<div class="col-6">
																		<input type="text" class="form-control" id="m_email">
																	</div>
																	<div class="col-3">
																		<button type="button" class="btn btn-secondary" id="emailCheckBtn">중복검사</button>
																		<span></span>
																	</div>
																</div>
																<div class="row" style="padding: 5px 0px 5px">
																	<div class="col-3">
																		<span>전화번호</span>
																	</div>
																	<div class="col-6">
																		<select class="form-control phone" id="m_phone1" onchange="changePhone(this)">
																			<option value="010">010</option>
																			<option value="070">070</option>
																			<option value="030">030</option>
																			<option value="050">050</option>
																			<option value="etc">기타</option>
																		</select>
																		<input type="text" class="form-control phone" id="m_phone4" maxlength="4">							
																		-
																		<input type="text" class="form-control phone" id="m_phone2" maxlength="4">
																		-
																		<input type="text" class="form-control phone" id="m_phone3" maxlength="4">
																	</div>
																	<div class="col-3">
																		<span><button class="btn btn-primary" type="button" form="insertMember" id="save">저장하기</button></span>
																		<span><button class="btn btn-warning" type="button" id="toHome">홈으로</button></span>
																	</div>
																</div>
														</div>
														<!-- <div class="col-2" style="border:1px solid; padding: 20px;">
															<button class="btn btn-primary" type="button" form="insertMember" id="save">저장하기</button><br>
														</div> -->
													</div>
													<div id="formDiv">
															<div class="row" style="padding: 5px 0px 5px">
																	<input type="file" name="upfile" class="form-control" id="upfile">
																	<input type="text" class="form-control" name="id" id="id">
																	<input type="password" class="form-control" name="pw" id="pw">
																	<input type="text" class="form-control" name="name" id="name">
																	<input type="text" class="form-control" name="nickname" id="nickname">
																	<input type="text" class="form-control" name="address" id="address">
																	<input type="text" class="form-control" name="email" id="email">
																	<input type="text" class="form-control" name="phone" id="phone">
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
	
		<!-- Custom for this page -->
    <link rel="stylesheet" href="/resources/css/custom/member.css">
		<script type="text/javascript" src="/resources/js/custom/member/signUp.js"></script>
</body>
</html>