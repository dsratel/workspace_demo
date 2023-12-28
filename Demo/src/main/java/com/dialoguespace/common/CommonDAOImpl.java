package com.dialoguespace.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dialoguespace.vo.FileVO;

@Repository
public class CommonDAOImpl implements CommonDAO {
	
	private SqlSession sqlsession;
	
	@Autowired
	public CommonDAOImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
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
	public int delFileDbBySeq(int seq) throws Exception {
		return sqlsession.update("commonMapper.delFileDbBySeq", seq);
	}
	
	// fileno로 특정 파일 DB정보 업데이트
	@Override
	public int updateFileDB(FileVO file) throws Exception {
		return sqlsession.update("commonMapper.updateFileDB", file);
	}
	
	// id와 category로 파일 경로 찾기
	@Override
	public List<String> selFilePathByIdCat(Map<String, String> map) {
		return sqlsession.selectList("commonMapper.selFilePathByIdCat", map);
	}
	
	// id와 category로 파일 DB 삭제
	@Override
	public int delFileDbByIdCat(Map<String, String> map) {
		return sqlsession.update("commonMapper.delFileDbByIdCat", map);
	}
	
	// id와 category로 fileparent update
	@Override
	public int modifyFileparent(Map<String, Object> map) { 
		return sqlsession.update("commonMapper.modifyFileparent", map);
	}
	
	// seq로 File db 찾기
	@Override
	public List<FileVO> SelFileById(Map<String, String> map) {
		return sqlsession.selectList("commonMapper.selFileById", map);
	}
	
	// fileparent로 file path 찾기
	@Override
	public List<String> SelFilePathById(Map<String, Object> map) {
		return sqlsession.selectList("commonMapper.selFilePathById", map);
	}
	
	// 삭제할 seq 찾기
	public List<Integer> getDelSeq(Map<String, Object> map) {
		return sqlsession.selectList("commonMapper.getDelSeq", map);
	}
	
	// seq로 sysname 찾기
	@Override
	public String getSysNameBySeq(Map<String, Object> map) {
		return sqlsession.selectOne("commonMapper.getSysNameBySeq", map);
	}
}
