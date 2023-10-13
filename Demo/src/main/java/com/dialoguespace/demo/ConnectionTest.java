package com.dialoguespace.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	public static void main(String[] args) {
		
		try {
			// MySQL DB용 드라이브로드
			Class.forName("com.mysql.jdbc.Driver");
			// DB 연결
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo_schema", "root", "1234");
			System.out.println("mysql db연결 성공");
			
			// DB연결해제
			conn.close();
			System.out.println("mysql DB 연결 해제 성공");
			
			
		} catch(ClassNotFoundException error) {
			System.out.println(error);
			System.out.println("mysql driver 미설치 또는 드라이버 이름 오류");
		} catch(SQLException error) {
			System.out.println("DB접속오류");
		}
	}
}