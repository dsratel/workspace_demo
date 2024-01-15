		// 글 작성 버튼
		$("#writeBtn").click(function(){
			window.location.replace("/board/toWrite");
		});
		
		// 검색 버튼
		$("#searchBtn").click(function(){
			$("#searchForm").submit();
		});
		
		// 로그아웃 버튼
		$("#logoutBtn").click(function(){
			window.location.replace("/common/logout?id=${loginId}");
		});
		
		// 선택 게시물 삭제
		$("#selDelBtn").click(function(){
			var frm = $("#searchForm");
			frm.attr("action", "/board/selDelArticle");
			frm.attr("method", "post");
			frm.submit();
		});
		
		// 관리자 메뉴
		$("#masterMenu").click(function(){
			window.location.replace("/master/home");
		});
		
		// 초기 값 세팅
		if("${srchInfo}" != "") {
 			$("#category").val($("#category").attr("init"));
			$("#searchType").val($("#searchType").attr("init"));
			$("#pageSize").val($("#pageSize").attr("init"));
			$("input[name='searchKeyword']").val($("input[name='searchKeyword']").attr("init"));
		}
		
		// 요소 클릭 시 클릭한 요소 위치에 레이어팝업 띄우기
		$(".authors").on("click", function(e){
			target = $(e.target);
			var p = $(target).offset();
			$("#toMemberArticle").attr("author", target.attr("nickname"));
			
			var userTdSize = target[0].offsetWidth;
			var userInitBtnSize = 162; //$("#toMemberArticle")[0].offsetWidth;
			
			var divTop	= p.top + 30;	// 상단 좌표
			var divLeft	= p.left + (userTdSize - userInitBtnSize)/2;		// 좌측 좌표
			
			// 레이어 팝업 view
			$(".layer_popup").css({"z-index" : "10000", "top" : divTop, "left" : divLeft, "position" : "absolute"}).show();
		});
		
		// 다른 곳 클릭 시 레이어 팝업 사라짐
 		$(document).click(function(){
			var authors = $(".authors");
			var toMemberArticle = $("#toMemberArticle");
			if((!authors.is(event.target) && !authors.has(event.target).length)) {
				$(".layer_popup").hide();
			}
		}); 
		
		// 페이지 이동
		$("#toMemberArticle").click(function(e){
			console.log("사용자 게시글로 이동");
			$("#searchType").val(1);
			$("input[name='searchKeyword']").val($(this).attr("author"));
		});			
	
		// 카테고리 변경 시 페이지 리로드
		function changeCategory(cate) {
			window.location.replace("/board/toList?category="+$(cate).val());
		}
		
		// 페이지 이동
		function toPage(page) {
			$("#curPage").val(page);
			$("#searchForm").submit();
		}
		
		// 페이지 사이즈 변경
		function changePageSize(pageSize) {
			$("#searchForm").submit();
		}
		
		// 게시글 삭제
		function delArticle(seq, pid, attachfile, cmtYn) {
			window.location.replace("/board/delArticle?seq=" + seq + "&pid=" + pid + "&attachfile=" + attachfile + "&cmtYn=" + cmtYn);
		}
	
		// page
		$(".page-item").find("a[value=" + $('#curPage_init').val() + "]").css("color", "pink");