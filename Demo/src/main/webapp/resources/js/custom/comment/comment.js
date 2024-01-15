	// 팝업 창에서 비밀번호 입력
	function pwInit() {
		//$(opener.document).find("form[name='cmtForm'] input[name='seq']").val($("#pw").val());
		opener.parent.delPwCheck($("#pw").val());
		window.close();
	}
	
	// 초기 값 세팅
	if("${#curPage}" != "") {
		//$("#category").val("${srchInfo.category}");
		$("#searchType").val($("#searchType_init").val());
		$("#pageSize").val($("#pageSize_init").val());
		//$("input[name='searchKeyword']").val("${srchInfo.searchKeyword}");
	}
	// 검색 버튼
	$("#searchBtn").click(function(){
		$("#searchForm").submit();
	});
	
	// 페이지 사이즈 변경
	function changePageSize(pageSize) {
		$("#searchForm").submit();
	}
	
	// 선택한 댓글 삭제
	$("#selDelBtn").click(function(){
		var frm = $("#searchForm");
		frm.attr("action", "/comment/selDelComment");
		frm.attr("method", "post");
		frm.submit();
	});
	
	// 페이지 이동
	function toPage(page) {
		$("#curPage").val(page);
		$("#searchForm").submit();
	}
	
	// page
	$(".page-item").find("a[value=" + $('#curPage').val() + "]").css("color", "pink");	