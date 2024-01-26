package com.dialoguespace.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConnectionTest {public static void main(String[] args) throws Exception {
		
//		String password = "Freepass123!@#";
//		System.out.println(getSHA512(password));
		String key = "20240115";
		String str = key.substring(0, 4) + "-" + key.substring(4, 6);
		if(key.length() > 6) str += "-" + key.substring(6, 8);
		
		//System.out.println(str);

		
		//System.out.println(list2);
		
		//System.out.println((int)Math.pow(10, 0));
		
		ListNode node1 = new ListNode(0);//new ListNode(4, new ListNode(3, new ListNode(9, null)));
		ListNode node2 = new ListNode(0);//new ListNode(3, new ListNode(4, new ListNode(8, null)));
		
		ListNode node = null;
		
		// 첫 번째 리스트노드
		node = node1;
		
		int p = 1;
		int num1 = node.val;
		while(node.next != null) {
			node = node.next;			
			num1 += node.val*(int)Math.pow(10,p);
			p++;
		}
		
		System.out.println(num1);
		
		// 두 번째 리스트노드
		node = null;
		node = node2;
		p = 1;
		int num2 = node.val;
		while(node.next != null) {
			node = node.next;
			num2 += node.val*(int)Math.pow(10, p);
			p++;
		}
		
		System.out.println(num2);
		
		// 합계 리스트노드
		int num3 = num1 + num2;
		/*
		int size = 1;
		p = 1;
		ListNode head = new ListNode(num3%(int)Math.pow(10,p));
		ListNode tail = head;
		
		num3 -= num3%(int)Math.pow(10,p);	// 첫 째 자리인 경우 여기에서 끝
		System.out.println("십의 자리부터 합계 리스트노드 : " + num3);
		
		while(num3 > 0) {
			size++;
			ListNode last = tail;

			p++;
			System.out.println("p : " + p + " / num3 : " + num3);
			int val = num3%(int)Math.pow(10,p)/(int)Math.pow(10,p-1);
			tail = new ListNode(val);
			//System.out.println("next_node 전 : " + next_node);
			System.out.println("num3 : " + num3 + " / 뺄 수 : " + num3%(int)Math.pow(10,p));
			//next_node = next_node.next;
			//System.out.println("next_node 후 : " + next_node);
			num3 -= num3%(int)Math.pow(10,p);
			//node = node.next;
			
			last.next = tail;
		}
		

//		ListNode aaa = head.next;
//		while(aaa.next != null) {
//			System.out.println("값 출력 : " + aaa.val);
//			aaa = aaa.next;
//			
//		}
		System.out.println("head val");
		System.out.println(head.val);
		System.out.println(head.next.val);
		System.out.println(head.next.next.val);
		System.out.println(head.next.next.next.val);
		System.out.println("==========");
		Object[] arr = new Object[size];
		int index = 0;
		ListNode n = head;
		while(n != null) {
			arr[index] = n.val;
			index++;
			n = n.next;			
		}
		
		System.out.println(Arrays.toString(arr));
		*/
		System.out.println("==========");
//		List list = new ArrayList();
//		int i = 10;
//		while(num3 > 0) {
//			list.add(num3%i/(i/10));
//			num3 -= num3%i;
//			i *= 10;
//		}
//		System.out.println(list.toString());

		int i = 10;
		int size = 0;
		ListNode head = null;
		ListNode tail = null;
		while(num3 >= 0) {
			ListNode last = tail;
			ListNode newNode = new ListNode(num3%i/(i/10));
			System.out.println("newNode.val : " + newNode.val);
			tail = newNode;
			size++;
			if(last == null) {
				// 마지막이 null 이라면 처음 추가하는 데이터 이므로 head도 똑같은 노드를 가리킨다.
				head = tail;
				System.out.println("head = tail");
			} else {
				last.next = newNode;
			}
			num3 -= num3%i;
			i *= 10;
		}
		
		Object[] arr = new Object[size];
		int index = 0;
		ListNode n = head;
		while(n != null) {
			arr[index] = n.val;
			n = n.next;
			index++;
		}
		
		System.out.println(Arrays.toString(arr));
		
		
		
						
		
		
		
		
		
		

		
//		while(num3 > 0) {
//			ListNode next = new ListNode();
//			next = n.next;				
//			for(int i = 1; i < p; p++) {
//				System.out.println("i : " + i + " / p : " + p);
//				next = next.next;
//			}
//			p++;
//			System.out.println("p : " + p + " / num3 : " + num3);
//			int val = num3%(int)Math.pow(10,p)/(int)Math.pow(10,p-1);
//			next = new ListNode(val);
//			//System.out.println("next_node 전 : " + next_node);
//			System.out.println("num3 : " + num3 + " / 뺄 수 : " + num3%(int)Math.pow(10,p));
//			//next_node = next_node.next;
//			//System.out.println("next_node 후 : " + next_node);
//			num3 -= num3%(int)Math.pow(10,p);
//			//node = node.next;
//		}
//		
//		
//		System.out.println("==========");		
//		System.out.println(n.next);
//		System.out.println(n.next.next);
		
		
		//System.out.println(1822%100/10);
		
		
		
		//ListNode node3 = new ListNode(5, new ListNode(0, new ListNode(8, new ListNode(1, null))));
		
		
		// n1=5, n2=0, n3=8, n4=1
		// 
		
		
//		int a = 1824356243;
//		int l = 0;
//		while(a > 0) {
//			l++;
//			a -= a%(int)Math.pow(10, l);
//		}
//		System.out.println(l);
//		
//		for(int i = 0; i < l; i++) {
//			ListNode node
//		}
//		
		
		
		
		
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

