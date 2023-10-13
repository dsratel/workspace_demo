package com.dialoguespace.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.dao.MemberDAO;
import com.dialoguespace.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	// 회원 추가
	public int insertMember(MemberDTO memberDto) throws Exception {
		memberDto.setM_regdate(new Timestamp(System.currentTimeMillis()));
		return memberDAO.insertMember(memberDto);
	}
	
	// 회원 목록 출력
	public List<MemberDTO> toMemberList() throws Exception {
		List<MemberDTO> list = memberDAO.memberListAll();
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
	public MemberDTO toEditMember(String m_id) throws Exception {
		return memberDAO.selectMember(m_id);
	}
	
	// 회원 정보 수정
	public int editMember(MemberDTO memberDto) throws Exception {
		return memberDAO.editMember(memberDto);
	}
	
}