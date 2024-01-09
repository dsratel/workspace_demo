package com.dialoguespace.password;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dialoguespace.member.MemberService;
import com.dialoguespace.utils.EncryptionUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/password")
@Slf4j
public class PasswordResetController {

	private MemberService memberService;
	private PasswordResetService passwordResetService;
	private AuthService authService;
	private JavaMailSenderImpl mailSender;
	private final EncryptionUtils encryptionUtils;
	private final HttpSession session;
	
	@Autowired
	public PasswordResetController(MemberService memberService, PasswordResetService passwordResetService, AuthService authService
								, JavaMailSenderImpl mailSender, EncryptionUtils encryptionUtils, HttpSession session) {
		this.memberService = memberService;
		this.passwordResetService = passwordResetService;
		this.authService = authService;
		this.mailSender = mailSender;
		this.encryptionUtils = encryptionUtils;
		this.session = session;
	}
	
	// 비밀번호 변경 URL 전송
	@PostMapping(value="/sendURL")
	public String sendURL(String id, String email) throws MessagingException {
		// 비밀번호 변경 URL 생성
		String uuid = UUID.randomUUID().toString();
		String url = "demo.com:8080/password/resetPassword?link=" + uuid;
		
		// DB저장
		passwordResetService.addInfo(url, id, email);
		// 이메일 전송
		// [참고 : https://kimvampa.tistory.com/93]
        String subject = "[NOLBU] PASSWORD RESET EMAIL";
        String content = "URL을 주소창에 복사하여 접속하세요.\n" + url;
        String from = "ratelamsh@gmail.com";
        String to = email;
        //.createMimeMessage()
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
        // true는 멀티파트 메세지를 사용하겠다는 의미
        
        /*
         * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
         * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
         */
        mailHelper.setFrom(from);
        // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
        // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
        //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
        mailHelper.setTo(to);
        mailHelper.setSubject(subject);
        mailHelper.setText(content, true);
        // true는 html을 사용하겠다는 의미입니다.
        
        /*
         * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
         */
        
        mailSender.send(mail);
		return "redirect:/"; 
	}
	
	// 비밀번호 변경 페이지 전송
	@GetMapping(value="/resetPassword")
	public String toResetPassword(String link, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException{
		// link를 검색하여 ID, EMAIL 가져오기
		PasswordResetDTO dto = passwordResetService.getUserInfo(link);
		if(dto != null) {
			System.out.println(dto.toString());
			// 요청 email과 DB에 저장된 email 비교
			
			// 비밀번호 변경 페이지 전송
			// KeyPariGenerator 인스턴스 생성(RSA 알고리즘)
			encryptionUtils.genRsaInstance(model);
			
			model.addAttribute("link", link);
			
			return "/member/resetPassword";			
		} else {
			return "/wrongURL";
		}
		
	}
	
	// 비밀번호 초기화
	@PostMapping(value="/resetPassword.do")
	public String resetPassword(String pw, String link, Model model) throws IllegalBlockSizeException, BadPaddingException
					, InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
		// link를 검색하여 ID, EMAIL 가져오기
		PasswordResetDTO dto = passwordResetService.getUserInfo(link);
		
		int rs = 0;
		log.info("수정 str : " + pw);
		// RSA PrivateKey
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		if(!pw.equals("")) {
			String strPw = encryptionUtils.decryptRsa(privateKey, pw);
			log.info("수정 비밀번호 : " + strPw);
			strPw = encryptionUtils.getSHA512(strPw);
			log.info("수정 비밀번호 암호화 : " + strPw);
			rs = memberService.resetPassword(dto.getId(), strPw);
		}
	
		if(rs > 0) passwordResetService.expiredURL(link);
		
		return "redirect:/";
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
