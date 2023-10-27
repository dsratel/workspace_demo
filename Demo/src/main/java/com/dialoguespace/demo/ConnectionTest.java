package com.dialoguespace.demo;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;

import com.dialoguespace.utils.EncryptionUtils;

public class ConnectionTest {
	public static void main(String[] args) throws Exception {
		
/*
		
		String str = "<p>"
				+ "<img style=\"width: 640px;\" src=\"/resources/summerNoteImg/1dd21104-aa20-4ed0-9889-dc56dcfe8a56.jpg\">"
				+ "<img style=\"width: 427px;\" src=\"/resources/summerNoteImg/292a0437-9eee-45a9-b18b-74f6738eab1e.jpg\">"
				+ "<img style=\"width: 640px;\" src=\"/resources/summerNoteImg/5972aa2a-0ccf-46c9-a203-9c6dc42f3f05.jpg\"><br></p>";
		// <img > 가 들어간 개수
		
		String rs[] = str.split(">");
		for(int i = 0; i < rs.length; i++) {
			System.out.println(rs[i]);
		}
		
		*/
		
//		String path = "D:\\workspace_demo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Demo\\resources/summerNoteImg/07092b7e-6877-4f5a-a81e-8b7ed2ccc11d";
//		File f = new File(path);
//		f.delete();
		
		for(int i = 0; i < 5; i++) {
			
			for(int j = 0; j < 5; j++) {
				
				if(j == 2) continue;
				
				System.out.println("i = " + i + " / j = " + j);
			}
		}
		
		

	}	// main end
	
	
	// 임시 폴더 생성 
	public static void createTempFolder(String tempPath) {
		
		File folder = new File(tempPath);
		try {
			if(folder.exists()) {
				System.out.println("demo 임시 폴더가 존재합니다. 하위 파일을 삭제합니다.");
				delFiles(folder);
			} else {
				folder.mkdir();
				System.out.println("demo 임시 폴더 생성");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 하위 파일 삭제
	public static void delFiles(File folder) {
		File[] folder_list = folder.listFiles();
		for(File file : folder_list) {
			file.delete();
			System.out.println(file + " 삭제 완료");
		}
	}
	
	// fileno로 특정 파일 삭제(물리적)
	public static void delFilePhs(String filePath) throws Exception {
		File f = new File(filePath);
		f.delete();
		System.out.println(filePath + " 삭제 완료");
	}

}
