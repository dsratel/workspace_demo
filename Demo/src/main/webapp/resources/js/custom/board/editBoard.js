			// 첨부파일 개수만큼 <input>보이기
 			for(var i = 0; i < "${fileCnt}"; i++) {
				$("#attachfile" + (i+1)).hide();
			}
			
			// 사진 더블클릭 시 삭제
			$("img").dblclick(function(el){
				$($("#"+$(this).attr("conFile"))).show();
				$(this).next().remove();	// prevImg input 태그 삭제
				$(this).remove();			// 이전 이미지 삭제
			});
			
			// 글 수정 버튼
			$("#editBtn").click(function(){
				// 파일 개수 체크
				var fileCnt = 0;
				var files = $("input[type='file']");
				for(var i = 0; i < files.length; i++) {
					if(files.get(i).files.length > 0) {
						fileCnt++;
						files.get(i).name= 'upfile';
					} else {
						files.get(i).name = '';
					}
				}
				
				// 파일이 있는 경우 attachfile에 y값 입력
				var prevImg = $("input[name='prevImg']");
				if(prevImg.length == 0 && fileCnt == 0) {
					$("#attachfile").val('n');					
				} else {
					$("#attachfile").val('y');
				}
				
				// 파일 개수 입력
				$("#fileCnt").val(fileCnt);
				
				$("form[name='editBoardForm']").submit();
			});
			
			// 글 목록 버튼
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});