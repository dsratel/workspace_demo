package com.dialoguespace.comment;

import java.util.List;
import java.util.Map;

public interface CommentDAO {
	
	// 댓글 작성
	public int writeComment(CommentDTO commentDto);
	
	// 댓글 목록
	public List<CommentDTO> cmtListByBoardseq(int boardseq);
	
	// 댓글 삭제
	public int deleteCmt(int seq);
	
	// 댓글 비밀번호 확인
	public int passwordCheck(Map map);
	
	// 댓글 수정
	public int editCmt(Map map);

}
