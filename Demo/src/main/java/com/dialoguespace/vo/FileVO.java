package com.dialoguespace.vo;

import java.util.Date;

import lombok.Data;

@Data
public class FileVO {
	private int seq;				// 첨부파일 번호(PK)
	private String fileParent;		// 부모 게시글의 PK
	private String category;		// 부모글의 분류(Member, Board 등)
	private String orgName;			// 사용자가 올린 원래 파일명
	private String sysName;			// DB에 저장되는 파일명
	private long fileSize;			// 파일 크기
	private String fancySize;		// 1KB, 1MB
	private String contentType;		// 컨텐츠 타입
	private String filePath;		// 저장 경로
	private int downHit;			// 다운로드 횟수
	private String delYn;			// 삭제여부
	private Date regDate;			// 등록일
}
