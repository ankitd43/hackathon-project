package com.hackathon.consumer.integration.helper.common_logic;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.consumer.integration.helper.encryption.AESEncryptionUtility;

public interface CommonUtility {

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}
	
	public static String getStringfromObject(Object object) throws JsonProcessingException  {
		if (object != null) {
			return new ObjectMapper().writeValueAsString(object);
		} else {
			return "{}";
		}
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim())
								|| "null".equalsIgnoreCase((String) value) || "undefined".equals((String) value)
								|| "{}".equals((String) value) || "[]".equals((String) value))
						: false));
	}
	


	public static String generateToken(String password, String username) throws UnsupportedEncodingException, GeneralSecurityException {
		return AESEncryptionUtility.encrypt(password + System.currentTimeMillis() + username);
		
	}
	
	public interface DateDiffType {
		public static final int DAYS = 0;
		public static final int HOUR = 1;
		public static final int MINUTES = 2;
		public static final int SECONDS = 3;
		public static final int MILISECONDS = 4;
	}

	
	public static Long getDateDifference(Date from, Date to, int dateDifType) {
		Calendar today = Calendar.getInstance();
		today.setTime(to);
		Calendar createdDate = Calendar.getInstance();
		createdDate.setTime(from);
		long diffInMiliseconds = today.getTime().getTime() - createdDate.getTime().getTime();
		long difference = 0;
		switch (dateDifType) {
		case DateDiffType.DAYS:
			difference = TimeUnit.DAYS.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			break;
		case DateDiffType.HOUR:
			difference = TimeUnit.HOURS.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			break;
		case DateDiffType.MINUTES:
			difference = TimeUnit.MINUTES.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			break;
		case DateDiffType.SECONDS:
			difference = TimeUnit.SECONDS.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			break;
		case DateDiffType.MILISECONDS:
			difference = diffInMiliseconds;
			break;
		default:
			break;
		}
		return difference;
	}
}