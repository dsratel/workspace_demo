package com.dialoguespace.demo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ConnectionTest {public static void main(String[] args) throws Exception {
		
		String password = "Freepass123!@#";
		System.out.println(getSHA512(password));
		
	}	// main end
	
	// SHA512 암호화
	public static String getSHA512(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.reset();
			md.update(password.getBytes("utf8"));
			password = String.format("%0128x", new BigInteger(1, md.digest()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	
	// RSA 복호화
		public String decryptRsa(PrivateKey privateKey, String securedValue) throws NoSuchAlgorithmException, NoSuchPaddingException,
		InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException  {
			Cipher cipher = Cipher.getInstance("RSA");
			byte[] encryptedBytes = hexToByteArray(securedValue);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
			return decryptedValue;
		}
		
		// 16진 문자열 -> byte 배열로 변환
		public static byte[] hexToByteArray(String hex) {
			if(hex == null || hex.length() % 2 != 0) {
				return new byte[] {};
			}
			
			byte[] bytes = new byte[hex.length() / 2];
			for(int i = 0; i < hex.length(); i += 2) {
				byte value = (byte)Integer.parseInt(hex.substring(i, i+2), 16);
				bytes[(int) Math.floor(i/2)] = value;
			}
			
			return bytes;
		}


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