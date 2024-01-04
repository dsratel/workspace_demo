package com.dialoguespace.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity doGoogleLogin(String code) {
		return	null;
	}
}
