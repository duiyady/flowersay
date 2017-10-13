package com.duiya.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static MessageDigest md5Digest = null;
	// MD5 加盐
	private static final String seed = "duiya";

	static {
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
	}

	private static byte[] md5digest(String key) {
		if (key == null) {
			return null;
		}
		MessageDigest md5;
		try {
			md5 = (MessageDigest) md5Digest.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("clone of MD5 not supported", e);
		}
		md5.update(key.getBytes());
		return md5.digest();
	}

	/**
	 * 获取Md5 加密的字符串 已经 通过加盐处理
	 * 
	 * @param key
	 * @return
	 */
	public static String generateCheckString(String key) {
		if (key == null) {
			return null;
		}
		key = key + seed;
		return digest(key);
	}

	private static String digest(String key) {
		byte[] bs = md5digest(key);
		return byte2hex(bs);
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 转换�?16进制字符串，每个byte生成2个字�?
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toLowerCase();
	}

	/**
	 * 转换�?16进制字符串，每个byte生成�?个字符（会丢失信息）
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2SingleHex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.generateCheckString("123456"));
	}
}
