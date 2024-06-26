package com.dialoguespace.board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
	// 글 작성 프로세스
	public int writeArticle(BoardDTO boardDto);
	
	// 글 전체 불러오기
	public List<BoardDTO> selectArticle(Map<String, Object> srchInfo);
	
	// seq로 게시글 정보 불러오기
	public BoardDTO selArticleBySeq(Map<String, Integer> map);

	// 가장 최신 글 seq 불러오기
	public int getLatestSeq(Map<String, Object> map);
	
	// 게시글 삭제
	public int delArticle(Map<String, Integer> map);
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto);
	
	// 조건에 맞는 게시글 개수
	public int countList(Map<String, Object> map);
	
	// 게시글 댓글 개수 증가
	public int addCommentCnt(Map<String, Object> map);
	
	// 게시글 댓글 개수 감소
	public int reduceCommentCnt(Map<String, Object> map);
	
	// 게시글 조회 수 증가
	public int addViewCnt(int seq);
	
	// 게시글 시퀀스 리스트
	public List<Integer> getSeqList(Map<String, Object> srchInfo);

}
