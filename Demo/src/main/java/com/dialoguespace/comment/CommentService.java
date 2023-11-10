package com.dialoguespace.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.utils.EncryptionUtils;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;
	
	// 댓글 작성
	public int wrtieComment(CommentDTO commentDto) {
		// 비밀번호 암호화
		EncryptionUtils encryption = new EncryptionUtils();
		commentDto.setPw(encryption.getSHA512(commentDto.getPw()));
		
		return commentDAO.writeComment(commentDto);
	}
	
	// 댓글 목록
	public List<CommentDTO> cmtListByBoardseq(int boardseq) {
		return commentDAO.cmtListByBoardseq(boardseq);
	}

}
