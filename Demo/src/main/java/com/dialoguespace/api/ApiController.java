package com.dialoguespace.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/api")
public class ApiController {
	private final ApiService apiService;
	
	@Autowired
	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	
	// 인가 코드 받은 후 access token으로
	@GetMapping(value="/google-login")
	public ResponseEntity<?> doGoogleLogin(String code ) {
		System.out.println("====== ApiContoller - doGoogleLogin ======");
		System.out.println(code);
		return ResponseEntity.created(URI.create("/google-login"))
                .body(apiService.doGoogleLogin(code));
	}

}
