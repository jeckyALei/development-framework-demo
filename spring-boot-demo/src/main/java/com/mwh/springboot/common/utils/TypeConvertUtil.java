package com.mwh.springboot.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TypeConvertUtil {

	public static Object bytesToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = null;
			ObjectInputStream oi = null;
			try {
				bi = new ByteArrayInputStream(bytes);
				oi = new ObjectInputStream(bi);
				obj = oi.readObject();
			} catch (Exception e) {
				throw new RuntimeException(
						"It's to occur error when byte[] is converted to object ",
						e);
			} finally {
				if (bi != null) {
					bi.close();
				}
				if (oi != null) {
					oi.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

	public static byte[] objectToBytes(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = null;
			ObjectOutputStream oo = null;
			try {
				bo = new ByteArrayOutputStream();
				oo = new ObjectOutputStream(bo);
				oo.writeObject(obj);
				bytes = bo.toByteArray();
				bo.close();
				oo.close();
			} catch (Exception e) {
				throw new RuntimeException(
						"It's to occur error when object is converted to byte[] ",
						e);
			} finally {
				if (bo != null) {
					bo.close();
				}
				if (oo != null) {
					oo.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return bytes;
	}
}
