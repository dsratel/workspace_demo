// 목록출력
$("#showList").click(function(){
    window.location.replace("/member/list?status="+$("#status").val());
});

// 프로필 사진 삭제
$("#delPfPhoto").click(function(){
    $.ajax({
        type: "get",
        url: "/member/delPfPhoto",
        data: {id : $("input[name='id']").val()},
        success: function(rs){
            alert("프로필 사진 삭제 성공");
            console.log(rs);
            window.location.replace("/member/editMember?id=" + $("input[name='id']").val());
        },
        error: function(rs){
            alert("프로필 사진 삭제 실패");
            console.log(rs);
        }
    });
});
    
// 휴대폰 번호 세팅
var phone = $("#phone_init").val();
var p1 = phone.substring(0, phone.length-8);
var p2 = phone.substring(phone.length-8, phone.length-4);
var p3 = phone.substring(phone.length-4);

$("#phone1").val(p1);
$("#phone2").val(p2);
$("#phone3").val(p3);		

// 수정하기
$("#save").on("click", function(){
    // 유효성 검사
    //// 1. 이름
    var testName = $("input[name='name']").val();
    if(testName.length < 2) {
        alert("이름은 2자리 이상이어야 합니다.");
        return;
    } else {
        $("#name").val($("input[name='name']").val());
    }
    
    //// 2. 닉네임
    var testNickname = $("input[name='nickname']").val();
    if(testNickname.length < 2) {
        alert("닉네임은 2자리 이상이어야 합니다.");
        return;
    } else {
        $("#nickname").val($("input[name='nickname']").val());
    }
    
    //// 3. 주소
    var testAddress = $("input[name='address']").val();
    if(testAddress.length < 1) {
        alert("주소를 입력해주세요.");
        return;
    } else {
        $("#address").val($("input[name='address']").val());
    }
    
    //// 4. 이메일
    if($("#emailCheckBtn").attr("verified") != true) {
        alert("이메일 중복검사를 실행해주세요.");
        return;
    };
    
    //// 5. 전화번호
    var regExp = /^[0-9]{4}$/;
    var testPhone = "";
    if(!regExp.test($("#phone2").val()) || $("#phone2").val().length < 4 || !regExp.test($("#phone3").val()) || $("#phone3").val().length < 4) {
        // 2번째 3번째 번호가 4자리 보다 작을 때 리턴
        alert("전화번호를 확인하세요. 각 칸마다 4자리 확인.");
        return ;
    } else {
        // 1번째 번호가 기타인 경우
        regExp = /^[0-9]{3,4}$/;
        if($("#phone1").val() == "etc") {
            if(!regExp.test($("#phone4").val()) || $("#phone4").val().length < 3) {
                alert("첫 번째 자리 번호를 확인하세요.");
                return;
            }
            $("#phone").val($("#phone4").val() + $("#phone2").val() + $("#phone3").val());
        } else {
            $("#phone").val($("#phone1").val() + $("#phone2").val() + $("#phone3").val());
        }
    }
    
    
    $("#editMemberForm").submit();
});

// 사진 더블클릭 시 
if($("#preview").attr("src").indexOf("/userIcon.png") > 0) {
    $("#preview").dblclick(function(){
        console.log("더블클릭");
        // 기본이미지로 변경
        $("#preview").attr("src", "/repository/userIcon.png");
        
        // 파일 input 초기화
        $("#profileInput").val("");
    });			
}

// 수정하기 버튼
$("#toEdit").click(function(){
    $(".view").hide();
    $(".edit, #emailCheckBtn").show();	
});

// 수정 취소 버튼
$("#editCancel").click(function(){
    $(".edit, #emailCheckBtn").hide();
    $(".view").show();
});

// email 중복검사
$("#emailCheckBtn").click(function(){
    var testEmail = $("input[name='email']").val().trim();
    if(new RegExp(/^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,3}$/).test(testEmail)) {
        $.ajax({
            url: "/member/checkEmail",
            dataType: "text",
            type: "get",
            data: {email: testEmail},
            success: function(data) {
                if(data > 0) {
                    // 중복되는 ID가 있는 경우
                    alert("중복되는 EMAIL이 있습니다.");
                    $("#input[name='email']").val("");
                    $("#emailCheckBtn").removeAttr("verified");
                } else {
                    // 중복되는 ID가 없는 경우 <form> 하위 <input>에 값 주입
                    alert("EMAIL을 사용할 수 있습니다.");
                    $("#emailCheckBtn").hide();
                    $("#emailCheckBtn").attr("verified", true);
                    $("#email").val(testEmail);				
                }
            },
            error: function(data) {
                console.log("실패");
                console.log(data);
            }
        });
    } else {
        // 규칙에 맞지 않는 EMAIL 중복 검사 한 경우 input 값 지우기
        alert("EMAIL을 aaa@bbb.ccc 같은 형식으로 입력해주세요.");
        $("input[name='email']").val("");
        $("#email").val("");
    }
});

// EMAIL 입력창의 내용 변경 시 중복검사 재 실시
$("input[name='email']").keyup(function(rs){
    if($("#emailCheckBtn").css("display") == 'none') {
        $("input[name='email']").val("");
        $("#emailCheckBtn").show();
    }
});

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

    // 폰 앞자리가 기타인 경우
    function changePhone(input) {
    if(input.value == "etc") {
        $("#phone4").show();
        $("#phone1").css("display", "block");
    } else {
        $("#phone4").val('');
        $("#phone4").hide();
        $("#phone1").css("display", "inline");
    }
}

// ID input 태그 색상 변경
if($("#sa").val() != '') {
	$("input[name='email']").css({"background-color":"grey", "color":"white"}).prop("disabled", true);
}