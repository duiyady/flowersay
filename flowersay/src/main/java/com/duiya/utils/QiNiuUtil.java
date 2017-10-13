package com.duiya.utils;

import com.qiniu.util.Auth;

public class QiNiuUtil {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private static String ACCESS_KEY = Constants.AccessKey;
	private static String SECRET_KEY = Constants.SecretKey;

	// 密钥配置
	private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	/**
	 * 获取 上传的token
	 * @param type
	 *            1 表示上传图片 2表示上传文件
	 * @return
	 */
	public static String getUpToken(int type) {
		String bucketname = null;
		if (type == 1) {
			// 要上传的空间
			bucketname = Constants.picBucketName;
		} else {
			bucketname = Constants.fileBucketName;
		}
		return auth.uploadToken(bucketname);
	}

}