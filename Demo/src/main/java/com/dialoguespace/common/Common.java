package com.dialoguespace.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Data;

@Component
@Data
public class Common {
//	
//	private final HttpSession session;// = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//	private String contextRoot;
//	private String boardPath;
//	
//	@Autowired
//	public Common(HttpSession session) {
//		this.session = session;
//		
//		// final로 변수 선언 시 초기화 해야 하는데 생성자 생성 시 초기화 하므로 스코프 안에서 값 대입
//		contextRoot	= session.getServletContext().getRealPath("/");
//		boardPath		= contextRoot + "resources/board/";
//	}
//	
//	

}
