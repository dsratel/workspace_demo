package com.dialoguespace.password;

import java.util.Map;

public interface PasswordResetDAO {
	
	// 회원 정보 가져오기
	public PasswordResetDTO getUserInfo(String link);
	
	// 비밀번호 초기화 URL 저장
	public int addInfo(Map<String, String> map);

}
