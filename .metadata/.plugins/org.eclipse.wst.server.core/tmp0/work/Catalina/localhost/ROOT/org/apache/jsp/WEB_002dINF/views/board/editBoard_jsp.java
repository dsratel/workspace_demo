/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.93
 * Generated at: 2023-10-27 08:24:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class editBoard_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

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
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("	<meta charset=\"UTF-8\">\r\n");
      out.write("	<link rel=\"shortcut icon\" href=\"#\">\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/resources/css/bootstrap/bootstrap.min.css\">\r\n");
      out.write("	<script src=\"/resources/js/jquery/jquery-3.7.1.min.js\"></script>\r\n");
      out.write("	<title>edit board page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>글 수정 페이지 - 본인의 글만 수정 가능</h1>\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<form method=\"post\" name=\"editBoardForm\" action=\"/board/edit.do\" enctype=\"multipart/form-data\">\r\n");
      out.write("			<div class=\"row\">	<!-- 카테고리 -->\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("				<div class=\"col-1 tab\">\r\n");
      out.write("					<span>카테고리</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-7 cont\">\r\n");
      out.write("					<select class=\"form-select\" name=\"category\" id=\"category\">\r\n");
      out.write("						<option value=\"free\">자유게시판</option>\r\n");
      out.write("						<option value=\"notice\">공지사항</option>\r\n");
      out.write("					</select>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"row\">	<!-- 제목 -->\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("				<div class=\"col-1 tab\">\r\n");
      out.write("					<span>제목</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-7 cont\">\r\n");
      out.write("					<input class=\"form-control\" type=\"text\" name=\"title\" id=\"title\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"row\">	<!-- 작성자 -->\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("				<div class=\"col-1 tab\">\r\n");
      out.write("					<span>작성자</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-7 cont\">\r\n");
      out.write("					<input class=\"form-control\" type=\"text\" name= \"author\" id=\"author\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" readonly>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"row\">	<!-- 내용 -->\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("				<div class=\"col-1 tab\">\r\n");
      out.write("					<span>내용</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-7 cont\">\r\n");
      out.write("					<textarea class=\"form-control\" id=\"content\" name=\"content\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.content }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</textarea>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"row\">	<!-- 첨부파일 -->\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("				<div class=\"col-1 tab\">\r\n");
      out.write("					<span>첨부파일</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-7 cont attachDiv\">\r\n");
      out.write("					");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("					<input class=\"form-control\" type=\"file\" name=\"upfile\" id=\"attachfile1\">\r\n");
      out.write("					<input class=\"form-control\" type=\"file\" name=\"upfile\" id=\"attachfile2\">\r\n");
      out.write("					<input class=\"form-control\" type=\"file\" name=\"upfile\" id=\"attachfile3\">\r\n");
      out.write("					<input type=\"hidden\" name=\"attachfile\" id=\"attachfile\">	<!-- 첨부파일 유무 -->\r\n");
      out.write("					<input type=\"hidden\" name=\"fileCnt\" id=\"fileCnt\">		<!-- 첨부파일 개수 -->\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-2\"></div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"row\" id=\"btnDiv\">\r\n");
      out.write("				<div class=\"col\">\r\n");
      out.write("			    	<button type=\"button\" class=\"btn btn-primary\" id=\"editBtn\">글 수정하기</button>\r\n");
      out.write("		    		<button type=\"button\" class=\"btn btn-secondary\" id=\"listBtn\">글 목록으로</button>				\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</form>\r\n");
      out.write("	</div>\r\n");
      out.write("	<script>\r\n");
      out.write("		$(function(){\r\n");
      out.write("			// css\r\n");
      out.write("			$(\"div.tab, div.cont\").css({\"border\" : \"1px solid black\", \"padding\" : \"5px\"});\r\n");
      out.write("			$(\"button\").css({\"display\" : \"inline-block\", \"width\" : \"20%\", \"margin\": \"5px\"});\r\n");
      out.write("			$(\"#btnDiv, .tab\").css(\"text-align\", \"center\");	\r\n");
      out.write("			$(\".container\").css(\"margin-top\", \"20px\");\r\n");
      out.write("			$(\"#content\").css({\"width\" : \"100%\", \"height\" : \"300px\", \"border\" : \"none\", \"resize\" : \"none\"});\r\n");
      out.write("			$(\".img-thumbnail\").css({\"width\":\"200px\", \"height\":\"200px\"});\r\n");
      out.write("			\r\n");
      out.write("			// 첨부파일 개수만큼 <input>보이기\r\n");
      out.write(" 			for(var i = 0; i < \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileCnt}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"; i++) {\r\n");
      out.write("				$(\"#attachfile\" + (i+1)).hide();\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			// 사진 더블클릭 시 삭제\r\n");
      out.write("			$(\"img\").dblclick(function(el){\r\n");
      out.write("				$($(\"#\"+$(this).attr(\"conFile\"))).show();\r\n");
      out.write("				$(this).remove();\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			// 글 수정 버튼\r\n");
      out.write("			$(\"#editBtn\").click(function(){\r\n");
      out.write("				// 파일 개수 체크\r\n");
      out.write("				var fileCnt = 0;\r\n");
      out.write("				var files = $(\"input[type='file']\");\r\n");
      out.write("				for(var i = 0; i < files.length; i++) {\r\n");
      out.write("					if(files.get(i).files.length > 0) {\r\n");
      out.write("						fileCnt++;\r\n");
      out.write("					} else {\r\n");
      out.write("						files.get(i).name = '';\r\n");
      out.write("					}\r\n");
      out.write("				}\r\n");
      out.write("				\r\n");
      out.write("				// 파일이 있는 경우 attachfile에 y값 입력\r\n");
      out.write("				if(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileCnt}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" == 0 && fileCnt == 0) {\r\n");
      out.write("					$(\"#attachfile\").val('n');					\r\n");
      out.write("				} else {\r\n");
      out.write("					$(\"#attachfile\").val('y');					\r\n");
      out.write("				}\r\n");
      out.write("				\r\n");
      out.write("				console.log($(\"#attachfile\").val());\r\n");
      out.write("				\r\n");
      out.write("				// 파일 개수 입력\r\n");
      out.write("				$(\"#fileCnt\").val(fileCnt);\r\n");
      out.write("				\r\n");
      out.write("				//$(\"form[name='editBoardForm']\").submit();\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			// 글 목록 버튼\r\n");
      out.write("			$(\"#listBtn\").click(function(){\r\n");
      out.write("				window.location.replace(\"/board/toList\");\r\n");
      out.write("			});\r\n");
      out.write("		})	// function() end\r\n");
      out.write("	</script>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/views/board/editBoard.jsp(65,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("file");
      // /WEB-INF/views/board/editBoard.jsp(65,5) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/board/editBoard.jsp(65,5) '${filePaths }'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${filePaths }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /WEB-INF/views/board/editBoard.jsp(65,5) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVarStatus("status");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("						<img class=\"img-thumbnail\" src=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${file}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\" conFile=\"attachfile");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status.count }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\">\r\n");
            out.write("					");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }
}
