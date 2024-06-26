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
	@Override
	public int addInfo(Map<String, String> map) {
		return sqlsession.insert("passwordResetMapper.addInfo", map);
	};
	
	// 비밀번호 변경한 URL 폐기
	@Override
	public int expiredURL(String url) {
		return sqlsession.update("passwordResetMapper.expiredURL", url);
	}
	
	// 동일 id의 URL이 있는 경우 폐기 처리
	@Override
	public int expiredUrlById(String id) {
		return sqlsession.update("passwordResetMapper.expiredUrlById", id);
	};

}
