package com.dialoguespace.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// Controller로 보내기 전에 처리하는 인터셉터
		// 변환이 false라면 Controller로 요청을 안함
		// 매개변수 Object는 핸들러 정보를 의미한다.(RequestMapping, DefaultServletHandler)
		
		System.out.println("Interceptor preHandle 실행됨");
		
		System.out.println("========== URL ==========");
		System.out.println(request.getRequestURL());
		System.out.println("========== URI ==========");
		System.out.println(request.getRequestURI());
		System.out.println("========== contextPath ==========");
		System.out.println(request.getContextPath());
		System.out.println("========== servletPath ==========");
		System.out.println(request.getServletPath());
		System.out.println("========== queryString ==========");
		System.out.println(request.getQueryString());
		System.out.println("========== serverName ==========");
		System.out.println(request.getServerName());
		
		
		
		if(request.getSession().getAttribute("loginSession") == null) {
			System.out.println("로그인 페이지로 이동");
			response.sendRedirect("/?uri=" + request.getRequestURI() + "&" + request.getQueryString());
			return false;
		}		
		return true;
		
		
	}	
	

	// Controller의 handler가 끝나면 처리 됨
	@Override
	public void postHandle(HttpServletRequest Request, HttpServletResponse response, Object obj, ModelAndView max) throws Exception {
		
	}
	
	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
		
	}
}
