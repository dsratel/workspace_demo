$("#category").css("width", "60%");

var masteryn = $("#masteryn").val();
var loginId = $("#loginId").val();

// 사이드바 - 회원 탈퇴
$("#signOut").click(function(){
	if(masteryn != "y") {
		if(confirm("정말로 회원 탈퇴하시겠습니까?")) {
			if(confirm("회원 탈퇴 후에는 정보를 복구할 수 없습니다.")) {
				var tempForm = $("<form></form>");
				var loginId = $("#loginId").val();
				
				tempForm.attr("name", "delMember");
				tempForm.attr("method", "post");
				tempForm.attr("action", "/member/delMember.do");
				tempForm.append($("<input/>",{type:"text",name:"id",value:loginId}));
				tempForm.append($("<input/>",{type:"text",name:"self",value:"y"}));
				
				//tempForm.appendTo("body");
				
				tempForm.submit();
			}
		}
	} else {
		window.location.replace("/member/list?status=2");
	}
});

function moveToHome() {
	window.location.replace("/");
}