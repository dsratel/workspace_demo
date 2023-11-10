package com.dialoguespace.demo;

import lombok.Data;

@Data
public class Tester {

	private String str;
	
	public Tester() {}
	
	public Tester(String str) {
		this.str = str;
	}
	
}
