package com.dialoguespace.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("memberDto")
public class MemberDTO {
	private String m_id;
	private String m_pw;
	private String m_nickname;
	private String m_name;
	private String m_address;
	private String m_phone;
	private Date m_regdate;
	private boolean masteryn;
	
}
