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
	public int addInfo(String url, String id, String email) {
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		map.put("id", id);
		map.put("email", email);
		// 동일 id의 url이 있는 경우 expired = 'y' 처리
		passwordResetDAO.expiredUrlById(id);
		return passwordResetDAO.addInfo(map);
	}
	
	// 비밀번호 변경한 URL 폐기
	public int expiredURL(String url) {
		return passwordResetDAO.expiredURL(url);
	}
}
