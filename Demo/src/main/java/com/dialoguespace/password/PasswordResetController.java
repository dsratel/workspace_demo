package com.dialoguespace.password;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dialoguespace.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/password")
@Slf4j
public class PasswordResetController {
	

	
	private MemberService memberService;
	private PasswordResetService passwordResetService;
	
	private AuthService authService;
	
	@Autowired
	public PasswordResetController(MemberService memberService, PasswordResetService passwordResetService, AuthService authService) {
		this.memberService = memberService;
		this.passwordResetService = passwordResetService;
		this.authService = authService;
	}
	
	// 비밀번호 변경 URL 전송
	@GetMapping(value="/sendURL")
	public String sendURL(String id, String email) {
		// 비밀번호 변경 URL 생성
		String uuid = UUID.randomUUID().toString();
		String url = "localhost:8080/password/resetPassword?link="+uuid;
		
		// DB저장
		passwordResetService.addInfo(id, email);
		
		// 
		
		
		
		return ""; 
	}
	
	// 비밀번호 변경 페이지 전송
	@GetMapping(value="/resetPassword")
	public String toResetPassword(String link) {
		// link를 검색하여 ID, EMAIL 가져오기
		PasswordResetDTO dto = passwordResetService.getUserInfo(link);
		
		// 요청 email과 DB에 저장된 email 비교
		
		// 비밀번호 변경 페이지 전송
		
		return "";
		
	}
	
	// 카카오 테스트
	// https://kauth.kakao.com/oauth/authorize?client_id=85bd70c57eabfc320c9269334d3f584d&redirect_uri=http://demo.com:8080/kakao&response_type=code&scope=talk_message
	// ot94ZR7ZaidjHKcndAl0IXYjqEA1CBPMOLlQGloR_frfvRNoD8BSyK6ZR_UKKcjaAAABjK8fFVL_A_o_BVb6-Q
//	@GetMapping(value="/kakao")
//	public String kakao(String code) {
//		String kakaoAuthToken = authService.getKakaoAuthToken(code); 
//		return kakaoAuthToken;
//	}
}
