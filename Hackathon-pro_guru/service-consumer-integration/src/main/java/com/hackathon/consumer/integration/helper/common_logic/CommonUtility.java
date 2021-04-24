package com.hackathon.consumer.integration.helper.common_logic;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Collection;

import com.hackathon.consumer.integration.helper.encryption.AESEncryptionUtility;

public interface CommonUtility {
	
	public static String JOB_STATUS_NEW = "NEW";
	public static String JOB_STATUS_DONE = "Completed";

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim())
								|| "null".equalsIgnoreCase((String) value) || "undefined".equals((String) value)
								|| "{}".equals((String) value) || "[]".equals((String) value))
						: false));
	}
	


	public static String generateToken(Long applicationId, String password) throws UnsupportedEncodingException, GeneralSecurityException {
		return AESEncryptionUtility.encrypt(applicationId + System.currentTimeMillis() + password);
		
	}
}