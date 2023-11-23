package com.dialoguespace.board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
	// 글 작성 프로세스
	public int writeArticle(BoardDTO boardDto);
	
	// 글 전체 불러오기
	public List<BoardDTO> selectArticle(Map srchInfo);
	
	// seq로 게시글 정보 불러오기
	public BoardDTO selArticleBySeq(Map map);

	// 가장 최신 글 seq 불러오기
	public int getLatestSeq(String author);
	
	// 게시글 삭제
	public int delArticle(int seq);
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto);
	
	// 조건에 맞는 게시글 개수
	public int countList(Map map);
	
	// 게시글 댓글 개수 증가
	public int addCommentCnt(int boardseq);
	
	// 게시글 댓글 개수 감소
	public int reduceCommentCnt(Map map);
	
	// 게시글 조회 수 증가
	public int addViewCnt(int seq);
	
	// 게시글 시퀀스 리스트
	public List<Integer> getSeqList(Map srchInfo);

}
