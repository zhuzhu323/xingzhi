package com.zjf.kaw.util;

public class UrlFactory {
	/**
	 * URL工厂 整理所有的url地址
	 */

	/**
	 * 获取新闻列表的请求
	 * @param changleId
	 * @param name
	 * @return
	 */

	public static String getPopularNewsUrl(int page) {
	
		return "id=popular&page="+page;
	}
	public static String getSportsNewsUrl(int page) {
		
		return "id=sports&page="+page;
	}
	public static String getTechNewsUrl(int page) {
		
		return "id=tech&page="+page;
	}
	public static String getAutoNewsUrl(int page) {
		
		return "id=auto&page="+page;
	}


}
