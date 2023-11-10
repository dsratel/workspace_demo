package com.dialoguespace.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public interface CommentDAO {
	
	// 댓글 작성
	public int writeComment(CommentDTO commentDto);
	
	// 댓글 목록
	public List<CommentDTO> cmtListByBoardseq(int boardseq);

}
