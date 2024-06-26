			// 초기 세팅
			//// 카테고리
			switch("${dto.category}") {
				case 'free' :
					$("#category").text("자유게시판");
				break;
				case 'notice' :
					$("#category").text("공지사항");
				break;
			}
			//// 답글인 경우
			if("${replyBoard}" == "Y") {
				$("form[name='cmtForm'] input[name='boardseq']").val(-1);
				$("form[name='cmtForm'] input[name='replyboardseq']").val("${dto.seq}");
			}
				
			// 글 수정하기
			$("#editBtn").click(function(){
				window.location.replace("/board/editArticle?seq=${dto.seq}&pid=${dto.pid}&title=${dto.title}&category=${dto.category}");
			});
			
			// 글 삭제하기
			$("#delBtn").click(function(){
				var cmtYn = 'N';
				if($("div.cmtContent").length > 0) cmtYn = 'Y';
				if(confirm("정말로 게시글을 삭제하시겠습니까?\n삭제 후에는 복구할 수 없습니다.")) {
					window.location.replace("/board/delArticle?seq=${dto.seq}&attachfile=${dto.attachfile}&pid=${dto.pid}&cmtYn="+cmtYn);
				}
			});
			
			// 글 목록으로
			$("#listBtn").click(function(){
				window.location.replace("/board/toList");
			});
			
			// 답글 달기
			$("#replyBtn").click(function(){
				var cate = $("#category").text();
				window.location.replace("/board/toWrite?pid=${dto.seq}&title=${dto.title}&category=${dto.category}");
			});
			
			// 댓글 작성
			$("#commentBtn").on("click", function() {
				// cmtForm 초기화(pw, content)
				cmtFormReset();
				
				var cmtPw = $("#cmtPw").val().trim();
				var cmtContent = $("#cmtContent").val().trim();
								
				if(validCheck(cmtPw, cmtContent)){
					$("form[name='cmtForm'] input[name='pw']").val(cmtPw);
					$("form[name='cmtForm'] input[name='content']").val(cmtContent);
					
					$.ajax({
						type: "post",
						url: "/comment/write.do",
						data: $("#cmtForm").serialize(),
						success: function(data){
							$("#cmtPw, #cmtContent").val("");
							commentReload();
						},
						error: function(data){
							alert("ajax 실패");
							console.log(data);
						}
					});		
				}
			});

		// 댓글 작성 후 리로드
		function commentReload(pid) {
		    var appendStr = "";
		    var replyYn = ("${dto.pid}" > 0) ? "y" : "n";
		    $.ajax({
		        type: "post",
		        url: "/comment/list",
		        data: {
		            "boardseq" : "${dto.seq}", "replyYn" : replyYn	
		        },
		        success: function(data) {
		            for(var i=0; i < data.length; i++) {
		                console.log(data[i].depth == 0);
		                appendStr = appendStr 	+	"<div class='cmtList'>"
		                                        +	"<div class='row cmtIdDiv'>"
		                                        +		"<div class='col'>"
		                                        +			"<span>" + data[i].nickname + "</span>"
		                                        +		"</div>"
		                                        +	"</div>"
		                                        + "<div class='row cmtContDiv'>"
		                                        +	"<div class='col-8 cmtContent'>"
		                                        +		"<span>" + data[i].content + "</span>"
		                                        +		"<input type='hidden' seq='" + data[i].seq + "'>"
		                                        +		"<input type='hidden' boardseq='" + data[i].boardseq + "'>"
		                                        +	"</div>"
		                                        +	"<div class='col-2 cmtTime'>"
		                                        +		"<span>" + cvtDateFormat(data[i].regdate) + "</span>"
		                                        +	"</div>"
		                                        if(data[i].depth == 0) {
		                                            appendStr = appendStr +	"<div class='col-2 cmtBtnDiv'>"
		                                                    +		"<button type='button' class='btn btn-info' onclick='replyCmtForm("
		                                                    +			data[i].seq + ", " + data[i].depth + ", this)'"
		                                                    +			" id='replyCmtBtn_" + data[i].seq + "'>답글</button><br/>";													
		                                        }
		                
		                                        if("${loginId}" == data[i].id) {
		                                            appendStr =	appendStr +	"<button type='button' class='btn btn-danger delCmtBtn' "
		                                                    + 		"onclick='delPwPop(" + data[i].seq +",0)'>삭제</button>"
		                                                    +		"<button type='button' class='btn btn-success editCmtBtn' "
		                                                    +		"onclick='editCmt(" + data[i].seq + ", 0, this)'>수정</button>";
		                                        }
		                appendStr = appendStr + "</div> </div> </div>";
		            }
		            
		            $("#cmtListDiv").children().remove();
		            $("#cmtListDiv").append(appendStr);
		            $(".cmtList").css({"border":"1px black solid", "margin":"5px", "padding":"10px"});
		            $(".cmtIdDiv").css({"margin-bottom":"10px"});
		            $("input[name='pw'], #cmtContent").val("");
		            $("button.delCmtBtn").css({"margin-top":"5px", "margin-right":"5px", "margin-bottom":"5px"});
		            
		            // 만약 대댓글을 작성한 경우라면 대댓글 창 펼치기
		            if(pid > 0) {
		                $("#replyCmtBtn_" + pid).trigger("click");
		            }
		        },
		        error: function(data) {
		            alert("리로드 실패");
		            console.log(data);
		        }
		    });
		}

		// 날짜 변환
		function cvtDateFormat(date) {
		    date = new Date(date);
		    return date.getFullYear() + '년 ' + (date.getUTCMonth()%12+1) + '월 ' + date.getUTCDate() + '일 <br>'
		            + date.getHours() + '시 ' + date.getMinutes() + '분 ' + date.getSeconds() + '초';
		}

		// 댓글 삭제
		function delCmt(cmtSeq, pid) {
			var boardseq = $("#cmtForm input[name='boardseq']").val();
			var replyYn = "n";
			if(boardseq == 0) {
				boardseq = $("#cmtForm input[name='replyboardseq']").val();
				replyYn = "y";
			}
		    $.ajax({
		        type: "get",
		        url: "/comment/delete.do",
		        data: {"seq" : cmtSeq, "pid" : pid, "boardseq" : boardseq, "replyyn" : replyYn},
		        success: function(data){
		            commentReload();
		        },
		        error: function(data){
		            console.log(data);
		            alert("댓글 삭제에 실패하였습니다.");
		        }
		    });
		}


		// 댓글 비밀번호 체크
		function delPwCheck (pw) {
		    var cmtSeq		= $("form[name='cmtForm'] input[name='seq']").val();
		    var pid			= $("form[name='cmtForm'] input[name='pid']").val();
		    
		    if(pw.length < 4) {
		        alert("비밀번호를 4자리 이상 입력해주세요.");
		        return;
		    }			
		    
		    $.ajax({
		        type: "post",
		        url: "/comment/passwordCheck",
		        data:
		        {
		            "seq" : cmtSeq
		            , "pid" : pid
		            , "pw" : pw
		        },
		        success: function(data){
		            if(data > 0 && confirm("정말 댓글을 삭제하시겠습니까?")) {
		                delCmt(cmtSeq, pid);
		            } else {
		                alert("비밀번호가 일치하지 않습니다.");
		            }
		        },
		        error: function(data) {
		            alert("댓글 비밀번호 확인 오류");
		        }
		    });
		    
		}


		// 댓글 비밀번호 팝업
		function delPwPop(cmtSeq, pid) {
		    window.open("/comment/toPwCheck", "댓글 비밀번호 입력", "width=700px,height=200px,left=610,top=340,scrollbars=yes");
		    $("form[name='cmtForm'] input[name='seq']").val(cmtSeq);
		    $("form[name='cmtForm'] input[name='pid']").val(pid);
		}

		// 댓글 수정 버튼
		function editCmt(cmtSeq, pid, el) {
		/* 			if($("#onEditing").val() == 1) {
		        alert("현재 수정 중인 댓글이 있습니다.");
		        return;
		    }
		    $("#onEditing").val(1); */
		    
		    // copy element
		    var ce = $(el).parents("div.cmtList").clone();
		    ce.find("div.cmtContent").append("<textarea type='text' id='editCmtContent_" + ce.find("div.cmtContent input[seq]").attr("seq")
		                                        + "' style='width:100%; resize:none;'>"
		                                        + ce.find("div.cmtContent").children()[0].innerText + "</textarea>");
		    ce.find("div.cmtContent").children().first().remove();
		    ce.find("div.cmtBtnDiv").children().remove();
		    ce.find("div.cmtBtnDiv").append("<button type='button' class='btn btn-primary editProcBtn' onclick='editCmtProc("
		                                    + ce.find("div.cmtContent input[seq]").attr("seq")
		                                    + ", " + ce.find("div.cmtContent input[pid]").attr("pid") +")'>수정</button>");
		    ce.find("div.cmtBtnDiv").append("<button type='button' class='btn btn-secondary' onclick='cancelEditCmt(this)'>취소</button>");
		    $(el).parents("div.cmtList").after(ce).hide();
		    $("button.editProcBtn").css({"margin-top":"5px", "margin-right":"5px", "margin-bottom":"5px"});
		}

		// 댓글 수정 요청
		function editCmtProc(cmtSeq, pid) {
		    // cmtForm 초기화
		    cmtFormReset();
		    
		    // 수정한 댓글 내용 form에 담기
		    //$("#editCmtForm input[name='content']").val($("#editCmtContent").val());
		    $("form[name='cmtForm'] input[name='seq']").val(cmtSeq);
		    $("form[name='cmtForm'] input[name='pid']").val(pid);
		    $("form[name='cmtForm'] input[name='content']").val($("#editCmtContent_" + cmtSeq).val());
		    
		    
		    $.ajax({
		        type: "get",
		        url: "/comment/editProc.do",
		        data: $("#cmtForm").serialize(),
		        success: function(data) {
		            commentReload();
		            //$("#onEditing").val(0);	// 수정 완료 후 수정중 플래그 0으로 만들기
		        },
		        error: function(data) {
		            alert("댓글 수정 요청 실패");
		        }
		    });
		}

		// 댓글 수정 취소
		function cancelEditCmt(el) {
		    $("#onEditing").val(0);
		    $(el).parents("div.cmtList").prev().show();
		    $(el).parents("div.cmtList").remove();
		}

		// 댓글 - 답글
		function replyCmtForm(cmtSeq, depth, el) {
		    if($("#replyDiv" + cmtSeq).length > 0 || $("div.replyList_" + cmtSeq).length > 0) {
		    	$("div.replyList_" + cmtSeq).remove();
		    	return;
		    }
		    
		    if($("#loginId").val() != "") {
			    // 답글 폼 만들기
			    //var formStr = "<form name='replyCmtForm' action='/comment/replyCmtProc.do' method='post'>"
			    var formStr = "<div class='replyDiv' id='replyDiv" + cmtSeq + "'>"
			                +		"<div class='row'>"
			                +			"<div class='col'>	<span>대댓글 비밀번호 : <input type='password' id='replyCmtPw_" + cmtSeq + "' maxlength='20'> </span>	</div>"
			                +		"</div>"
			                +		"<div class='row replyCmtContDiv'>"
			                +			"<div class='col-10'>"
			                +				"<textarea name='content' id='replyCmtContent_" + cmtSeq + "'></textarea>"
			                +			"</div>"
			                +			"<div class='col-2 replyCmtBtnDiv'>"
			                +				"<button type='button' class='btn btn-primary' onclick='replyCmtProc("
			                +					cmtSeq + ", " + depth + ")'>대댓글</button>"
			                +				"<button type='button' class='btn btn-secondary' onclick='cancelReplyCmt(this, " + cmtSeq + ")'>취소</button>"
			                +			"</div>"
			                +			"<div>"
			                +				"<input type='hidden' ame='pid' value='" + cmtSeq +"'>"
			                +			"</div>"
			                +		"</div>"
			                +	"</div>";
			                //+ "</form>";
			                
			    $(el).parents("div.cmtList").after(formStr);		    	
		    }
		    
		    // 답글 목록 출력
		    $(el).parents("div.cmtList").after(createReplyForm(showReply(cmtSeq)));
		    
		    // css
		    $("div.replyDiv").css({"border":"1px solid black", "padding":"10px"});
		    $("div.replyCmtBtnDiv button:first-child").css({"margin-right":"5px"});
		    $("div.replyCmtContDiv").css("margin-top", "10px");
		    $("#replyCmtContent_"+cmtSeq).css({"width":"100%", "resize":"none"});
		    $("button.delCmtBtn").css({"margin-top":"5px", "margin-right":"5px", "margin-bottom":"5px"});
		    $("div[class*='replyList']").css({"margin":"10px", "background-color":"#dcdcdc"});
		    $("div.cmtList").css({"border":"1px black solid", "margin":"5px", "padding":"10px"});
		    
		}

		// 댓글 - 답글 요청
		function replyCmtProc(pid, depth) {
		    // cmtForm 초기화
		    cmtFormReset();
		    
		    var replyCmtPw = $("#replyCmtPw_" + pid).val().trim();
		    var replyCmtContent = $("#replyCmtContent_" + pid).val().trim();
		    
		    // 유효성 검사
		    if(validCheck(replyCmtPw, replyCmtContent)) {
		        // 답글 비밀번호, 내용, pid 수정
		        $("form[name='cmtForm'] input[name='pw']").val(replyCmtPw);
		        $("form[name='cmtForm'] input[name='content']").val(replyCmtContent);
		        $("form[name='cmtForm'] input[name='pid']").val(pid);
		        $("form[name='cmtForm'] input[name='depth']").val(depth+1);
		        
		        
		        $.ajax({
		            type: "post",
		            url: "/comment/write.do",
		            data: $("#cmtForm").serialize(),
		            success: function(data) {
		                commentReload(pid);
		            },
		            error: function(data) {
		                alert("대댓글 요청 실패");
		            }
		        });
		    };
		}

		// // cmtForm 초기화(pw, content)
		function cmtFormReset() {
		    $("form[name='cmtForm'] input[name='pw'], form[name='cmtForm'] input[name='content']").val("");
		    $("form[name='cmtForm'] input[name='seq'], form[name='cmtForm'] input[name='rootseq'], form[name='cmtForm'] input[name='pid']").val(0);
		}

		// 댓글 - 답글 창 닫기
		function cancelReplyCmt(el, pid) {
		    $(el).parents("div.replyDiv").remove();
		    $("div.replyList_" + pid).remove();
		}

		// 댓글 유효성 검사
		function validCheck(cmtPw, cmtContent) {
		    // 유효성 검사
		    //// 댓글 비밀번호(4자리 이상)
		    if(cmtPw.length < 4) {
		        alert("댓글 비밀번호를 4자리 이상 입력해주세요.");
		        return false;
		    } else if(cmtContent.length < 1) {
		        //// 댓글 입력
		        alert("댓글 내용을 입력해주세요.");
		        return false;
		    } else {
		        return true;
		    }
		}		

		// 대댓글 목록 출력
		function showReply(cmtSeq) {
		    var rs;
		    // 대댓글 목록을 만들어 리턴
		    $.ajax({
		        url: "/comment/listReply",
		        type: "get",
		        async: false,
		        data: {"pid" : cmtSeq},
		        success: function(data){
		            rs = data;
		        },
		        error: function(data){
		            alert("대댓글 목록 출력 실패");
		            console.log(data);
		        }
		    });
		    return rs;
		}

		// 대댓글 폼 만들기
		function createReplyForm(data) {
		    var appendStr = "";
		    for(var i=0; i < data.length; i++) { 
		        appendStr = appendStr + "<div class='cmtList replyList_" + data[i].pid + "' style='border:1px solid black;'>"
		                                + "<div class='row cmtIdDiv'>"
		                                +	"<div class='col'>"
		                                +		"<span>" + data[i].nickname + "</span>"
		                                +	"</div>"
		                                + "</div>"
		                                + "<div class='row cmtContDiv'>"
		                                +	"<div class='col-8 cmtContent'>"
		                                +		"<span>" + data[i].content + "</span>"
		                                +		"<input type='hidden' seq='" + data[i].seq + "'>"
		                                +		"<input type='hidden' pid='" + data[i].pid + "'>"
		                                +	"</div>"
		                                +	"<div class='col-2 cmtTime'>"
		                                +		"<span>" + cvtDateFormat(data[i].regdate) + "</span>"
		                                +	"</div>"
		                                +	"<div class='col-2 cmtBtnDiv'>"
		        
		                                if("${loginId}" == data[i].id) {
		                                    appendStr =	appendStr +	"<button type='button' class='btn btn-danger delCmtBtn' "
		                                            + 		"onclick='delPwPop(" + data[i].seq +", " + data[i].pid +")'>삭제</button>"
		                                            +		"<button type='button' class='btn btn-success editCmtBtn' "
		                                            +		"onclick='editCmt(" + data[i].seq + ", " + data[i].pid + ", this)'>수정</button>";
		                                }
		        appendStr = appendStr + "</div> </div> </div>";
		    }
		    
		    return appendStr;
		}	