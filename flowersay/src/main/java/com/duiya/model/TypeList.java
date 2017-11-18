package com.duiya.model;

import java.util.List;
import java.util.Map;

public class TypeList {
	private static List<Map<String,Object>> typeo;//分类
	private static List<Map<String,Object>> typet;//用途
	/**
	 * @return the typeo
	 */
	public static List<Map<String, Object>> getTypeo() {
		return typeo;
	}
	/**
	 * @param typeo the typeo to set
	 */
	public static void setTypeo(List<Map<String, Object>> typeo) {
		TypeList.typeo = typeo;
	}
	/**
	 * @return the typet
	 */
	public static List<Map<String, Object>> getTypet() {
		return typet;
	}
	/**
	 * @param typet the typet to set
	 */
	public static void setTypet(List<Map<String, Object>> typet) {
		TypeList.typet = typet;
	}
	
	

}
