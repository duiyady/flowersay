package com.duiya.utils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 的工具类
 * 
 * @author Administrator
 *
 */
public class StringUtil {
	/**
	 * 一个String的工具类
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isempty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 正则匹配 验证是否是邮件格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern pattern = Pattern.compile(check);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Lnj
	 * @return
	 */
	public static  String getUuid() {
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		return temp.replaceAll("-", "");
	}

	public static String getRandomString(int length) {
		char[] array = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(array.length);
			sb.append(array[num]);
		}
		return sb.toString();
	}

	/**
	 * 判断电话号码的格式是否正确
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断一个字符串的长度是否超出 指定长度
	 * 
	 * @author 蒋渊
	 * @param str
	 * @param leng
	 * @return
	 */
	public static boolean outOfRang(String str, int len) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length > len) {
			return true;
		}
		return false;
	}
	/**
	 * 将Object转换成Long
	 * @param o
	 * @return
	 */
	public static Long objectToLong(Object o){
		String S=String.valueOf(o);
		Long L=Long.valueOf(S);
		return L;
	}
}
