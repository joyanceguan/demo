package com.joyance.demo.base.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5输出128bits/16bytes
 */
public class MD5 {

	/**
	 * 利用第三方实现md5（DigestUtils）
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String md5(String text) throws Exception {
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text);
        return encodeStr;
    }
	
	/**
	 * java原生生成md5
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String md5_2(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		String result = String.format("%032x", new BigInteger(1,digest));
		return result;
    }
	
	public static void main(String[] args) throws Exception {
		String str = "231084199012160522";
		long start = System.currentTimeMillis();
		String text = MD5.md5(str);
		System.out.println("摘要:"+text+",长度:"+text.length()+",耗时:"+(System.currentTimeMillis() - start));
		text = MD5.md5_2(str);
		System.out.println("摘要:"+text+",长度:"+text.length());
	}
}
