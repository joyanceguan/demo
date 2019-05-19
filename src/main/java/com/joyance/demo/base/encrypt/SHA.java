package com.joyance.demo.base.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * SHA-1输出160bits/20bytes
 * SHA-256输出256bits/32bytes
 * SHA-512输出512bits/64bytes
 */
public class SHA {

	public static String SHA1(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		String result = String.format("%040x", new BigInteger(1,digest));
		return result;
    }
	
	public static String SHA256(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		String result = String.format("%064x", new BigInteger(1,digest));
		return result;
    }
	
	public static String SHA512(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		String result = String.format("%128x", new BigInteger(1,digest));
		return result;
    }
	
	public static void main(String[] args) throws Exception {
		String text = "Java摘要算法测试";
		String str = SHA.SHA1(text);
		System.out.println("摘要SHA-1:"+str+",长度:"+str.length());
		str = SHA.SHA256(text);
		System.out.println("摘要SHA-256:"+str+",长度:"+str.length());
		str = SHA.SHA512(text);
		System.out.println("摘要SHA-512:"+str+",长度:"+str.length());
	}
}
