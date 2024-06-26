package com.dialoguespace.comment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.utils.EncryptionUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentService {
	
	private final CommentDAO commentDAO;
	private final EncryptionUtils encryption;
	
	@Autowired
	public CommentService(CommentDAO commentDAO, EncryptionUtils encryption) {
		this.commentDAO = commentDAO;
		this.encryption = encryption; 
	}
	

	
	// 댓글 작성
	public int wrtieComment(CommentDTO commentDto) {
		// commentDTO 세팅
		// 비밀번호 암호화
		commentDto.setPw(encryption.getSHA512(commentDto.getPw()));
		
		int rs = 0;
				
		// 대댓글 인 경우
		if(commentDto.getPid() != 0) {
			// 대댓글 저장
			rs = commentDAO.writeReplyComment(commentDto);
		} else {
			// 댓글 저장
			 rs = commentDAO.writeComment(commentDto);
		}
		
		return rs;
	}
	
	// 댓글 목록 - boardseq별
	public List<CommentDTO> cmtListByBoardseq(int boardseq, char replyYn) {
		// 답글 구분
		Map<String, Object> map = new HashMap<>();
		map.put("boardseq", boardseq);
		map.put("replyYn", replyYn);
		
		return commentDAO.cmtListByBoardseq(map);
	}
	
	// 댓글 목록 - search condition 별
	public List<CommentDTO> cmtListBySearchcondition() {
		return commentDAO.cmtListBySearchcondition();
	}
	
	// 하위 댓글 확인
	public int hasReply(Iterator it) {
		return 0;
	}
	
	// 댓글 삭제
	public int deleteCmt(int seq, int pid) {
		Map<String, Integer> map = new HashMap<>();
		map.put("seq", seq);
		map.put("pid", pid);
		
		return commentDAO.deleteCmt(map);
	}
	
	// 댓글 비밀번호 확인
	public int passwordCheck(int seq, int pid, String pw) {
		Map<String, Object> map = new HashMap<>();
		
		// 비밀번호 암호화
		pw = encryption.getSHA512(pw);
		
		map.put("seq", seq);
		map.put("pid", pid);
		map.put("pw", pw);
		
		return commentDAO.passwordCheck(map);
		
	}
	
	// 댓글 수정
	public int editCmt(int seq, int pid, String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("seq", seq);
		map.put("pid", pid);
		map.put("content", content);
		
		return commentDAO.editCmt(map);
	}
	
	// 게시글 시퀀스로 댓글 삭제
	public int deleteCmtByBoardseq(Map<String, Integer> map) {
		return commentDAO.deleteCmtByBoardseq(map);
	}
	
	// 대댓글 목록
	public List<CommentDTO> cmtListByPid(int pid) {
		System.out.println("========== Comment Service - cmtListByPid ==========");
		System.out.println("pid : " + pid);
		List<CommentDTO> list = commentDAO.cmtListByPid(pid);
		log.info(list.toString());
		return list;
	}
	
	// 댓글 목록(댓글+대댓글)
	public List<CommentDTO> cmtList(String id) {
		return commentDAO.cmtList(id);
	}
	
	// 댓글 검색 조건 만들기
	public Map<String, Object> makeSrchInfo(String id, String searchType, String searchKeyword) {
		// 검색 조건 map 생성
		Map<String, Object> map = new HashMap<>();
		
		// map에 검색 조건 담기
		map.put("id", id);
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		
		return map;
	}
	
	// 조건에 맞는 댓글 갯수
	public int countList(Map<String, Object> srchInfo) {
		return commentDAO.countList(srchInfo);
	}
	
	
	// 조건에 맞는 댓글 목록
	public List<CommentDTO> commentList(Map<String, Object> srchInfo) {
		return commentDAO.commentList(srchInfo);
	}
	
	// 검색 조건에 맞는 댓글 시퀀스


}
