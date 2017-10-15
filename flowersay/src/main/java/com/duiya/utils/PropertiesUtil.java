package com.duiya.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来读取默认的配置文件
 * @author duiya
 *
 */

public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static InputStreamReader defaultsetIn;
	private static Properties defaultsetProp;
	private static InputStreamReader alidayuIn;
	private static Properties alidayuProp;

	private PropertiesUtil() {
		// 不能实例化
	}

	static {
		try {
			defaultsetProp = new Properties();
			defaultsetIn = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("defaultset.properties"), "UTF-8");
			defaultsetProp.load(defaultsetIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed to read defaultset.properties", e);
		} finally {
			if (defaultsetIn != null) {
				try {
					defaultsetIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			alidayuProp = new Properties();
			alidayuIn = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties"), "UTF-8");
			alidayuProp.load(alidayuIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed to read message.properties", e);
		} finally {
			if (alidayuIn != null) {
				try {
					alidayuIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获得默认设置的值
	 * @param key
	 * @return
	 */
	public static String getDSValue(String key) {
		return defaultsetProp.getProperty(key);
	}
	/**
	 * 获取阿里大于的配置
	 * @param key
	 * @return
	 */
	public static String getALValue(String key) {
		return alidayuProp.getProperty(key);
	}
	
}
