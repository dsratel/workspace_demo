package com.dialoguespace.board;

import java.util.List;

public interface BoardDAO {
	// 글 작성 프로세스
	public int writeArticle(BoardDTO boardDto);
	
	// 글 전체 불러오기
	public List<BoardDTO> selectAll();
	
	// seq로 게시글 정보 불러오기
	public BoardDTO selArticleBySeq(int seq);

	// 가장 최신 글 seq 불러오기
	public int getLatestSeq(String author);
	
	// 게시글 삭제
	public int delArticle(int seq);
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto);

}
