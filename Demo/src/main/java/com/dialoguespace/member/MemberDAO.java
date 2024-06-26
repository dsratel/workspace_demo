package com.dialoguespace.member;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
	// 등록
	public int insertMember(MemberDTO memberDto) throws NullPointerException;
	
	// 회원 목록
	public List<MemberDTO> memberList(Map<String, Object> srchInfo) throws Exception;
	
	// 회원 삭제
	public int delMember(String id) throws Exception;
	
	// 선택한 회원 삭제
	public int selDelMember(Map<String, Object> map) throws Exception;
	
	// 회원 수정 페이지로 이동
	public MemberDTO selectMember(String id) throws Exception;
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception;
	
	// 데이터 개수
	public int totalCnt(Map<String, Object> srchInfo) throws Exception;
	
	// 조건에 맞는 회원 카운팅
	public int countList(Map<String, Object> srchInfo) throws Exception;
	
	// 프로필 사진 PK 등록
	public int addFileNo(Map<String, Object> map) ;
	
	// 회원의 프로필 사진 PK 찾기
	public int selFileNo(String id) throws Exception;
	
	// ID 중복검사
	public int checkId(String id) throws Exception;
	
	// EMAIL 중복검사
	public List<MemberDTO> checkEmail(String email);
	
	// 로그인 - ID, PW로 회원 선택
	public MemberDTO selMemberByIdPw(MemberDTO memberDto) ;
	
	// 프로필 사진 삭제
	public int delPfPhoto(String id);
	
	// 비밀번호 변경
	public int changePassword(Map<String, String> map);
	
	// ID와 email로 회원정보 확인
	public int userByIdEmail(MemberDTO dto);

	// 비밀번호 초기화
	public int resetPassword(Map<String, String> map);
}
