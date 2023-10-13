package com.dialoguespace.dao;

import java.util.List;
import java.util.Map;

import com.dialoguespace.dto.MemberDTO;

public interface MemberDAO {
	// 등록
	public int insertMember(MemberDTO memberDto) throws Exception;
	
	// 전체 목록
	public List<MemberDTO> memberListAll() throws Exception;
	
	// 회원 삭제
	public int delMember(String id) throws Exception;
	
	// 선택한 회원 삭제
	public int selDelMember(Map<String, Object> map) throws Exception;
	
	// 회원 수정 페이지로 이동
	public MemberDTO selectMember(String m_id) throws Exception;
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception;
	
}