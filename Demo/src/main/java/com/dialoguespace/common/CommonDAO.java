package com.dialoguespace.common;

import java.util.List;
import java.util.Map;

import com.dialoguespace.vo.FileVO;

public interface CommonDAO {
	// 파일 저장
	public int saveNewFile(FileVO file) throws Exception;
	
	// 파일 삭제
	public int delFile(FileVO file) throws Exception;
	
	// 파일 PK 찾기
	public int selectFilePK(FileVO file) throws Exception;
	
	// 파일 경로 검색
	public String getPath(int seq);
	
	// seq로 특정 파일 DB정보 삭제
	public int delFileDbBySeq(int seq) throws Exception;
	
	// seq로 특정 파일 DB정보 업데이트
	public int updateFileDB(FileVO file) throws Exception;
	
	// id와 category로 파일 경로 찾기
	public List<String> selFilePathByIdCat(Map<String, String> map);
	
	// id와 category로 파일 DB 삭제
	public int delFileDbByIdCat(Map<String, String> map);
	
	// id와 category로 fileparent update
	public int modifyFileparent(Map<String, Object> map);
	
	// seq로 File db 찾기
	public List<FileVO> SelFileById(Map<String, String> map);
	
	// fileparent로 file path 찾기
	public List<String> SelFilePathById(Map<String, Object> map);
	
	// 삭제할 seq 찾기
	public List<Integer> getDelSeq(Map<String, Object> map);

	// seq로 sysname 찾기
	public String getSysNameBySeq(Map<String, Object> map);
}
