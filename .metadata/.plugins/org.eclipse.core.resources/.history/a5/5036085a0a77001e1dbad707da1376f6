package com.dialoguespace.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/common")
public class CommonController {
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	HttpSession session;
	
	// 로그아웃
	@GetMapping(value="/logout")
	public String logout(String id) {
		System.out.println("========== CommonController - logout ==========");
		System.out.println("로그아웃 ID : " + id);
		
		session.removeAttribute("loginSession");
		System.out.println(id + "님 로그아웃 완료");
		return "home";
	}

}
