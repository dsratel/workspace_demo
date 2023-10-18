package com.dialoguespace.dao;

import java.util.List;

import com.dialoguespace.vo.FileVO;

public interface CommonDAO {
	// 파일 저장
	public int saveFile(FileVO file) throws Exception;
	
	// 파일 삭제
	public int delFile(FileVO file) throws Exception;
}
