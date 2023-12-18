package com.dialoguespace.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class EncryptionUtils {
	
	private final HttpSession session;
	
	@Autowired
	public EncryptionUtils(HttpSession session) {
		this.session = session;
	}

	public String getSHA512(String password) {
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
	
	public String getSHA256(String password) throws NoSuchAlgorithmException{
		
			String EncodeString = "";

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] byteString = password.getBytes();

			md.update(byteString);

			byte[] digest = md.digest();
			String tmp = "";

			for( int i = 0; i < digest.length; i++ ) {
				tmp = Integer.toHexString(digest[i] & 0xFF);
			
				if( tmp.length() < 2 ) {
					tmp = "0" + tmp;
				}
				EncodeString = EncodeString + tmp;
			}

			return EncodeString;
	}
	
	// RSA 암호화 인스턴스 생성 & JS RSA 라이브러리에 넘기기
	public void genRsaInstance(Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 암호화
		//// KeyPariGenerator 인스턴스 생성(RSA 알고리즘)
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		//// Key size 지정
		generator.initialize(2048);
		//// KeyPair 인스턴스 생성
		KeyPair keyPair = generator.genKeyPair();
		//// 공개키, 개인키 생성
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		if(privateKey == null) return ;
		//// 개인키 세션에 개인키를 저장
		session.setAttribute("__rsaPrivateKey__", privateKey);
		System.out.println("========== EncryptionUtils - getRsaInstance ==========");
		System.out.println(session.getAttribute("__rsaPrivateKey__"));
		//// KeyFactory 인스턴스 생성(RSA 알고리즘)
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		//// RSAPublicKeySpec 인스턴스 생성. 공개키를 문자열로 전환하여 JavaScript RSA 라이브러리에 넘겨주기.
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		//// JavaScript RSA 라이브러리에 넘겨줄 공개키 modulus, exponent 생성
		String publicKeyModulus  = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

		model.addAttribute("publicKeyModulus", publicKeyModulus);
		model.addAttribute("publicKeyExponent", publicKeyExponent);
		
		//return map;
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
}
