package com.dialoguespace.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/common")
@Slf4j
public class CommonController {
	
	private final HttpSession session;
	
	@Autowired
	public CommonController(HttpSession session) {
		this.session = session;
	}
	
	// 로그아웃
	@GetMapping(value="/logout")
	public String logout(String id) {
		log.info("========== CommonController - logout ==========");
		log.info("logout ID : " + id);
		
		session.removeAttribute("loginSession");
		log.info(id + "님 로그아웃 완료");
		return "redirect:/";
	}
	
	// seq로 파일 지우기
	@GetMapping(value="/delFile")
	public String delFileBySeq(int seq) {
		log.info("========== CommonController - delFileBySeq ==========");
		return "";
	}
}
