package com.joyance.demo.base.encrypt;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
	
	private PrivateKey sk;
	
	private PublicKey pk;
	
	//生成公钥/私钥对
	public RSA() throws NoSuchAlgorithmException{
	  KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
	  //生成密钥长度1024位
	  kpGen.initialize(1024);
	  KeyPair kp = kpGen.generateKeyPair();
	  this.sk = kp.getPrivate();
	  this.pk = kp.getPublic();
	}
	
	//从已保存的字节中（例如读取文件）恢复公钥/私钥
	public RSA(byte[] pk,byte[] sk) throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pk);
		this.pk = kf.generatePublic(pkSpec);
		PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(sk);
		this.sk = kf.generatePrivate(skSpec);
	}
	
	//把私钥导出为字节
	public byte[] getPrivateKey(){
		return this.sk.getEncoded();
	}
	
	//把公钥导出为字节
	public byte[] getPublicKey(){
		return this.pk.getEncoded();
	}
	
	//用公钥加密
	public byte[] encrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, this.pk);
		return cipher.doFinal(message);
	}
	
	//用私钥解密
	public byte[] decrypt(byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, this.sk);
		return cipher.doFinal(input);
	}
	
	/**
	 * 签名算法包括
	 * MD5withRSA
	 * SHA1withRSA
	 * SHA256withRSA
	 */
	//数字签名
	public byte[] sign(byte[] message) throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(sk);
		signature.update(message);
		return signature.sign();
	}
	
	//验证签名
	public boolean verify(byte[] message,byte[] sign) throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(pk);
		signature.update(message);
		return signature.verify(sign);
	}
	
	public static void main(String[] args) throws Exception {
		byte[] plain = "hello world".getBytes();
		RSA rsa = new RSA();
		//加密
		byte[] encrypted = rsa.encrypt(plain);
		System.out.println("encrypted:"+Base64.getEncoder().encodeToString(encrypted));
		//解密
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println(new String(decrypted,"UTF-8"));
		//保存公钥/私钥
		byte[] pk = rsa.getPublicKey();
		byte[] sk = rsa.getPrivateKey();
		System.out.println("pk:"+Base64.getEncoder().encodeToString(pk));
		System.out.println("sk:"+Base64.getEncoder().encodeToString(sk));
		
		//重新恢复公钥/私钥
		RSA rsa2 = new RSA(pk,sk);
		//加密
		byte[] encrypted2 = rsa2.encrypt(plain);
		System.out.println("encrypted:"+Base64.getEncoder().encodeToString(encrypted2));
		//解密
		byte[] decrypted2 = rsa2.decrypt(encrypted2);
		System.out.println(new String(decrypted2,"UTF-8"));
		
		//签名
		rsa = new RSA();
		byte[] sign = rsa.sign(plain);
		System.out.println("sign:"+Base64.getEncoder().encodeToString(sign));
		//验证签名
		boolean verify = rsa.verify(plain, sign);
		System.out.println("verify:"+verify);
	}
}
