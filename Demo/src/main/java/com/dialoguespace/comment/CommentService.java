package com.dialoguespace.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.utils.EncryptionUtils;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;
	
	private EncryptionUtils encryption = new EncryptionUtils();
	
	// 댓글 작성
	public int wrtieComment(CommentDTO commentDto) {
		// commentDTO 세팅
		// 비밀번호 암호화
		commentDto.setPw(encryption.getSHA512(commentDto.getPw()));
		
		int rs = 0;
				
		// 답글 인 경우
		if(commentDto.getPid() != 0) {
			// 답글 저장
			rs = commentDAO.writeReplyComment(commentDto);
			
			
		} else {
			// 댓글 저장
			 rs = commentDAO.writeComment(commentDto);
			if(commentDto.getDepth() == 0) {
				commentDAO.editRootSeq();	// depth 0 일 때 rootseq 수정
			}
		}
		
		
		
		return rs;
	}
	
	// 댓글 목록
	public List<CommentDTO> cmtListByBoardseq(int boardseq) {
		return commentDAO.cmtListByBoardseq(boardseq);
	}
	
	// 하위 댓글 확인
	public int hasReply(Iterator it) {
		return 0;
	}
	
	// 댓글 삭제
	public int deleteCmt(int seq, int pid) {
		Map map = new HashMap();
		map.put("seq", seq);
		map.put("pid", pid);
		
		return commentDAO.deleteCmt(map);
	}
	
	// 댓글 비밀번호 확인
	public int passwordCheck(int seq, int pid, String pw) {
		System.out.println("========== CommentService - passwordCheck ==========");
		System.out.println("seq : " + seq + " / pid : " + pid + " / pw : " + pw);
		
		Map map = new HashMap();
		
		// 비밀번호 암호화
		pw = encryption.getSHA512(pw);
		
		map.put("seq", seq);
		map.put("pid", pid);
		map.put("pw", pw);
		
		return commentDAO.passwordCheck(map);
		
	}
	
	// 댓글 수정
	public int editCmt(int seq, int pid, String content) {
		Map map = new HashMap();
		map.put("seq", seq);
		map.put("pid", pid);
		map.put("content", content);
		
		return commentDAO.editCmt(map);
	}
	
	// 게시글 시퀀스로 댓글 삭제
	public int deleteCmtByBoardseq(int boardseq) {
		return commentDAO.deleteCmtByBoardseq(boardseq);
	}
	
	// 대댓글 목록
	public List<CommentDTO> cmtListByPid(int pid) {
		return commentDAO.cmtListByPid(pid);
	}

}
