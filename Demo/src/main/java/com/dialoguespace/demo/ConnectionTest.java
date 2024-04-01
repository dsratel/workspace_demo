package com.dialoguespace.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionTest {
	public static void main(String[] args) throws Exception {
	
		String s = "PAYPALISHIRING";
		int numRows = 5;
		/*
		StringBuilder sb = new StringBuilder();
		// String 글자 수
		int len = s.length();
		
		for(int i = 0; i < numRows; i++) {
			int index = i;
						
			if(i > 0 && i < numRows-1) {
				int round = 0;
				while(index < len) {
					sb.append(s.charAt(index));
					round++;
					if(round%2 > 0) {
						index += 2*(numRows-i-1);						
					} else {
						index += 2*i;						
					}
				}
					 
			} else if((i == 0 || i == numRows-1) && numRows > 1) {
				while(index < len) {
					sb.append(s.charAt(index));
					index += 2*(numRows-1);
				}
			} else {
				while(index < len) {
					sb.append(s.charAt(index));
					index++;
				}
			}
		}

		
		System.out.println(sb.toString());
		*/
		
		
		////////
		
	     int len = s.length();

	 

	        int index = 0;
	        int diff = 2 * numRows - 2;
	        char[] arr = new char[len];

	        for (int row = 0; row < numRows; row++) {
	            int diagDiff = diff - 2 * row;
	            boolean isDiagExist = diagDiff < diff && diagDiff > 0;

	            for (int col = row; col < len; col += diff) {
	                arr[index] = s.charAt(col);
	                index++;

	                if (isDiagExist && col + diagDiff < len) {
	                    arr[index] = s.charAt(col + diagDiff);
	                    index++;
	                }
	            }
	        }


		
		
		
		
	}	// main end

/*
	@Bean
	public FeignFormatterRegistrar localDateFormatter () {
	    return registry -> {
	        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
	        registrar.setUseIsoFormat(true);
	        registrar.registerFormatters(registry);
	    };
	}
	
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
	*/
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * 
 * */
 



class Test {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int l = l1.val;
    	
    	int lCnt = 0;
    	
		ListNode r1 = new ListNode();
		
//		List<ListNode> list = new ArrayList<ListNode> (); 
		
		List<Integer> list = new ArrayList<Integer> ();
		
    	while( l1.next != null ) {
     		list.add(l1.val);
    	}

    	
    	for( int i = list.size(); i == 0; i-- ) {
    		list.add(l1.val);
    	}
    	
        return new ListNode();
    }
}

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

