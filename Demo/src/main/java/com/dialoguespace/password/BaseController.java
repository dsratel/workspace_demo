package com.dialoguespace.password;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.dialoguespace.member.MemberDTO;

@RestController
public class BaseController {

	private final AuthService authService;
	private final CustomMessageService customMessageService;
	private final HttpSession session;
	@Autowired
	public BaseController(AuthService authService, CustomMessageService customMessageService, HttpSession session) {
		this.authService = authService;
		this.customMessageService = customMessageService;
		this.session = session;
	}
	
	@ModelAttribute
	public void loginInfo(Model model) {
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo != null) {
			model.addAttribute("loginId", loginInfo.getId());
			model.addAttribute("masteryn", loginInfo.getMasteryn());			
		}
	}

	@GetMapping("/kakao")
	public void serviceStart(String code, HttpServletResponse response) throws IOException {
		if(authService.getKakaoAuthToken(code)) {
			//return (customMessageService.sendMyMessage() ? "메시지 전송 성공" : "메시지 전송 실패");
			String loginId = "";
			MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
			if(loginInfo != null) loginId = loginInfo.getId();
			
			String btnTitle = "버튼 제목";
			String webUrl = "http://www.naver.com";
			String mobileUrl = "http://www.daum.net";
			String objType = "text";
			String text = loginId + "님이 로그인하였습니다.";
			customMessageService.sendMyMessage(btnTitle, webUrl, mobileUrl, objType, text);
			//return "redirect:/";
			response.sendRedirect("/");
		}/*else {
			return "토큰발급 실패";
		}*/
	}
}