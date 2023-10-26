package com.dialoguespace.member;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
	// 등록
	public int insertMember(MemberDTO memberDto) throws Exception;
	
	// 회원 목록
	public List<MemberDTO> memberList(Map srchInfo) throws Exception;
	
	// 회원 삭제
	public int delMember(String id) throws Exception;
	
	// 선택한 회원 삭제
	public int selDelMember(Map<String, Object> map) throws Exception;
	
	// 회원 수정 페이지로 이동
	public MemberDTO selectMember(String id) throws Exception;
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception;
	
	// 데이터 개수
	public int totalCnt(Map srchInfo) throws Exception;
	
	// 조건에 맞는 회원 카운팅
	public int countList(Map srchInfo) throws Exception;
	
	// 프로필 사진 PK 등록
	public int addFileNo(Map map) throws Exception;
	
	// 회원의 프로필 사진 PK 찾기
	public int selFileNo(String id) throws Exception;
	
	// ID 중복검사
	public int checkId(String id) throws Exception;
	
	// 로그인 - ID, PW로 회원 선택
	public MemberDTO selMemberByIdPw(MemberDTO memberDto) throws Exception;
}