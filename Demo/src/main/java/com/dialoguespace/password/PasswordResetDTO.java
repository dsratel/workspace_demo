package com.dialoguespace.password;

import java.util.Date;

import lombok.Data;

@Data
public class PasswordResetDTO {
	private int seq;
	private String url;
	private String id;
	private String email;
	private String expired;
	private Date regdate;
}
