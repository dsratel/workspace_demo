package com.dialoguespace.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.utils.EncryptionUtils;

@Service
public class MemberService {
	
	private final MemberDAO memberDAO;
	private final EncryptionUtils encryptionUtils;
	
	@Autowired
	public MemberService(MemberDAO memberDAO, EncryptionUtils encryptionUtils) {
		this.memberDAO = memberDAO;
		this.encryptionUtils = encryptionUtils;
	}
	
	// 회원 추가
	public int insertMember(MemberDTO memberDto) throws Exception {
		//memberDto.setRegdate(new Timestamp(System.currentTimeMillis()));
		if(checkMemberDto(memberDto, true) < 0) return -1;
		
		return memberDAO.insertMember(memberDto);
	}
	
	// 회원 목록 출력
	public List<MemberDTO> toMemberList(Map<String, Object> srchInfo) throws Exception {
		List<MemberDTO> list = memberDAO.memberList(srchInfo);
		return list;
	}
	
	// 회원 삭제
	public int delMember(String id) throws Exception {
		return memberDAO.delMember(id);
	}
	
	// 선택한 회원 삭제
	public int selDelMember(String[] selectedMember) throws Exception {
		// Mybatis 쿼리문에 여러개의 값을 보내기 위하여 Map 인스턴스에 값을 담는다.
		Map<String, Object> map = new HashMap<>();
		map.put("selectedMember", selectedMember);
		return memberDAO.selDelMember(map);
	}
	
	// // 회원 상세보기 페이지로 이동 - 회원 한 명 정보 가져오기
	public MemberDTO toViewMember(String id) throws Exception {
		return memberDAO.selectMember(id);
	}
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception {
		if(checkMemberDto(memberDto, false) < 0) return -1;
		
		return memberDAO.editMember(memberDto);
	}
	
	// 조건에 맞는 회원 카운팅
	public int countList(Map<String, Object> srchInfo) throws Exception {
		return memberDAO.countList(srchInfo);
	}
	
	// 프로필 사진 PK 등록
	public int addFileNo(String id, int seq) throws Exception {
		// 파라미터 map에 등록
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("seq", seq);
		return memberDAO.addFileNo(map);
	}
	
	// 회원의 프로필 사진 PK 찾기
	public int selFileNo(String id) throws Exception {
		return memberDAO.selFileNo(id);
	}
	
	// ID 중복검사
	public int checkId(String id) throws Exception {
		return memberDAO.checkId(id);
	}
	
	// EMAIL 중복검사
	public MemberDTO checkEmail(String email) {
		return memberDAO.checkEmail(email);
	}
	
	// 회원정보 유효성 체크
	public int checkMemberDto(MemberDTO memberDto, boolean validPw) {
		//  체크	
		if(memberDto.getId().length() < 5) return -1;
		if(memberDto.getName().length() < 2) return -1;
		if(memberDto.getNickname().length() < 2) return -1;
		if(memberDto.getAddress().length() < 2) return -1;
		if(memberDto.getPhone().length() < 10) return -1;

		// 비밀번호를 체크해야 한다면? 비밀번호 유효성 검사를 하고 비밀번호를 수정해야 한다. 만일 비밀번호 유효성 검사를 하지 않아도 된다면 그냥 넘어가도 된다.
		if(validPw) return checkPassword(memberDto);
		
		return 1;
	}
	
	// 로그인 - ID, PW로 회원 선택
	public MemberDTO selMemberByIdPw (MemberDTO memberDto) throws Exception {
		// 유효성 검사
		if((memberDto.getId().length() < 5 || memberDto.getPw().length() < 8) && !memberDto.getPw().equals("aaa")) return null;
		
		return memberDAO.selMemberByIdPw(memberDto);
	}
	
	// 비밀번호 체크
	public int checkPassword(MemberDTO memberDto) {
		// 비밀번호 암호화
		if(memberDto.getPw().length() < 8) {
			return -1;
		} else {
			memberDto.setPw(encryptionUtils.getSHA512(memberDto.getPw()));
			return 1;
		}
	}
		
	// 프로필 사진 삭제
	public int delPfPhoto(String id) {
		return memberDAO.delPfPhoto(id);
	}
	
	// 비밀번호 변경
	public int changePassword(String id, String pw, String curPw) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		map.put("curPw", curPw);
		return memberDAO.changePassword(map);
	}
	
	// ID와 email로 회원정보 확인
	public int userByIdemail(MemberDTO dto) {
		return memberDAO.userByIdemail(dto);
	}
	
	// 비밀번호 초기화
	public int resetPassword(String id, String pw) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		return memberDAO.resetPassword(map);
	}
	
	// 구글 회원 DTO 만들기
	public MemberDTO setDtoByEmail(String email) {
		MemberDTO dto = new MemberDTO();
		// UUID로 무작위 str 생성
		String uuid = UUID.randomUUID().toString().substring(0, 8);
		dto.setId("");
		return dto;
	}
	
}
