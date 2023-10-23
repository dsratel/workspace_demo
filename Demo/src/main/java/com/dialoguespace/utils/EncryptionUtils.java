package com.dialoguespace.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptionUtils {

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
}
