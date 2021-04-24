package com.hackathon.consumer.integration.helper.encryption;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionUtility {
	private static final String ALGORITHM = "AES";
	private static final String CHAR_ENCODING = "UTF-8";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final byte[] IV = "09-05-2018 11:50".getBytes();
	private static SecretKeySpec skeySpec;
	private static IvParameterSpec ivspec;
	private static Cipher cipher;

	static {
		try {
			skeySpec = new SecretKeySpec(generateKey(), ALGORITHM);
			ivspec = new IvParameterSpec(IV);
			cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (Exception e) {
			System.out.println("Error Msg-=======>" + e.getMessage());
		}
	}

	public static String encrypt(String plainText) throws GeneralSecurityException, UnsupportedEncodingException  {
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
		byte[] plainTextbytes = plainText.getBytes(CHAR_ENCODING);
		byte[] encryptedBytes = cipher.doFinal(plainTextbytes);
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decrypt(String encryptedText) throws GeneralSecurityException, UnsupportedEncodingException {
		byte[] cipheredBytes = Base64.getDecoder().decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
		byte[] decryptedBytes = cipher.doFinal(cipheredBytes);
		return new String(decryptedBytes, CHAR_ENCODING);
	}

	// Need to check with .NET API as below method added as per SBI Guidlines
	private static byte[] generateKey() {
		try {
			String hex = "56f1ac95f77n22ebc66e2359c13ea6611ebd5e2bd8fbe50e5b3ac2977a772302";			
			byte[] bytes = new byte[hex.length() / 2];
			for (int i = 0; i < hex.length() / 2; i++) {
				bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
			}
			return bytes;
		} catch (Exception e) {

		}
		return null;
	}
	
}