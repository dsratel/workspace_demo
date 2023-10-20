<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<title>member view page</title>
</head>
<body>
	<div class="container">
		<div class="row" style="margin: 10px;">
			<div class="col-3" style="border:1px solid" id="rs">
				
			</div>
			<div class="col-6" style="border:1px solid">
				<form action="/member/editMember.do" method="post" name="editMemberForm" id="editMemberForm" enctype="multipart/form-data">
					<div class="row" style="padding: 5px 0px 5px">
						<div class="col-6">
							<span>프로필 사진</span>
						</div>
						<div class="col-6">
							<img class="img-thumbnail" src="${filePath }" id="preview"/>
							<input type="file" class="form-control" id="inputGroupFile02" name="profilePhoto" onchange="readURL(this)";>
							<button type="button" class="btn btn-danger" id="delPfPhoto">프로필 사진 삭제</button>
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
		
		// 프로필 사진 삭제
		$("#delPfPhoto").click(function(){
			alert('프로필 사진 삭제');
		});
	}); // function() end
	
	// 사진 미리보기
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();	// FileReader 객체 생성
			reader.onload = function(e) {	// load 이벤트의 핸들러. 읽기 동작이 성공적으로 완료되었을 때마다 발생.
				$("#preview").attr("src", e.target.result);
			};
			reader.readAsDataURL(input.files[0]);	// <input type="file">의 파일을 url로 읽어들인다. 성공적으로 읽혔다면 위의 .onload를 통해 <img>의 src 속성에 url 주입.
		} else {
			$("#preview").attr("src", "");
		}
	}
	</script>
</body>
</html>