/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.93
 * Generated at: 2023-12-12 09:53:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class changePasswordPop_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("jar:file:/D:/workspace_demo/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Demo/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1702283681437L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1702262660095L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1696575023656L));
    _jspx_dependants.put("jar:file:/D:/workspace_demo/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Demo/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153352682000L));
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

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("	<meta charset=\"UTF-8\">\r\n");
      out.write("	<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"data:image/x-icon;\">\r\n");
      out.write("    <!-- Bootstrap core JavaScript-->\r\n");
      out.write("    <script src=\"/resources/template/vendor/jquery/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"/resources/template/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    \r\n");
      out.write("	<!-- Custom template -->\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/resources/template/css/custom.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/resources/template/css/sb-admin-2.min.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/resources/template/vendor/fontawesome-free/css/all.min.css\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("       \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"/resources/js/rsa/jsbn.js\"></script>\r\n");
      out.write("<script src=\"/resources/js/rsa/rsa.js\"></script>\r\n");
      out.write("<script src=\"/resources/js/rsa/prng4.js\"></script>\r\n");
      out.write("<script src=\"/resources/js/rsa/rng.js\"></script>\r\n");
      out.write("	\r\n");
      out.write("<body id=\"page-top\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Page Wrapper -->\r\n");
      out.write("    <div id=\"wrapper\">\r\n");
      out.write("	\r\n");
      out.write("        <!-- Content Wrapper -->\r\n");
      out.write("        <div id=\"content-wrapper\" class=\"d-flex flex-column\">\r\n");
      out.write("\r\n");
      out.write("            <!-- Main Content -->\r\n");
      out.write("            <div id=\"content\">\r\n");
      out.write("\r\n");
      out.write("                <!-- Begin Page Content -->\r\n");
      out.write("                <div class=\"container-fluid\">\r\n");
      out.write("                \r\n");
      out.write("                    <!-- Content Row -->\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                \r\n");
      out.write("                		<h1>change password</h1>\r\n");
      out.write("                		<form name=\"chgPwForm\" action=\"/member/changePassword.do\" method=\"post\">\r\n");
      out.write("                			<div class=\"row\">\r\n");
      out.write("                				<div class=\"col\">비밀번호</div>\r\n");
      out.write("                				<div class=\"col\"><input class=\"form-control\" type=\"password\" id=\"password\"></div>\r\n");
      out.write("                			</div>\r\n");
      out.write("                			<div class=\"row\">\r\n");
      out.write("                				<div class=\"col\">비밀번호 확인</div>\r\n");
      out.write("                				<div class=\"col\"><input class=\"form-control\" type=\"password\" id=\"password\"></div>\r\n");
      out.write("                			</div>\r\n");
      out.write("	                		<input type=\"hidden\" id=\"id\" name=\"id\">\r\n");
      out.write("	                		<input type=\"hidden\" id=\"pw\" name=\"pw\">                		\r\n");
      out.write("                		</form>\r\n");
      out.write("                		\r\n");
      out.write("                		<button type=\"button\" class=\"btn btn-primary\" id=\"editBtn\" onclick=\"editPassword()\">수정</button>\r\n");
      out.write("                		<button type=\"button\" class=\"btn btn-secondary\" id=\"cancelBtn\" onclick=\"window.close()\">취소</button>\r\n");
      out.write("                		\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /.container-fluid -->\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <!-- End of Main Content -->\r\n");
      out.write("\r\n");
      out.write("            <!-- Footer -->\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <footer class=\"sticky-footer bg-white\">\r\n");
      out.write("                <div class=\"container my-auto\">\r\n");
      out.write("                    <div class=\"copyright text-center my-auto\">\r\n");
      out.write("                        <span>Copyright &copy; NOLBU 2023</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </footer>\r\n");
      out.write("            <!-- End of Footer -->");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- End of Content Wrapper -->\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- End of Page Wrapper -->\r\n");
      out.write("		\r\n");
      out.write("	<!-- Core plugin JavaScript-->\r\n");
      out.write("    <script src=\"/resources/template/vendor/jquery-easing/jquery.easing.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Custom scripts for all pages-->\r\n");
      out.write("    <script src=\"/resources/template/js/sb-admin-2.min.js\"></script>\r\n");
      out.write("    \r\n");
      out.write("    <script>\r\n");
      out.write("	    const getByteLengthOfString = function (s, b, i, c) {\r\n");
      out.write("		  for (b = i = 0; (c = s.charCodeAt(i++)); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);\r\n");
      out.write("		  return b;\r\n");
      out.write("		};\r\n");
      out.write("		\r\n");
      out.write("		// 저장할 때 모든 데이터 유효성 검사 후 input hidden에 넣고 서버로 요청\r\n");
      out.write("		$(\"#save\").click(function(){\r\n");
      out.write("			// 비밀번호 유효성 검사\r\n");
      out.write("			var testPw = $(\"#pw\").val();\r\n");
      out.write("			var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;\r\n");
      out.write("			if(testPw.length < 8) {\r\n");
      out.write("				alert(\"비밀번호는 8자리 이상이어야 합니다.\");\r\n");
      out.write("				return;\r\n");
      out.write("			} else {\r\n");
      out.write("				if(!regex.test(testPw)){\r\n");
      out.write("					alert(\"대문자, 소문자, 특수문자, 숫자 각 1개 이상 씩 입력하세요. (8~25자리)\");\r\n");
      out.write("					return;\r\n");
      out.write("				} else {\r\n");
      out.write("					// 비밀번호 RSA 암호화\r\n");
      out.write("					//// RSAKey 객체 생성\r\n");
      out.write("					const rsa = new RSAKey();\r\n");
      out.write("					//// 공개키 설정 값 가져오기\r\n");
      out.write("					const rsaPublicKeyModulus = $(\"#rsaPublicKeyModulus\").val();\r\n");
      out.write("					const rsaPublicKeyExponent = $(\"#rsaPublicKeyExponent\").val();\r\n");
      out.write("					//// RSAKey 객체에 공개키 설정\r\n");
      out.write("					rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);\r\n");
      out.write("					//// RSAKey 객체의 encrypt메서드 이용하여 비밀번호 암호화\r\n");
      out.write("					const securedPassword = rsa.encrypt(testPw);\r\n");
      out.write("					console.log(\"입력 비밀번호 : \" + testPw);\r\n");
      out.write("					console.log(\"암호화 비밀번호 : \" + securedPassword);\r\n");
      out.write("					console.log(\"암호화 길이 : \" + getByteLengthOfString(securedPassword) + \"Bytes\");\r\n");
      out.write("					\r\n");
      out.write("					$(\"#pw\").val(securedPassword);\r\n");
      out.write("				}\r\n");
      out.write("			}\r\n");
      out.write("    </script>\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
