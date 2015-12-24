package com.mwh.springboot.common.utils;

import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectionPoolTimeoutException;

public class TransferExceptionUtil {
	public static boolean isRetryException(Throwable cause) {
		if (cause != null) {
			return checkException(cause);
		} else {
			return true;
		}
	}

	private static boolean checkException(Throwable cause) {
		String exName = cause.getClass().getName();
		exName += cause.getMessage();
		if (exName.contains("Exception")
				&& (exName.contains("Timeout") || exName.contains("Connect") || exName.contains("Interrupt"))) {
			return true;
		} else {
			return false;
		}
	}
}
