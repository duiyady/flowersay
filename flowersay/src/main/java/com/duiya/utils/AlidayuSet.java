package com.duiya.utils;

public class AlidayuSet {
	public static String accessKeyId;
	public static String accessKeySecret;
	/**
	 * 短信签名
	 */
	public static String signName;
	/**
	 * 商家处理了
	 */
	public static String TemplateCode1;
	/**
	 * 商家发货了
	 */
	public static String TemplateCode2;
	/**
	 * 有人下单了
	 */
	public static String TemplateCode3;
	/**
	 * 交易完成
	 */
	public static String TemplateCode4;
	/**
	 * 交易完成了
	 */
	public static String TemplateCode5;
	/**
	 * 注册验证
	 */
	public static String TemplateCode6;
	/**
	 * 找回密码
	 */
	public static String TemplateCode7;
	/**
	 * 退单通知
	 */
	public static String TemplateCode8;
	static {
		accessKeyId = PropertiesUtil.getALValue("accessKeyId");
		accessKeySecret = PropertiesUtil.getALValue("accessKeySecret");
		signName = PropertiesUtil.getALValue("signName");
		TemplateCode1 = PropertiesUtil.getALValue("TemplateCode1");
		TemplateCode2 = PropertiesUtil.getALValue("TemplateCode2");
		TemplateCode3 = PropertiesUtil.getALValue("TemplateCode3");
		TemplateCode4 = PropertiesUtil.getALValue("TemplateCode4");
		TemplateCode5 = PropertiesUtil.getALValue("TemplateCode5");
		TemplateCode6 = PropertiesUtil.getALValue("TemplateCode6");
		TemplateCode7 = PropertiesUtil.getALValue("TemplateCode7");
		TemplateCode8 = PropertiesUtil.getALValue("TemplateCode8");
	}
	
}
