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

public class ConnectionTest {
	private static char map1[];
	private static byte map2[];
	
	public static void main(String[] args) throws Exception {
		

		String pw = "9c0fabac9beb630646bddea285ee3360b4dbc4826db332fde59597d9c7ccc77e352f68ed697c4c1bfbc839f522c95aed3e4762bcd57033dd298174702e5a4700625c5fa2ab181aeaf9b48f844c23e64e71a46d48e3f677eeab94b0fe739d8d6d0a569aeb7d5c1e224cad08c297296bf843fc89dbc327b48d96e6d112bd4ce83b66dceb9bf35280a2db7ae498fe193e8810ea136e64175baa2519de8e88a44aaa25cac1c9ec74c89f3d1eabc526c0b260582ca6846ab0499904a12ea599a8ee98a49f6e617e2adef016fdc1d94fd43c760c11d3b16bcfc578848b9a6c49a2d31b521f375d4e8738f297b9fe4258cb5b2c229efc3d708a4414484a019eaf2ef5c2";
		

		
		

	}	// main end
	
	
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
