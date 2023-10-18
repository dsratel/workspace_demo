package com.dialoguespace.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dialoguespace.vo.FileVO;

@Repository
public class CommonDAOImpl implements CommonDAO {
	
	@Autowired
	SqlSession sqlsession;

	
	// 파일 저장
	@Override
	public int saveFile(FileVO file) throws Exception {
		System.out.println("CommonDAOImpl saveFile 진입");
		return sqlsession.insert("commonMapper.saveFile", file);
	}

	// 파일 삭제
	@Override
	public int delFile(FileVO file) throws Exception {
		System.out.println("CommonDAOImpl delFile 진입");
		return sqlsession.delete("commonMapper.delFile", file);
	}
}
