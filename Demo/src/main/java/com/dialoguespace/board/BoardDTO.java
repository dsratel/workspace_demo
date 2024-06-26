package com.dialoguespace.board;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private int seq;
	private String category;
	private String author;
	private String title;
	private String content;
	private String attachfile;
	private int viewcnt;
	private int commentcnt;
	private int pid;
	private Date regdate;
	private Date modifydate;
	private String etc;
}
