package com.mwh.springmvc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Base64Util {
	private static Logger log = Logger.getLogger(Base64Util.class);

	public static String encode(String data, String encode) {
		byte[] datas = null;
		try {
			datas = data.getBytes(encode);
		} catch (UnsupportedEncodingException e) {
		}
		if ((datas == null) || (datas.length == 0))
			return null;
		return new BASE64Encoder().encode(datas);
	}

	public static String encode(byte[] datas) {
		if ((datas == null) || (datas.length == 0))
			return null;
		return new BASE64Encoder().encode(datas);
	}

	public static byte[] decode(String s) {
		if ((s == null) || (s.length() == 0))
			return null;
		try {
			return new BASE64Decoder().decodeBuffer(s);
		} catch (IOException e) {
			log.error("an error occurred", e);
		}
		return null;
	}

	public static String decode(String s, String encode) {
		if ((s == null) || (s.length() == 0))
			return null;
		try {
			return new String(new BASE64Decoder().decodeBuffer(s), encode);
		} catch (UnsupportedEncodingException e) {
			log.error("an error occurred", e);
		} catch (IOException e) {
			log.error("an error occurred", e);
		}
		return null;
	}

}