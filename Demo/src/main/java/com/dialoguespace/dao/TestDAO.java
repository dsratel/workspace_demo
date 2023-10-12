package com.dialoguespace.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	public int acCheck() throws Exception {
		int result = sqlsession.selectOne("testMapper.acCheck");
		System.out.println(result);
		return result;
	}
	

}
