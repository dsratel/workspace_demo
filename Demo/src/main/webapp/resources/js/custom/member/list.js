				// 삭제 버튼 클릭
				$(".BtnDelMember").click(function(){
					var param = "id=" + $(this).val();
					console.log('param : ' + param);
	 				$.ajax({
						url: "${pageContext.request.contextPath}/member/delMember.do",
	//					contentType: "application/json",
						method: "post",
	//					data: JSON.stringify({
	//						id: $(this).val()
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
				
				// 보기 버튼 클릭
	 			$(".BtnEditMember").on("click", function(){
	 				window.location.replace("/member/toViewMember?id="+$(this).val());
	 			});
				
				// 검색 버튼 클릭
				$("#searchBtn").on("click", function(){
					search();
				});
				
				// 검색창에서 엔터
				$("searchKeyword").keypress(function(){
					search();
				});
				
				// 검색 함수
				function search() {				
					var searchType = $("#searchType").val();
					var searchKeyword = $("#searchKeyword").val();
					
					console.log("searchType : " + searchType + " / searchKeyword : " + searchKeyword);
					
					$("#searchForm").submit();
				}
				
				// 전 페이지의 내용 전달
				$("#searchType").val($("#searchType_init").val()).prop("selected", true);
				$("#pageSize").val($("#pageSize_init").val()).prop("selected", true);
				
				// 페이징
				//// 페이징 파라미터
				var searchType		= "";
				var searchKeyword	= "";
				var curPage			= "";
				var pageSize		= "";
				var url				= "";
				
				function setPaginationParam(opt, pageNo) {
					switch(opt) {
					case "prev" :
						curPage = ( $("#curRange_init").val()-1 ) * $("#rangeSize_init").val();
					break;
					case "next" :
						curPage = ( $("#curRange_init").val() * $("#rangeSize_init").val() +1 );
					break;
					default :
						curPage = pageNo;
					break;
					}
					searchType		= $("#searchType").val();
					searchKeyword	= $("#searchKeyword").val();
					pageSize		= $("#pageSize").val();
					url				= "/member/list?" + "searchType=" + searchType + "&searchKeyword=" + searchKeyword
											+ "&curPage=" + curPage + "&pageSize=" + pageSize;
				}
				
				//// 이전 페이지 클릭
				$("#prevPage").click(function(){
					setPaginationParam('prev');
					window.location.href=url;
					// window.location.replace(url);
					// window.open(url);
				});
				
				//// 다음 페이지 클릭
				$("#nextPage").click(function(){
					setPaginationParam('next');
					window.location.href=url;
				});
				
				//// 특정 페이지 클릭
				$(".toPage").click(function(){
					setPaginationParam('cur', $(this).attr("value"));
					window.location.href=url;
				});
				
				//// 한 페이지 row 수 변경
				$("#pageSize").on("change", function(){
					setPaginationParam('cur', 1);		
					window.location.href=url;
				});
				
				// 로그아웃 버튼
				$("#logoutBtn").click(function(){
					window.location.replace("/common/logout?id=${loginId}");
				});
				
				// page
				$(".page-item").find("a[value=" + $('#curPage_init').val() + "]").css("color", "pink");