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

function sendEmail() {
    $("#passwordResetForm").attr("action", "/password/sendURL");
    alert($("#email").val().trim() + " 로 비밀번호 변경 링크를 전송합니다.").
    $("#passwordResetForm").submit();
}

		
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
						
						$("#"+kind).val(securedPassword);
						return true;
					}
				
			}
		}
		
		// 저장할 때 모든 데이터 유효성 검사 후 input hidden에 넣고 서버로 요청
		function editPassword(e) {
			if(e.keyCode == 13 || e.type == "click") {
				var pw1 = $("#password").val();
				var pw2 = $("#password2").val();
				
				// 1. 현재 비밀번호 유효성 검사
				if(!validCheck(pw1, "pw")) {
					alert("비밀번호를 다시 입력해 주세요.");
					return ;
				} else {
					// 2. 수정 비밀번호와 비밀번호 확인 일치 여부 검사
					if(!new RegExp(pw1).test(pw2)) {
						alert("비밀번호와 비밀번호 확인이 일치해야 합니다.");
						return false;
					} else {
						// 3. 비밀번호 유효성 검사
						if(validCheck(pw1, "pw")) {
							if(confirm("비밀번호를 수정하시겠습니까?")) {
								$("form[name='passwordResetForm']").attr("action", "/password/resetPassword.do");
								$("form[name='passwordResetForm']").submit();
							}
						}
					}
				}
			}
		};