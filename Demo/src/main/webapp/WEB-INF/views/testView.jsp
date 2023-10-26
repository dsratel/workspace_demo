<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
	<script src="/resources/js/jquery/jquery-3.7.1.min.js"></script>
	<title>TEST_member view page</title>
</head>
<body>
	<div class="container">
		<div class="row" style="margin: 10px;">
			<div class="col-3" style="border:1px solid" id="rs">
				<h1>TEST VIEW PAGE</h1>
			</div>
			<div class="col-6" style="border:1px solid">
				<form action="/member/editMember.do" method="post" name="editMemberForm" id="editMemberForm">
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>프로필 사진</span>
						</div>
						<div class="col-6">
							<img class="img-thumbnail" src="${filePath }" id="preview"/>
							<input type="file" onchange="readURL(this)";>
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>ID</span>
						</div>
						<div class="col-6">
							<input type="text" class="form-control" name="m_id" value="${dto.m_id }" readonly>
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>비밀번호</span>
						</div>
						<div class="col-6">
							<input type="password" class="form-control" name="m_pw" value="${dto.m_pw }">
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>닉네임</span>
						</div>
						<div class="col-6">
							<input type="text" class="form-control" name="m_nickname" value="${dto.m_nickname }">
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>이름</span>
						</div>
						<div class="col-6">
							<input type="text" class="form-control" name="m_name" value="${dto.m_name }">
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>주소</span>
						</div>
						<div class="col-6">
							<input type="text" class="form-control" name="m_address" value="${dto.m_address }">
						</div>
					</div>
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>전화번호</span>
						</div>
						<div class="col-6">
							<input type="text" class="form-control" name="m_phone" value="${dto.m_phone }">
						</div>
					</div>
				</form>
			</div>
			<div class="col-3" style="border:1px solid; padding: 2px;">
				<button class="btn btn-success" type="button" form="editMemberForm" id="save">수정하기</button>
				<button class="btn btn-dark" type="button" id="showList">목록출력</button>
			</div>
		</div>
	</div>

	<script>
	$(function(){
		
		// 수정하기
		$("#save").on("click", function(){
/* 			var data = JSON.stringify({m_id: $("input[name=m_id]").val(), m_pw: $("input[name=m_pw]").val(), m_nickname: $("input[name=m_nickname]").val(),
												m_name: $("input[name=m_name]").val(), m_address: $("input[name=m_address]").val(), m_phone: $("input[name=m_phone]").val()
										});
			console.log(data);
			$("<input type='hidden' name='memberDto' value='" + data + "'>").appendTo("#editMemberForm");
			alert('submit'); */
			$("#editMemberForm").submit();
		});
		
		// 목록출력
		$("#showList").click(function(){
			window.location.replace("/member/list");
		});
		
		// ID input 태그 색상 변경
		$("input[name='m_id']").css({"background-color":"grey", "color":"white"});
		
	}); // function() end
	
	// 사진 미리보기
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				console.log(e.target.result);
				$("#preview").attr("src", e.target.result);
			};
			reader.readAsDataURL(input.files[0]);
		} else {
			$("#preview").attr("src", "");
		}
	}
	</script>
</body>
</html>