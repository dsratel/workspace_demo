package com.dialoguespace.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
	private int p_sn;
	private String p_category;
	private String p_name;
	private int p_price;
	private boolean p_attachfile;
	private Date p_regdate;
	
}
