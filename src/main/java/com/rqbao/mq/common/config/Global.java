package com.rqbao.mq.common.config;

import com.google.common.collect.Maps;
import com.rqbao.mq.util.PropertiesLoader;

import java.util.Map;

/**
 * 全局配置类
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){

			if (value == null) {
				value = propertiesLoader.getProperty(key);
			}
			map.put(key, value);
		}
		return value;
	}
	public static Map<String, String> getMap() {
		return map;
	}

	public static void setMap(Map<String, String> map) {
		if (map == null) {
			Global.map = Maps.newHashMap();
		} else {
			Global.map = map;
		}
	}
	
	
}
