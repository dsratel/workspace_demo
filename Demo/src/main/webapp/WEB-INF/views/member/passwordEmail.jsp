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
<body class="bg-gradient-primary">
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
																				<input type="text" class="form-control" id="id" name="id" placeholder="Enter Id...">
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
				console.log($("#id").val().trim());
				console.log($("#email").val().trim());
				if($("#id").val().trim().length > 5 && new RegExp(/^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,3}$/).test($("#email").val().trim())) {
					console.log("id, 비밀번호 유효성검사 통과");
					$.ajax({
						type: "post", 
						url: "/member/userByIdemail",
						data: $("form[name='passwordResetForm']").serialize(),
						success: function(rs){
							if(rs > 0) {
//								window.location.replace("/password/resetPassword");
									kakaoMessage();
							} else {
								alert("일치하는 회원 정보가 없습니다. 입력하신 ID와 EMAIL을 다시 확인해주시기 바랍니다.");
							}
						},
						error: function(rs){
							alert("통신 오류가 발생하였습니다. 다시 시도해 주시기 바랍니다.");
						}
					}) 
				}
				
			});
		});
		
		function kakaoMessage() {
			/*
			$.ajax({
				type: "post",
				url: 'https://kapi.kakao.com/v1/api/talk/friends/message/default/send',
				data: {
				  
				},
				success: function(rs){
					  alert("성공");
					  console.log(rs);	  
				},
				error: function(rs){
					  alert("실패");
					  console.log(rs);
				}
				  
			});
			*/
			
			Kakao.API.request({
				  url: '/v1/api/talk/friends/message/default/send',
				  data: {
				    receiver_uuids: ['${RECEIVER_UUID}'],
				    template_object: {
				      object_type: 'feed',
				      content: {
				        title: '딸기 치즈 케익',
				        description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
				        image_url:
				          'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        link: {
				          web_url: 'https://developers.kakao.com',
				          mobile_web_url: 'https://developers.kakao.com',
				        },
				      },
				      item_content: {
				        profile_text: 'Kakao',
				        profile_image_url: 'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        title_image_url: 'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        title_image_text: 'Cheese cake',
				        title_image_category: 'Cake',
				        items: [
				          {
				            item: 'Cake1',
				            item_op: '1000원',
				          },
				          {
				            item: 'Cake2',
				            item_op: '2000원',
				          },
				          {
				            item: 'Cake3',
				            item_op: '3000원',
				          },
				          {
				            item: 'Cake4',
				            item_op: '4000원',
				          },
				          {
				            item: 'Cake5',
				            item_op: '5000원',
				          },
				        ],
				        sum: 'Total',
				        sum_op: '15000원',
				      },
				      social: {
				        like_count: 100,
				        comment_count: 200,
				      },
				      buttons: [
				        {
				          title: '웹으로 보기',
				          link: {
				            mobile_web_url: 'https://developers.kakao.com',
				            web_url: 'https://developers.kakao.com',
				          },
				        },
				        {
				          title: '앱으로 보기',
				          link: {
				            mobile_web_url: 'https://developers.kakao.com',
				            web_url: 'https://developers.kakao.com',
				          },
				        },
				      ],
				    },
				  },
				})
				  .then(function(response) {
				    console.log(response);
				  })
				  .catch(function(error) {
				    console.log(error);
				  });
		}

	</script>
</body>
</html>