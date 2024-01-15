			// 글 작성 버튼
			$("#writeBtn").click(function(){
				// 유효성 검사
				//// 제목
				if($("#title").val().length < 1) {
					alert("제목을 입력해주세요.");
					return;
				}
				
				//// 내용
				if($("#content").val().length < 1) {
					alert("내용을 입력해주세요.");
					return;
				}
				
				// 파일 개수 체크
				var fileCnt = 0;
				var files = $("input[type='file']");
				for(var i = 0; i < files.length; i++) {
					if(files.get(i).files.length > 0) {
						fileCnt++;
					} else {
						files.get(i).name = '';
					}
				}
				
				// 파일이 있는 경우 attachfile에 y값 입력
				if(fileCnt > 0) {
					$("#attachfile").val('y');
					
				} else {
					$("#attachfile").val('n');
				}
				
				// 파일 개수 입력
				$("#fileCnt").val(fileCnt);
				
				if(confirm("글 작성을 하시겠습니까?")) {
					$("form[name='writeBoardForm']").submit();					
				}
			});
			
			// 글 목록 버튼
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});