package com.dialoguespace.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler
	public String toError(Exception e) {
		System.out.println("익셉션 클래스 실행됨");
		e.printStackTrace();
		return "errorPage";
	}
}
