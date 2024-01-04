package com.dialoguespace.password;

import java.util.Map;

public interface PasswordResetDAO {
	
	// 회원 정보 가져오기
	public PasswordResetDTO getUserInfo(String link);
	
	// 비밀번호 초기화 URL 저장
	public int addInfo(Map<String, String> map);
	
	// 비밀번호 변경한 URL 폐기
	public int expiredURL(String url);
	
	// 동일 id의 URL이 있는 경우 폐기 처리
	public int expiredUrlById(String id);
}
