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
	public int saveNewFile(FileVO file) throws Exception {
		System.out.println("CommonDAOImpl saveFile 진입");
		return sqlsession.insert("commonMapper.saveFile", file);
	}

	// 파일 삭제
	@Override
	public int delFile(FileVO file) throws Exception {
		System.out.println("CommonDAOImpl delFile 진입");
		return sqlsession.delete("commonMapper.delFile", file);
	}
	
	// 파일 PK 찾기
	@Override
	public int selectFilePK(FileVO file) throws Exception {
		return sqlsession.selectOne("commonMapper.selectFilePK", file);
	}
	
	// 파일 경로 검색
	@Override
	public String getPath(int seq) throws Exception {
		return sqlsession.selectOne("commonMapper.getPath", seq);
	}
	
	// fileno로 특정 파일 DB정보 삭제
	@Override
	public int delFileDB(int seq) throws Exception {
		return sqlsession.update("commonMapper.delFileDB", seq);
	}
	
	// fileno로 특정 파일 DB정보 업데이트
	@Override
	public int updateFileDB(FileVO file) throws Exception {
		return sqlsession.update("commonMapper.updateFileDB", file);
	}
}
