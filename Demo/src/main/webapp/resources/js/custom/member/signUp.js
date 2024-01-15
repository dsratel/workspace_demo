		// 저장하기
		/*
        $("#save").click(function(){
        
			//$("#insertTest").submit();
  			$.ajax({
				url: "./member/insertMember.do",
				contentType:'application/json',
				type: "post",
				data: JSON.stringify({
					m_id: $("input[name=m_id]").val(),
					m_pw: $("input[name=m_pw]").val(),
					m_nickname: $("input[name=m_nickname]").val(),
					m_name: $("input[name=m_name]").val(),
					m_address: $("input[name=m_address]").val(),
					m_phone: $("input[name=m_phone]").val()
				}),
				success:function(data){
					alert("success");
					window.location.replace("/member/list");
				},
				error:function(){
					alert("error");
				}
			});
		}); 
		*/
		
		const getByteLengthOfString = function (s, b, i, c) {
			  for (b = i = 0; (c = s.charCodeAt(i++)); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
			  return b;
		};
		
		// 저장할 때 모든 데이터 유효성 검사 후 input hidden에 넣고 서버로 요청
		$("#save").click(function(){
			// 유효성 검사
			//// 1. 프로필 사진 -> 프로필 사진 선택할 때 <input type="file">에 img 주입.

			//// 2. ID -> ID 중복검사 할 때 <input type="text">에 주입.
			if($("#id").val().length < 5) {
				alert("ID를 입력 후 중복검사를 해주세요.");
				return;
			}
			
			//// 3. 비밀번호
			var testPw = $("#m_pw").val();
			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			if(testPw.length < 8) {
				alert("비밀번호는 8자리 이상이어야 합니다.");
				return;
			} else {
				if(!regex.test(testPw)){
					alert("대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)");
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
					console.log("입력 비밀번호 : " + testPw);
					console.log("암호화 비밀번호 : " + securedPassword);
					console.log("암호화 길이 : " + getByteLengthOfString(securedPassword) + "Bytes");
					
					$("#pw").val(securedPassword);
				}
			}
			
			//// 4. 이름
			var testName = $("#m_name").val().trim();
			if(testName.length < 2) {
				alert("이름은 2자리 이상이어야 합니다.");
				return;
			} else {
				$("#name").val(testName);
			}
			
			//// 5. 닉네임
			var testNickname = $("#m_nickname").val().trim();
			if(testNickname.length < 2) {
				alert("닉네임은 2자리 이상이어야 합니다.");
				return;
			} else {
				$("#nickname").val(testNickname);
			}

			//// 6. 주소
			var testAddress = $("#m_address").val().trim();
			if(testAddress.length < 1) {
				alert("주소를 입력해주세요.");
				return;
			} else {
				$("#address").val(testAddress);
			}
			console.log("address valid check");
			
			//// 7. EMAIL -> EMAIL 중복검사 할 때 <input type="text">에 주입.
			var testEmail = $("#email").val().trim();
			if(!new RegExp(/^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,3}$/).test(testEmail)) {
				alert("EMAIL을 입력 후 중복검사를 해주세요.");
				return;
			}
			
			//// 8. 전화번호
			var regExp = /^[0-9]{4}$/;
			var testPhone = "";
 			if(!regExp.test($("#m_phone2").val()) || $("#m_phone2").val().length < 4 || !regExp.test($("#m_phone3").val()) || $("#m_phone3").val().length < 4) {
				// 2번째 3번째 번호가 4자리 보다 작을 때 리턴
				alert("전화번호를 확인하세요. 각 칸마다 4자리 확인.");
				return ;
			} else {
				// 1번째 번호가 기타인 경우
				regExp = /^[0-9]{3,4}$/;
				if($("#m_phone1").val() == "etc") {
					if(!regExp.test($("#m_phone4").val()) || $("#m_phone4").val().length < 3) {
						alert("첫 번째 자리 번호를 확인하세요.");
						return;
					}
					testPhone = $("#m_phone4").val() + $("#m_phone2").val() + $("#m_phone3").val();
				} else {
					testPhone = $("#m_phone1").val() + $("#m_phone2").val() + $("#m_phone3").val();
				}
			}
 			
 			// <form> 값 대입
 			//// 전화번호
 			$("#phone").val(testPhone);
 			
 			$("#insertMemberForm").submit();
		});

		// ID 중복검사
		$("#idCheckBtn").click(function(){
			var testId = $("#m_id").val().trim();
			if(testId.length > 4) {
				$.ajax({
					url: "/member/checkId",
					dataType: "text",
					type: "get",
					data: {id :testId },
					success: function(data) {
						if(data > 0) {
							// 중복되는 ID가 있는 경우
							alert("중복되는 ID가 있습니다.");
							$("#m_id").val("");
						} else {
							// 중복되는 ID가 없는 경우 <form> 하위 <input>에 값 주입
							alert("ID를 사용할 수 있습니다.");
							$("#idCheckBtn").hide();
							$("#id").val(testId);				
						}
					},
					error: function(data) {
						console.log("실패");
						console.log(data);
					}
				});
			} else {
				// 규칙에 맞지 않는 ID 중복 검사 한 경우 input 값 지우기
				alert("ID는 5글자 이상 이어야 합니다.");
				$("#m_id").val("");
				$("#id").val("");
			}
			
		});
		
		// ID 입력창의 내용 변경 시 중복검사 재 실시
		$("#m_id").keyup(function(rs){
			if($("#idCheckBtn").css("display") == 'none') {
				$("#id").val("");
				$("#idCheckBtn").show(); 
			}
		});
		
		// email 중복검사
		$("#emailCheckBtn").click(function(){
			var testEmail = $("#m_email").val().trim();
			if(new RegExp(/^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,3}$/).test(testEmail)) {
				$.ajax({
					url: "/member/checkEmail",
					dataType: "text",
					type: "get",
					data: {email: testEmail},
					success: function(data) {
						if(data > 0) {
							// 중복되는 ID가 있는 경우
							alert("중복되는 EMAIL이 있습니다.");
							$("#m_email").val("");
						} else {
							// 중복되는 ID가 없는 경우 <form> 하위 <input>에 값 주입
							alert("EMAIL을 사용할 수 있습니다.");
							$("#emailCheckBtn").hide();
							$("#email").val(testEmail);				
						}
					},
					error: function(data) {
						console.log("실패");
						console.log(data);
					}
				});
			} else {
				// 규칙에 맞지 않는 EMAIL 중복 검사 한 경우 input 값 지우기
				alert("EMAIL을 aaa@bbb.ccc 같은 형식으로 입력해주세요.");
				$("#m_email").val("");
				$("#email").val("");
			}
		});
		
		// EMAIL 입력창의 내용 변경 시 중복검사 재 실시
		$("#m_email").keyup(function(rs){
			if($("#emailCheckBtn").css("display") == 'none') {
				$("#email").val("");
				$("#emailCheckBtn").show(); 
			}
		});		
		
		// 홈으로 이동 버튼
		$("#toHome").click(function(){
			window.location.replace("/");
		});
		
		
			// 프로필 사진 미리보기
	function readURL(input) {
		if(input.files && input.files[0]) {
			/* console.log("input.files\n");
			console.log(input.files);
			console.log("input.files[0]\n")
			console.log(input.files[0]);
			console.log("$('upfile').files")
			console.log($("#upfile").files); */
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview").attr({"src":e.target.result}).css({"width":"50px", "height":"50px"});
				/* console.log("프로필 사진 미리보기\n");
				console.log(e);
				console.log("$('#upfile')[0].files = input.files;"); */
				$("#upfile")[0].files = input.files;
			}
			reader.readAsDataURL(input.files[0]);
			
		} else {
			$("#presview").attr("src", "/resources/userIcon.png");
		}
	};
	
	// 폰 앞자리가 기타인 경우
	function changePhone(input) {
		if(input.value == "etc") {
			$("#m_phone4").show();
			$("#m_phone1").css("display", "block");
		} else {
			$("#m_phone4").val('');
			$("#m_phone4").hide();
			$("#m_phone1").css("display", "inline");
		}
	};