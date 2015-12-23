package com.mwh.springboot.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;


/*
 * Copyright (c) 2010 CCX(China) Co.,Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * CCX(China) Co.,Ltd. ("Confidential Information").
 * It may not be copied or reproduced in any manner without the express 
 * written permission of CCX(China) Co.,Ltd.
 *
 * @author LiXiaoChuan
 * Date: 2011-10-18 上午10:24:00
 */

public class PropertiesUtil {
	private static Map<String, String> configMap = new HashMap<String, String>();
	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);
	public static String configFilePath = "config.properties";
	
	public static void main(String[] args) {
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据指定属性名，获取属性值
	 * @param constName
	 * @return
	 */
	public static String getConstValue(String constName) {
		if(configMap.isEmpty()){
			try {
				loadProperties();
			} catch (Exception e) {
				logger.error("load properties from " + configFilePath + " fail!", e);
			}
		}
		return configMap.get(constName);
	}
	
	/**
	 * 根据指定属性名，获取属性值
	 * @param constName
	 * @return
	 */
	public static Integer getIntegerConstValue(String constName) {
		if(configMap.isEmpty()){
			try {
				loadProperties();
			} catch (Exception e) {
				logger.error("load properties from " + configFilePath + " fail!", e);
			}
		}
		return Integer.valueOf(configMap.get(constName));
	}
	
	/**
	 * 加载配置文件
	 * @throws Exception
	 */
	public static void loadProperties() throws Exception{
		InputStream in = null;
		Properties properties = null;
		try {
			properties = new Properties();
			in = PropertiesUtil.class.getResourceAsStream("/" + configFilePath);
			if(in == null) {
				File f = new File(configFilePath);
				in = new FileInputStream(f);
			}
			properties.load(in);
			Set<Entry<Object,Object>> set = properties.entrySet();
			for (Entry<Object,Object> entry : set) {
				configMap.put(entry.getKey() + "", entry.getValue().toString().trim() + "");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
	
	/**
	 * 加载配置至map中
	 * @return
	 */
	public static Map<String, String> getConfigMap(){
		if(configMap.isEmpty()){
			try {
				loadProperties();
			} catch (Exception e) {
				//log.equals(e);
			}
		}
		return configMap;
	}
	
}
