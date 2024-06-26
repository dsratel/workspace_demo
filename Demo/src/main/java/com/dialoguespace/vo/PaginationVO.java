package com.dialoguespace.vo;

import lombok.Data;

@Data
public class PaginationVO {
		
	// 한 페이지에 게시글 수
	private int pageSize;
	
	// 한 블록 당 페이지 개수(한 화면에 몇 개의 페이지를 보여주는지)
	private int rangeSize;
	
	// 현재 페이지
	private int curPage;
	
	// 현재 블록(ex.12페이지를 5개씩 잘라서 3개의 블록을 가지고 있다고 가정. 현재 페이지가 7페이지면 현재 블록은 2다.)
	private int curRange;
	
	// 총 row 수
	private int listCnt;
	
	// 총 페이지 수
	private int pageCnt;
	
	// 총 블록 수
	private int rangeCnt;
	
	// 시작 페이지(한 블록의 시작 페이지)
	private int startPage;
	
	// 끝 페이지(한 블록의 끝 페이지)
	private int endPage;
	
	// 시작 인덱스(???)
	private int startIndex;
	
	// 이전 페이지
	private int prevPage;
	
	// 다음 페이지
	private int nextPage;
	
	// 기본생성자
	public PaginationVO() {}
	
	// listCnt 매개변수 명시적 생성자
	public PaginationVO(int listCnt, int curPage, int pageSize) {
		
		// 기본 페이징 설정
		this.rangeSize = 5;
		
		// 사용자 정의 설정
		this.pageSize	= pageSize;
		this.curPage	= curPage;
		this.listCnt	= listCnt;
		
		this.pageCnt	= (listCnt-1)/pageSize +1;
		this.rangeCnt	= (pageCnt-1)/rangeSize +1;
		this.startPage	= ((curPage-1)/rangeSize)*rangeSize +1;
		this.endPage	= startPage + rangeSize -1;
		this.curRange	= (startPage-1)/rangeSize +1;
		
		// 마지막 페이지가 총 페이지 수보다 큰 경우 마지막 페이지를 총 페이지 개수에 맞추기
		if(endPage > pageCnt) endPage = pageCnt;
		if(curPage > pageCnt) this.curPage = pageCnt;
		
		this.startIndex	= (this.curPage-1)*pageSize;
	}
}
