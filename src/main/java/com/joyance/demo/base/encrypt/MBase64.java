package com.joyance.demo.base.encrypt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class MBase64 {

	public static String encode(String str) throws UnsupportedEncodingException{
		return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
	}
	
	
	public static String decode(String str) throws UnsupportedEncodingException{
		return new String(Base64.getDecoder().decode(str));
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String encode = MBase64.encode("joy");
		String decode = MBase64.decode(encode);
		System.out.println("encode:"+encode+",decode:"+decode);
	}
}
