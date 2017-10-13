package com.duiya.utils;

/**
 * 七牛云中的配置项的常量 其实也可以通过PropertiesUitl获取 七牛云配置值 但是 那种方法需要写 配置变量名 key 写的越多 出错的几率就越大
 * 写在这里 直接调用 出错的几率小
 * 
 * @author joe蒋渊
 */

public class Constants {

	public static String AccessKey;
	public static String SecretKey;
	public static String picBucketName;
	public static String fileBucketName;

	// 加载类的时候初始化参数
	static {
		AccessKey = PropertiesUtil.getValue("AccessKey");
		SecretKey = PropertiesUtil.getValue("SecretKey");
		picBucketName = PropertiesUtil.getValue("picBucketName");
		fileBucketName = PropertiesUtil.getValue("fileBucketName");
	}

}
