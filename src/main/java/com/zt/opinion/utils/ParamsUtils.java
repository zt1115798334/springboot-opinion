package com.zt.opinion.utils;

public class ParamsUtils {
	
	public static Integer getInt(Integer num, Integer def) {
		return num != null ? num : def;
	}
	public static Integer getInt(Integer num) {
		return num != null ? num : 0;
	}
}
