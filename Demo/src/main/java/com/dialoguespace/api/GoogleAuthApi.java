package com.dialoguespace.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.dialoguespace.api.config.FeignConfiguration;

@FeignClient(value="googleAuth", url="https://oauth2.googleapis.com", configuration= {FeignConfiguration.class})
public interface GoogleAuthApi {
	@PostMapping(value="/token")
	ResponseEntity<String> getAccessToken();
	
	
}
