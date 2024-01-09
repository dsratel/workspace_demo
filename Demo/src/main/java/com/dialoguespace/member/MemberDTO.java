package com.dialoguespace.member;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("memberDto")
public class MemberDTO {
	private int seq;
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String address;
	private String email; 
	private String phone;
	private int status;		// 1: 활동 / 2: 탈퇴 / 3 : 구글 계정 / 9: 휴면
	private String socialAccount;
	private Date regdate;
	private Date modifydate;
	private char masteryn;
	private int fileno;
}
