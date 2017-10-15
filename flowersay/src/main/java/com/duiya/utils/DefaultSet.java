package com.duiya.utils;

/**
 * 读取图片的上传信息
 * @author duiya
 *
 */
public class DefaultSet {
	public static String DEFAULT_PICTURE;
	public static String DEFAULT_USERDIR;
	public static String DEFAULT_FLOWERDIR;
	static {
		DEFAULT_PICTURE = PropertiesUtil.getDSValue("DEFAULT_PICTURE");
		DEFAULT_USERDIR = PropertiesUtil.getDSValue("DEFAULT_USERDIR");
		DEFAULT_FLOWERDIR = PropertiesUtil.getDSValue("DEFAULT_FLOWERDIR");
	}
}
