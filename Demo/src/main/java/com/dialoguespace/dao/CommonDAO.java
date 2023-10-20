package com.dialoguespace.dao;

import com.dialoguespace.vo.FileVO;

public interface CommonDAO {
	// 파일 저장
	public int saveNewFile(FileVO file) throws Exception;
	
	// 파일 삭제
	public int delFile(FileVO file) throws Exception;
	
	// 파일 PK 찾기
	public int selectFilePK(FileVO file) throws Exception;
	
	// 파일 경로 검색
	public String getPath(int seq) throws Exception;
	
	// seq로 특정 파일 DB정보 삭제
	public int delFileDB(int seq) throws Exception;
	
	// seq로 특정 파일 DB정보 업데이트
	public int updateFileDB(FileVO file) throws Exception;
}
