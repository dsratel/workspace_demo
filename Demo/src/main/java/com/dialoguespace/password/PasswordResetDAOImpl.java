package com.dialoguespace.password;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetDAOImpl implements PasswordResetDAO {
	
private SqlSession sqlsession;
	
	@Autowired
	public PasswordResetDAOImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	// 회원 정보 가져오기
	@Override
	public PasswordResetDTO getUserInfo(String link) {
		return sqlsession.selectOne("passwordResetMapper.getUserInfo", link);
	}
	
	// 비밀번호 초기화 URL 저장
	public int addInfo(Map<String, String> map) {
		return sqlsession.insert("passwordResetMapper.addInfo", map);
	};

}
