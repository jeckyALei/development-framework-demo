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

	public static void main(String[] args) {
		ConnectionPoolTimeoutException exception = new ConnectionPoolTimeoutException(
				" Timeout waiting for connection from pool");
		SocketTimeoutException exception1 = new SocketTimeoutException("Read timed out");
		System.out.println(checkException(exception1));
		// String exName =
		// "class java.lang.RuntimeException:java.net.SocketTimeoutException: Read timed out";
		// exName="class java.lang.RuntimeException:org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool";
		// if (exName.contains("Exception")
		// && (exName.contains("Timeout") || exName.contains("Connect") ||
		// exName.contains("Interrupt"))) {
		// System.out.println("true");
		// } else {
		// System.err.println("false");
		// }
	}
}
