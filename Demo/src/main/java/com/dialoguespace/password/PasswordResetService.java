package com.dialoguespace.password;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {
	
	private PasswordResetDAO passwordResetDAO;
	
	@Autowired
	public PasswordResetService (PasswordResetDAO passwordResetDAO) {
		this.passwordResetDAO = passwordResetDAO;
	}
	
	
	// 회원 정보 가져오기
	public PasswordResetDTO getUserInfo(String link) {
		return passwordResetDAO.getUserInfo(link);
	}
	
	// 비밀번호 변경 URL 정보 저장
	public int addInfo(String id, String email) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		return passwordResetDAO.addInfo(map);
	}

}
