package com.mwh.springboot.exception;
/**
 * 用于block重新发送和任务重新执行
 * @author alei
 *
 */
public class TransferCanRetryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferCanRetryException(Throwable cause) {
		super(cause);
	}

	public TransferCanRetryException(String message, Throwable cause) {
		super(message, cause);
	}
}
