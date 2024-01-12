package com.dialoguespace.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dialoguespace.api.dto.GoogleInfResponse;
import com.dialoguespace.api.dto.GoogleRequest;
import com.dialoguespace.api.dto.GoogleResponse;
import com.dialoguespace.api.service.ApiService;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.member.MemberService;

@RestController
@RequestMapping(value="/api")
public class ApiController {
	private final ApiService apiService;
	private final MemberService memberService;
	private final HttpSession session;
	
	@Value("${google.client.id}")
	private String client_id;
	
	@Value("${google.client.pw}")
	private String client_pw;
	
	@Autowired
	public ApiController(ApiService apiService, MemberService memberService, HttpSession session) {
		this.apiService = apiService;
		this.memberService = memberService;
		this.session = session;
	}
	
	@PostMapping(value="/google-login")
	@ResponseBody
	public String getGoogleLoginUrl() {
		String requestUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + client_id +
				"&redirect_uri=http://demo.com:8080/api/google-login&response_type=code&scope=email%20profile";
		
		return requestUrl;
	}
	
//	@GetMapping(value="/google-login")
//	public ResponseEntity<?> doGoogleLogin(String code ) {
//		System.out.println("====== ApiContoller - doGoogleLogin ======");
//		System.out.println(code);
//		return ResponseEntity.created(URI.create("/google-login"))
//                .body(apiService.doGoogleLogin(code));
//	}
	
	@GetMapping(value="/google-login")
    public String loginGoogle(@RequestParam(value = "code") String authCode, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(client_id)
                .clientSecret(client_pw)
                .code(authCode)
                .redirectUri("http://demo.com:8080/api/google-login")
                .grantType("authorization_code").build();
        
        // 제네릭으로 응답 객체의 타입을 정했으므로 인스턴스.getBody()를 한 후에 응답 객체의 필드안에 있는 값을 가져올 수 있다.
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        System.out.println(resultEntity.toString());
        System.out.println(resultEntity.getBody());
        String jwtToken = resultEntity.getBody().getId_token();
        Map<String, String> map = new HashMap<>();
        map.put("id_token", jwtToken);
        
        // google api에서 넘어온 토큰으로 사용자 정보 가져오기
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);
        System.out.println(resultEntity2.toString());
        String email = resultEntity2.getBody().getEmail();
        String name = resultEntity2.getBody().getName();
        
        // 계정이 있는지 확인
        List<MemberDTO> list = memberService.checkEmail(email);
        MemberDTO dto = new MemberDTO();
        if(list.size() == 0) {
    	// 없으면 회원가입 후 로그인 화면 띄우기
        	dto = memberService.createDtoBySocialAccount(email, name);
        	try {
        		memberService.insertMember(dto);        		
        	} catch(Exception e) {
        		e.printStackTrace();
        		return "errorPage";
        	}	
        } else {
        	dto = list.get(0);
        }
        
        session.setAttribute("loginSession", dto);
		model.addAttribute("dto", dto);

        return "<script>window.opener.location.reload();window.close();</script>";
    }
}


