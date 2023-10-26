package com.dialoguespace.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.utils.EncryptionUtils;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	// 회원 추가
	public int insertMember(MemberDTO memberDto) throws Exception {
		//memberDto.setRegdate(new Timestamp(System.currentTimeMillis()));
		if(checkMemberDto(memberDto) < 0) return -1;
		
		System.out.println("MemberService memberDto 등록");
		return memberDAO.insertMember(memberDto);
	}
	
	// 회원 목록 출력
	public List<MemberDTO> toMemberList(Map srchInfo) throws Exception {
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
	
	// 회원 수정 페이지로 이동 - 회원 한 명 정보 가져오기
	public MemberDTO toEditMember(String id) throws Exception {
		return memberDAO.selectMember(id);
	}
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception {
		if(checkMemberDto(memberDto) < 0) return -1;
		
		return memberDAO.editMember(memberDto);
	}
	
	// 조건에 맞는 회원 카운팅
	public int countList(Map srchInfo) throws Exception {
		return memberDAO.countList(srchInfo);
	}
	
	// 프로필 사진 PK 등록
	public int addFileNo(String id, int seq) throws Exception {
		// 파라미터 map에 등록
		Map map = new HashMap();
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
	
	// 회원정보 유효성 체크
	public int checkMemberDto(MemberDTO memberDto) {
		//  체크	
		if(memberDto.getId().length() < 5) return -1;
		if(memberDto.getPw().length() < 8) return -1;
		if(memberDto.getName().length() < 2) return -1;
		if(memberDto.getNickname().length() < 2) return -1;
		if(memberDto.getAddress().length() < 2) return -1;
		if(memberDto.getPhone().length() < 10) return -1;
		// 비밀번호 암호화
		EncryptionUtils encryption = new EncryptionUtils();
		memberDto.setPw(encryption.getSHA512(memberDto.getPw()));
		return 1;
	}
	
	// 로그인 - ID, PW로 회원 선택
	public MemberDTO selMemberByIdPw (MemberDTO memberDto) throws Exception {
		// 유효성 검사
		if(memberDto.getId().length() < 5 || memberDto.getPw().length() < 8) return null;
		
		// 비밀번호 암호화
		EncryptionUtils encryption = new EncryptionUtils();
		memberDto.setPw(encryption.getSHA512(memberDto.getPw()));
		
		return memberDAO.selMemberByIdPw(memberDto);
	}
	
}