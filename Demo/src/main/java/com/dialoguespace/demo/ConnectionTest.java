package com.dialoguespace.demo;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;

import com.dialoguespace.utils.EncryptionUtils;

public class ConnectionTest {
	public static void main(String[] args) throws Exception {
		
//		try {
//			// MySQL DB용 드라이브로드
//			Class.forName("com.mysql.jdbc.Driver");
//			// DB 연결
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo_schema", "root", "1234");
//			System.out.println("mysql db연결 성공");
//			
//			// DB연결해제
//			conn.close();
//			System.out.println("mysql DB 연결 해제 성공");
//			
//			
//		} catch(ClassNotFoundException error) {
//			System.out.println(error);
//			System.out.println("mysql driver 미설치 또는 드라이버 이름 오류");
//		} catch(SQLException error) {
//			System.out.println("DB접속오류");
//		}
		
//		String tempPath = "D:\\demoTemp\\";
//		//createTempFolder(tempPath);
//		
//		File testFolder = new File("D:\\workspace_demo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Demo\\resources\\testFoler");
//		
//		for(File f : testFolder.listFiles()) {
//			System.out.println(f);			
//		}
		
		
		//delFilePhs("D:\\workspace_demo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Demo\\resources/testFoler/31973541-2080-4337-9ef2-ef0e12039e43");
		
//		String str = "D:\\workspace_demo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Demo\\resources/testFoler/31973541-2080-4337-9ef2-ef0e12039e43";
//		String rs1 = str.substring(str.lastIndexOf("resources"));
//		System.out.println(rs1);
		
		
		EncryptionUtils encUtils = new EncryptionUtils();
		String password = "Abcde02!@#";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			md.update(password.getBytes("utf8"));
			password = String.format("%0128x", new BigInteger(1, md.digest()));
			System.out.println("객체 생성");
		} catch(Exception e) {
			e.printStackTrace();	
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
