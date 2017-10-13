package com.duiya.utils;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
	/**
	 * @author jipeng
	 * @return
	 * 
	 * 		构造返回json
	 * 
	 */
	public static JSONObject constructResponse(int code, String msg, Object data) {
		JSONObject jo = new JSONObject();
		jo.put("code", code);
		jo.put("msg", msg);
		jo.put("data", data);
		return jo;
	}

	/**
	 * 参数错误返回json
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject constructArgErrorResponse(String msg) {
		JSONObject jo = new JSONObject();
		jo.put("code", EnumUtil.ARG_ERROR);
		jo.put("msg", msg);
		jo.put("data", null);
		return jo;
	}

	/**
	 * 数据库错误返回json
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject constructDbErrorResponse(String msg) {
		JSONObject jo = new JSONObject();
		jo.put("code", EnumUtil.DB_ERROR);
		jo.put("msg", msg);
		jo.put("data", null);
		return jo;
	}

	/**
	 * 未知错误 返回json
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject constructUnknownErrorResponse(String msg) {
		JSONObject jo = new JSONObject();
		jo.put("code", EnumUtil.UNKNOW_ERROR);
		jo.put("msg", msg);
		jo.put("data", null);
		return jo;
	}

	/**
	 * ok 返回
	 * 
	 * @param msg
	 * @param data
	 * @return
	 */
	public static JSONObject constructOKResponse(String msg, Object data) {
		JSONObject jo = new JSONObject();
		jo.put("code", EnumUtil.OK);
		jo.put("msg", msg);
		jo.put("data", data);
		return jo;
	}
}
