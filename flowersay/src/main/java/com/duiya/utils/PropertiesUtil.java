package com.duiya.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此类是是用来 读取properties 文件
 * @author joe蒋渊
 *
 */

public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static InputStreamReader in;
	private static Properties prop;

	private PropertiesUtil() {
		// 不能实例化
	}

	static {
		try {
			// 读取七牛云的配置文件
			prop = new Properties();
			in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("qiniuyun.properties"), "UTF-8");
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("读取配置文件qiniuyun.properties出错", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取 key 的值
	 * @param key
	 * @return
	 */

	public static String getValue(String key) {
		return prop.getProperty(key);
	}

}
