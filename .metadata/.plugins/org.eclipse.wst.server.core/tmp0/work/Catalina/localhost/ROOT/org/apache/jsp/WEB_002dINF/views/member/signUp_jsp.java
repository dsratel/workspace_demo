/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.93
 * Generated at: 2023-12-12 09:29:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signUp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/workspace_demo/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Demo/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1696575023656L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("	<meta charset=\"UTF-8\">\n");
      out.write("	<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"data:image/x-icon;\">\n");
      out.write("	\n");
      out.write("	<script src=\"/resources/js/jquery/jquery-3.7.1.min.js\"></script>\n");
      out.write("	<script src=\"/resources/js/rsa/jsbn.js\"></script>\n");
      out.write("	<script src=\"/resources/js/rsa/rsa.js\"></script>\n");
      out.write("	<script src=\"/resources/js/rsa/prng4.js\"></script>\n");
      out.write("	<script src=\"/resources/js/rsa/rng.js\"></script>\n");
      out.write("	<title>Home page</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<div class=\"container\">\n");
      out.write("		<div class=\"row\" style=\"margin: 10px;\">\n");
      out.write("			<div class=\"col-3\" style=\"border:1px solid; text-align: center;\" id=\"rs\">\n");
      out.write("				<h3>프로필 미리보기</h3>\n");
      out.write("				<img class=\"img-thumbnail\" src=\"/resources/testFolder/userIcon.png\" id=\"preview\"/>\n");
      out.write("			</div>\n");
      out.write("			<div class=\"col-8\" style=\"border:1px solid\">\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>프로필 사진</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"file\" class=\"form-control\" id=\"m_pf\" onchange=\"readURL(this)\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span></span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>ID</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"text\" class=\"form-control\" name=\"m_id\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<button type=\"button\" class=\"btn btn-secondary\" id=\"idCheckBtn\">중복검사</button>\n");
      out.write("							<span></span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>비밀번호</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"password\" class=\"form-control\" id=\"m_pw\">\n");
      out.write("							<input type=\"hidden\" id=\"rsaPublicKeyModulus\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${publicKeyModulus}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("							<input type=\"hidden\" id=\"rsaPublicKeyExponent\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${publicKeyExponent}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>8자리 이상 - ??(대문자, 숫자, 특수문자 포함)</span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>닉네임</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"text\" class=\"form-control\" name=\"m_nickname\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>두 글자 이상</span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>이름</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"text\" class=\"form-control\" name=\"m_name\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>두 글자 이상</span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>주소</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<input type=\"text\" class=\"form-control\" name=\"m_address\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span></span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span>전화번호</span>\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-6\">\n");
      out.write("							<select class=\"form-select phone\" id=\"m_phone1\" onchange=\"changePhone(this)\">\n");
      out.write("								<option value=\"010\">010</option>\n");
      out.write("								<option value=\"070\">070</option>\n");
      out.write("								<option value=\"030\">030</option>\n");
      out.write("								<option value=\"050\">050</option>\n");
      out.write("								<option value=\"etc\">기타</option>\n");
      out.write("							</select>\n");
      out.write("							<input type=\"text\" class=\"form-control phone\" id=\"m_phone4\" maxlength=\"4\">							\n");
      out.write("							-\n");
      out.write("							<input type=\"text\" class=\"form-control phone\" id=\"m_phone2\" maxlength=\"4\">\n");
      out.write("							-\n");
      out.write("							<input type=\"text\" class=\"form-control phone\" id=\"m_phone3\" maxlength=\"4\">\n");
      out.write("						</div>\n");
      out.write("						<div class=\"col-3\">\n");
      out.write("							<span></span>\n");
      out.write("						</div>\n");
      out.write("					</div>\n");
      out.write("			</div>\n");
      out.write("			<div class=\"col-1\" style=\"border:1px solid; padding: 2px;\">\n");
      out.write("				<button class=\"btn btn-primary\" type=\"button\" form=\"insertMember\" id=\"save\">저장하기</button><br>\n");
      out.write("				<button class=\"btn btn-success\" type=\"button\" id=\"showList\">목록출력</button>\n");
      out.write("				<button class=\"btn btn-danger\" type=\"button\" id=\"testBtn\">테스트 버튼</button>\n");
      out.write("			</div>\n");
      out.write("		</div>\n");
      out.write("		<div id=\"formDiv\">\n");
      out.write("			<form action=\"/member/insertMember.do\" method=\"post\" name=\"insertMember\" id=\"insertMemberForm\" enctype=\"multipart/form-data\">\n");
      out.write("				<div class=\"row\" style=\"padding: 5px 0px 5px\">\n");
      out.write("						<input type=\"file\" name=\"upfile\" class=\"form-control\" id=\"upfile\">\n");
      out.write("						<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\">\n");
      out.write("						<input type=\"password\" class=\"form-control\" name=\"pw\" id=\"pw\">\n");
      out.write("						<input type=\"text\" class=\"form-control\" name=\"nickname\" id=\"nickname\">\n");
      out.write("						<input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\">\n");
      out.write("						<input type=\"text\" class=\"form-control\" name=\"address\" id=\"address\">\n");
      out.write("						<input type=\"text\" class=\"form-control\" name=\"phone\" id=\"phone\">\n");
      out.write("				</div>\n");
      out.write("			</form>\n");
      out.write("		</div>\n");
      out.write("	</div>\n");
      out.write("	<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>\n");
      out.write("	<script>\n");
      out.write("	$(function(){\n");
      out.write("		// css\n");
      out.write("		$(\"#formDiv\").css({\"display\":\"none\"});\n");
      out.write("		/*\n");
      out.write("		// 저장하기\n");
      out.write(" 		$(\"#save\").click(function(){\n");
      out.write("			alert(\"click\");\n");
      out.write("			//$(\"#insertTest\").submit();\n");
      out.write("  			$.ajax({\n");
      out.write("				url: \"./member/insertMember.do\",\n");
      out.write("				contentType:'application/json',\n");
      out.write("				type: \"post\",\n");
      out.write("				data: JSON.stringify({\n");
      out.write("					m_id: $(\"input[name=m_id]\").val(),\n");
      out.write("					m_pw: $(\"input[name=m_pw]\").val(),\n");
      out.write("					m_nickname: $(\"input[name=m_nickname]\").val(),\n");
      out.write("					m_name: $(\"input[name=m_name]\").val(),\n");
      out.write("					m_address: $(\"input[name=m_address]\").val(),\n");
      out.write("					m_phone: $(\"input[name=m_phone]\").val()\n");
      out.write("				}),\n");
      out.write("				success:function(data){\n");
      out.write("					alert(\"success\");\n");
      out.write("					window.location.replace(\"/member/list\");\n");
      out.write("				},\n");
      out.write("				error:function(){\n");
      out.write("					alert(\"error\");\n");
      out.write("				}\n");
      out.write("			});\n");
      out.write("		}); \n");
      out.write("		*/\n");
      out.write("		\n");
      out.write("		const getByteLengthOfString = function (s, b, i, c) {\n");
      out.write("			  for (b = i = 0; (c = s.charCodeAt(i++)); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);\n");
      out.write("			  return b;\n");
      out.write("			};\n");
      out.write("		\n");
      out.write("		// 저장할 때 모든 데이터 유효성 검사 후 input hidden에 넣고 서버로 요청\n");
      out.write("		$(\"#save\").click(function(){\n");
      out.write("			// 유효성 검사\n");
      out.write("			//// 1. 프로필 사진 -> 프로필 사진 선택할 때 <input type=\"file\">에 img 주입.\n");
      out.write("\n");
      out.write("			//// 2. ID -> ID 중복검사 할 때 <input type=\"text\">에 주입.\n");
      out.write("			if($(\"#id\").val().length < 5) {\n");
      out.write("				alert(\"ID를 입력 후 중복검사를 해주세요.\");\n");
      out.write("				return;\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("			//// 3. 비밀번호\n");
      out.write("			var testPw = $(\"#m_pw\").val();\n");
      out.write("			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;\n");
      out.write("			if(testPw.length < 8) {\n");
      out.write("				alert(\"비밀번호는 8자리 이상이어야 합니다.\");\n");
      out.write("				return;\n");
      out.write("			} else {\n");
      out.write("				if(!regex.test(testPw)){\n");
      out.write("					alert(\"대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)\");\n");
      out.write("					return;\n");
      out.write("				} else {\n");
      out.write("					// 비밀번호 RSA 암호화\n");
      out.write("					//// RSAKey 객체 생성\n");
      out.write("					const rsa = new RSAKey();\n");
      out.write("					//// 공개키 설정 값 가져오기\n");
      out.write("					const rsaPublicKeyModulus = $(\"#rsaPublicKeyModulus\").val();\n");
      out.write("					const rsaPublicKeyExponent = $(\"#rsaPublicKeyExponent\").val();\n");
      out.write("					//// RSAKey 객체에 공개키 설정\n");
      out.write("					rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);\n");
      out.write("					//// RSAKey 객체의 encrypt메서드 이용하여 비밀번호 암호화\n");
      out.write("					const securedPassword = rsa.encrypt(testPw);\n");
      out.write("					console.log(\"입력 비밀번호 : \" + testPw);\n");
      out.write("					console.log(\"암호화 비밀번호 : \" + securedPassword);\n");
      out.write("					console.log(\"암호화 길이 : \" + getByteLengthOfString(securedPassword) + \"Bytes\");\n");
      out.write("					\n");
      out.write("					$(\"#pw\").val(securedPassword);\n");
      out.write("				}\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("			//// 4. 닉네임\n");
      out.write("			var testNickname = $(\"input[name='m_nickname']\").val();\n");
      out.write("			if(testNickname.length < 2) {\n");
      out.write("				alert(\"닉네임은 2자리 이상이어야 합니다.\");\n");
      out.write("				return;\n");
      out.write("			} else {\n");
      out.write("				$(\"#nickname\").val($(\"input[name='m_nickname']\").val());\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("			//// 5. 이름\n");
      out.write("			var testName = $(\"input[name='m_name']\").val();\n");
      out.write("			if(testName.length < 2) {\n");
      out.write("				alert(\"이름은 2자리 이상이어야 합니다.\");\n");
      out.write("				return;\n");
      out.write("			} else {\n");
      out.write("				$(\"#name\").val($(\"input[name='m_name']\").val());\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("			//// 6. 주소\n");
      out.write("			var testAddress = $(\"input[name='m_address']\").val();\n");
      out.write("			if(testAddress.length < 1) {\n");
      out.write("				alert(\"주소를 입력해주세요.\");\n");
      out.write("				return;\n");
      out.write("			} else {\n");
      out.write("				$(\"#address\").val($(\"input[name='m_address']\").val());\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("			//// 7. 전화번호\n");
      out.write("			var regExp = /^[0-9]{4}$/;\n");
      out.write("			var testPhone = \"\";\n");
      out.write(" 			if(!regExp.test($(\"#m_phone2\").val()) || $(\"#m_phone2\").val().length < 4 || !regExp.test($(\"#m_phone3\").val()) || $(\"#m_phone3\").val().length < 4) {\n");
      out.write("				// 2번째 3번째 번호가 4자리 보다 작을 때 리턴\n");
      out.write("				alert(\"전화번호를 확인하세요. 각 칸마다 4자리 확인.\");\n");
      out.write("				return ;\n");
      out.write("			} else {\n");
      out.write("				// 1번째 번호가 기타인 경우\n");
      out.write("				regExp = /^[0-9]{3,4}$/;\n");
      out.write("				if($(\"#m_phone1\").val() == \"etc\") {\n");
      out.write("					if(!regExp.test($(\"#m_phone4\").val()) || $(\"#m_phone4\").val().length < 3) {\n");
      out.write("						alert(\"첫 번째 자리 번호를 확인하세요.\");\n");
      out.write("						return;\n");
      out.write("					}\n");
      out.write("					testPhone = $(\"#m_phone4\").val() + $(\"#m_phone2\").val() + $(\"#m_phone3\").val();\n");
      out.write("					console.log(testPhone);\n");
      out.write("				} else {\n");
      out.write("					testPhone = $(\"#m_phone1\").val() + $(\"#m_phone2\").val() + $(\"#m_phone3\").val();\n");
      out.write("				}\n");
      out.write("			}\n");
      out.write(" 			\n");
      out.write(" 			// <form> 값 대입\n");
      out.write(" 			//// 닉네임\n");
      out.write(" 			$(\"#nickname\").val(testNickname);\n");
      out.write(" 			\n");
      out.write(" 			//// 이름\n");
      out.write(" 			$(\"#name\").val(testName);\n");
      out.write(" 			\n");
      out.write(" 			//// 주소\n");
      out.write(" 			$(\"#address\").val(testAddress);\n");
      out.write(" 			\n");
      out.write(" 			//// 전화번호\n");
      out.write(" 			$(\"#phone\").val(testPhone);\n");
      out.write(" 			\n");
      out.write(" 			$(\"#insertMemberForm\").submit();\n");
      out.write("		});\n");
      out.write("		\n");
      out.write("		\n");
      out.write("		\n");
      out.write("		// 목록출력\n");
      out.write("		$(\"#showList\").click(function(data){\n");
      out.write("			window.location.replace(\"/member/list\");\n");
      out.write("		});\n");
      out.write("		\n");
      out.write("		// ID 중복검사\n");
      out.write("		$(\"#idCheckBtn\").click(function(){\n");
      out.write("			var testId = $(\"input[name='m_id']\");\n");
      out.write("			if(testId.val().length > 4) {\n");
      out.write("				$.ajax({\n");
      out.write("					url: \"/member/checkId\",\n");
      out.write("					dataType: \"text\",\n");
      out.write("					type: \"get\",\n");
      out.write("					data: {id :testId.val() },\n");
      out.write("					success: function(data) {\n");
      out.write("						if(data > 0) {\n");
      out.write("							// 중복되는 ID가 있는 경우\n");
      out.write("							alert(\"중복되는 ID가 있습니다.\");\n");
      out.write("							testId.val(\"\");\n");
      out.write("						} else {\n");
      out.write("							// 중복되는 ID가 없는 경우 <form> 하위 <input>에 값 주입\n");
      out.write("							alert(\"ID를 사용할 수 있습니다.\");\n");
      out.write("							$(\"#id\").val(testId.val());						\n");
      out.write("						}\n");
      out.write("					},\n");
      out.write("					error: function(data) {\n");
      out.write("						console.log(\"실패\");\n");
      out.write("						console.log(data);\n");
      out.write("					}\n");
      out.write("				});\n");
      out.write("			} else {\n");
      out.write("				// 규칙에 맞지 않는 ID 중복 검사 한 경우 input 값 지우기\n");
      out.write("				alert(\"ID는 5글자 이상 이어야 합니다.\");\n");
      out.write("				testId.val(\"\");\n");
      out.write("				$(\"#id\").val(\"\");\n");
      out.write("			}\n");
      out.write("			\n");
      out.write("		});\n");
      out.write("		\n");
      out.write("		\n");
      out.write("		// css\n");
      out.write("		$(\"#save, #showList\").css(\"margin\", \"1px\");\n");
      out.write("		$(\".phone\").css({\"width\" :\"30%\", \"display\" : \"inline\"});\n");
      out.write("		$(\"#m_phone4\").hide();\n");
      out.write("	});	// function() end\n");
      out.write("	\n");
      out.write("	// 프로필 사진 미리보기\n");
      out.write("	function readURL(input) {\n");
      out.write("		if(input.files && input.files[0]) {\n");
      out.write("			/* console.log(\"input.files\\n\");\n");
      out.write("			console.log(input.files);\n");
      out.write("			console.log(\"input.files[0]\\n\")\n");
      out.write("			console.log(input.files[0]);\n");
      out.write("			console.log(\"$('upfile').files\")\n");
      out.write("			console.log($(\"#upfile\").files); */\n");
      out.write("			var reader = new FileReader();\n");
      out.write("			reader.onload = function(e) {\n");
      out.write("				$(\"#preview\").attr(\"src\", e.target.result);\n");
      out.write("				/* console.log(\"프로필 사진 미리보기\\n\");\n");
      out.write("				console.log(e);\n");
      out.write("				console.log(\"$('#upfile')[0].files = input.files;\"); */\n");
      out.write("				$(\"#upfile\")[0].files = input.files;\n");
      out.write("			}\n");
      out.write("			reader.readAsDataURL(input.files[0]);\n");
      out.write("			\n");
      out.write("		} else {\n");
      out.write("			$(\"#presview\").attr(\"src\", \"/resources/testFolder/iconImg.png\");\n");
      out.write("		}\n");
      out.write("	};\n");
      out.write("	\n");
      out.write("	// 폰 앞자리가 기타인 경우\n");
      out.write("	function changePhone(input) {\n");
      out.write("		if(input.value == \"etc\") {\n");
      out.write("			$(\"#m_phone4\").show();\n");
      out.write("			$(\"#m_phone1\").css(\"display\", \"block\");\n");
      out.write("		} else {\n");
      out.write("			$(\"#m_phone4\").val('');\n");
      out.write("			$(\"#m_phone4\").hide();\n");
      out.write("			$(\"#m_phone1\").css(\"display\", \"inline\");\n");
      out.write("		}\n");
      out.write("	\n");
      out.write("	}\n");
      out.write("	\n");
      out.write("	</script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
