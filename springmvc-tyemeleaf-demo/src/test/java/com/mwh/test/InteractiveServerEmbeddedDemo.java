package com.mwh.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantLock;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

/**
 * Hello world!
 * 
 */
public class InteractiveServerEmbeddedDemo {

	public static void main(String[] args) throws Exception {
		ServerCacheContainer scc = new ServerCacheContainer();
		scc.init();
		System.out.println("-------------------------------获取缓存");
		Cache<String, String> cache = scc.getCache();
		System.out.println("-------------------------------准备缓存操作");
		opCache(cache);
		System.out.println("------------------------------- 销毁缓存容器");
		scc.destory();
	}

	private static void opCache(Cache<String, String> cache) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("操作说明：\n");
		sb.append("1:put操作\n");
		sb.append("2:get操作\n");
		sb.append("3:remove操作\n");
		sb.append("4:clear操作(清空缓存)\n");
		sb.append("5:exit操作（推出操作）\n");
		System.out.println(sb.toString());
		doOpCache(cache);
	}

	private static void doOpCache(Cache<String, String> cache) throws IOException {
		System.out.print("请输入操作序号：");
		String nums = inString();
		int num = checknum(nums);
		switch (num) {
		case 1:
			System.out.print("---------------请输入key值：");
			String key=inString();
			System.out.print("\n");
			System.out.print("---------------请输入value值：");
			String value=inString();
			System.out.print("\n");
			cache.put(key, value);
			System.out.println("---------------存储成功");
			break;
		case 2:
			System.out.print("---------------请输入key值：");
			key=inString();
			System.out.print("\n");
			System.out.println("----------------获取的值："+cache.get(key));
			break;
		case 3:
			System.out.print("---------------请输入key值：");
			key=inString();
			System.out.print("\n");
			System.out.println("----------------删除的值："+cache.remove(key));
			break;
		case 4:
			cache.clear();
			System.out.println("----------------清除成功");
			break;
		case 5:
			System.out.println("----------------退出成功");
			System.exit(0);
			break;
		default:
			break;
		}
		doOpCache(cache);
	}

	private static int checknum(String nums) throws IOException {
		if (nums != null) {
			boolean isNum = nums.matches("[1-5]");
			if (isNum) {
				return Integer.valueOf(nums);
			} else {
				System.err.print("请输入正确的操作序号【1-4】\n");
				System.out.print("请输入操作序号：");
				return checknum(inString());
			}
		} else {
			System.err.print("请输入正确的操作序号【1-4】\n");
			System.out.print("请输入操作序号：");
			return checknum(inString());
		}

	}

	private static String inString() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			throw e;
		}
	}
}

class ServerCacheContainer {
	private DefaultCacheManager cacheManager = null;
	private ReentrantLock lock = new ReentrantLock();
	private String name = "demo1";

	public void init() {
		if (cacheManager == null) {
			try {
				lock.lock();
				ConfigurationBuilder config = new ConfigurationBuilder();
//				config.clustering().cacheMode(CacheMode.LOCAL);
				cacheManager = new DefaultCacheManager();
				cacheManager.getCacheConfiguration(name);
				cacheManager.start();
			} finally {
				lock.unlock();
			}
		}
	}

	public <K, V> Cache<K, V> getCache() throws Exception {
		if (cacheManager != null) {
			return cacheManager.getCache(name);
		} else {
			throw new Exception("cacheManager cann't be inited");
		}
	}

	public void destory() {
		if (cacheManager != null) {
			cacheManager.stop();
			cacheManager = null;
		}
	}
}