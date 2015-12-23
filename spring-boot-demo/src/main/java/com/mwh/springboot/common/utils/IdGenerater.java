package com.mwh.springboot.common.utils;

import java.util.UUID;

public class IdGenerater {

	public static String getId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
