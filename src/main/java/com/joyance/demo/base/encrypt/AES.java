package com.joyance.demo.base.encrypt;


import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 密钥长度:128/192/256 
 * 工作模式:ECB/CBC/PCBC/CTR....
 * 填充模式:NoPadding/PKCS5Padding/PKCS7Padding
 */
public class AES {
	
	/**
	 * AES/ECB/PKCS5Padding模式加密
	 * @param key
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String keyStr,String message) throws Exception{
		byte[] input = message.getBytes("UTF-8");
		byte[] key = keyStr.getBytes("UTF-8");
		//加密开始
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] result = cipher.doFinal(input);
		//加密结束
		return Base64.getEncoder().encodeToString(result);
	}
	
	/**
	 * AES/CBC/PKCS5Padding模式加密
	 * @param key
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String encrypt2(String keyStr,String message) throws Exception{
		byte[] input = message.getBytes("UTF-8");
		byte[] key = keyStr.getBytes("UTF-8");
		//加密开始
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
		//CBC模式 begin
		SecureRandom sr = SecureRandom.getInstanceStrong();
		byte[] iv = sr.generateSeed(16);
		IvParameterSpec ivps = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivps);
		//CBC模式 end
		byte[] result = cipher.doFinal(input);
		//加密结束
		return Base64.getEncoder().encodeToString(result);
	}
	
	/**
	 * AES/ECB/PKCS5Padding模式解密
	 * @param key
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String keyStr,String message) throws Exception{
		byte[] input = Base64.getDecoder().decode(message);
		byte[] key = keyStr.getBytes("UTF-8");
		//解密开始
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] result = cipher.doFinal(input);
		//解密结束
		return new String(result,"UTF-8");
	}
	
	
	public static void main(String[] args) throws Exception {
		String message = "关悦";
		//128位密钥 = 16 bytes key
		String key = "1234567890abcdef";
		String encrypt = AES.encrypt(key, message);
		System.out.println("AES/ECB/PKCS5Padding方式，128位密钥加密生成："+encrypt+",长度："+encrypt.length());
	    
		String decrypt = AES.decrypt(key, encrypt);
		System.out.println("AES/ECB/PKCS5Padding方式，128位密钥解密生成："+decrypt+",长度："+encrypt.length());
		
		encrypt = AES.encrypt2(key, message);
		System.out.println("AES/CBC/PKCS5Padding方式，128位密钥加密生成："+encrypt+",长度："+encrypt.length());
		
	}
	
}
