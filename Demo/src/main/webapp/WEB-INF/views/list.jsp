<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<title>member list</title>
</head>
<body>
	<h1>회원 목록</h1>
 	<div class="container">
 		<div class="row">
 			<div class="col"><button class="btn btn-primary" id="regForm">회원가입 화면으로</button></div>
 			<div class="col" style="text-align: right"><button class="btn btn-danger" id="selDelMember">선택 회원 삭제</button></div>
 		</div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">선택</th>
					<th scope="col">#</th>
					<th scope="col">ID</th>
					<th scope="col">닉네임</th>
					<th scope="col">이름</th>
					<th scope="col">주소</th>
					<th scope="col">전화번호</th>
					<th scope="col">가입일자</th>
					<th scope="col">수정</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
		
			<tbody>
				<c:forEach var="member" items="${list }" varStatus="status">
				<tr>
					<td><input type="checkbox" class="memCheckBox form-check-input" name="memCheckBox" value="${member.m_id }"></td>
					<th scope="row">${status.count}</th>
					<td><c:out value="${member.m_id }"/></td>
					<td><c:out value="${member.m_nickname }"/></td>
					<td><c:out value="${member.m_name }"/></td>
					<td><c:out value="${member.m_address }"/></td>
					<td><c:out value="${member.m_phone }"/></td>
					<td><c:out value="${member.m_regdate }"/></td>
					<td><button type="button" class="btn btn-secondary BtnEditMember" id="BtnEditMember" value="${member.m_id }">수정</button></td>
					<td><button type="button" class="btn btn-danger BtnDelMember" id="BtnDelMember" value="${member.m_id }">삭제</button></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		$(function(){
			// 회원가입 화면으로 이동
			$("#regForm").click(function(){
				window.location.replace("/");
			});
			
			// 삭제 버튼 클릭
			$(".BtnDelMember").click(function(){
				var param = "m_id=" + $(this).val();
				console.log('param : ' + param);
 				$.ajax({
					url: "${pageContext.request.contextPath}/member/delMember.do",
//					contentType: "application/json",
					method: "post",
//					data: JSON.stringify({
//						m_id: $(this).val()
//					}),
					data: param,
					success:function(data){
						alert("삭제 성공");
						window.location.replace("/member/list");
					},
					error:function(data){
						alert("삭제 실패");
					}
				})
			});
			
			
			// 선택 회원 삭제
			$("#selDelMember").on("click", function(){
				var selectedMember = [];
				$(".memCheckBox:checked").each(function(i) {
					selectedMember.push($(this).val());
				});
				console.log(selectedMember);
				console.log(JSON.stringify(selectedMember));
				
  				$.ajax({
					url: "${pageContext.request.contextPath}/member/selDelMember.do",
					method: "post",
					contentType: "application/json",
					data: JSON.stringify(selectedMember),
					success: function(data){
						alert("선택 삭제 성공");
						window.location.replace("/member/list");
					},
					error: function(data) {
						alert("선택 삭제 실패");
					}
				});
			});
			
			// 수정 버튼 클릭
 			$(".BtnEditMember").on("click", function(){
 				window.location.replace("/member/editMember?m_id="+$(this).val());
 			});
			
			
		});	// funcion() end
		
	</script>
</body>
</html>