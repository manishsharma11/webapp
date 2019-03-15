package com.main.sts.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class MD5PassEncryptionClass {

	static String key = "arcplexus";
	//static String key = "easycommute";

	private static byte[] sharedvector = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B,
			0x0D, 0x11 };

	/*
	 * public String encryptWithMD5(String pass) { try { MessageDigest md =
	 * MessageDigest.getInstance("MD5"); byte[] passBytes = pass.getBytes();
	 * md.reset(); byte[] digested = md.digest(passBytes); StringBuffer sb = new
	 * StringBuffer(); for (int i = 0; i < digested.length; i++) {
	 * sb.append(Integer.toHexString(0xff & digested[i])); } return
	 * sb.toString(); } catch (NoSuchAlgorithmException ex) {
	 * ex.printStackTrace(); return null; }
	 * 
	 * }
	 */

	public static String DecryptText(String EncText) {
		String RawText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		byte[] toEncryptArray = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));

			if (temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++) {
					keyArray[i] = temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"),
					new IvParameterSpec(sharedvector));
			byte[] decrypted = c.doFinal(Base64.decodeBase64(EncText));

			RawText = new String(decrypted, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RawText;
	}

	public static String EncryptText(String RawText) {
		String EncText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		byte[] toEncryptArray = null;

		try {
			toEncryptArray = RawText.getBytes("UTF-8");
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));

			if (temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++) {
					keyArray[i] = temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"),
					new IvParameterSpec(sharedvector));
			byte[] encrypted = c.doFinal(toEncryptArray);
			EncText = Base64.encodeBase64String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return EncText;
	}

	/*
	 * public static void main(String args[]) { String
	 * pass=cryptWithMD5("admin123");
	 * //System.out.println("ency password is :"+pass); }
	 */

}
