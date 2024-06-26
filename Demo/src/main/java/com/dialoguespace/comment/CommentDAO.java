package com.dialoguespace.comment;

import java.util.List;
import java.util.Map;

public interface CommentDAO {
	
	// 댓글 작성
	public int writeComment(CommentDTO commentDto);
	
	// 댓글 목록 - boardseq별
	public List<CommentDTO> cmtListByBoardseq(Map<String, Object> map);
	
	// 댓글 목록 - search condition 별
	public List<CommentDTO> cmtListBySearchcondition();
	
	// 댓글 삭제
	public int deleteCmt(Map<String, Integer> map);
	
	// 댓글 비밀번호 확인
	public int passwordCheck(Map<String, Object> map);
	
	// 댓글 수정
	public int editCmt(Map<String, Object> map);
	
	// depth 0 댓글 rootseq 수정
	public int editRootSeq();
	
	// 동일 rootseq, depth의 마지막 re_order 가져오기
	public int getNextReorder(Map map);
	
	// 댓글 정렬
	public int sortComment(Map map);
	
	// 게시글 시퀀스로 댓글 삭제
	public int deleteCmtByBoardseq(Map<String, Integer> map);
	
	// 대댓글 작성
	public int writeReplyComment(CommentDTO commentDto);
	
	// 대댓글 목록
	public List<CommentDTO> cmtListByPid(int pid);
	
	// 대댓글 삭제
	public int deleteReplyCmt(int seq);
	
	// 댓글 목록(댓글+대댓글)
	public List<CommentDTO> cmtList(String id);
	
	// 조건에 맞는 댓글 개수
	public int countList(Map<String, Object> srchInfo);
	
	// 조건에 맞는 댓글 목록
	public List<CommentDTO> commentList(Map<String, Object> srchInfo);

}
