package com.mwh.springboot.exception;

/**
 * 用于block不可重新发送和任务不可重新执行
 * 
 * @author alei
 * 
 */
public class TransferInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferInvalidException(String message) {
		super(message);
	}

	public TransferInvalidException(Throwable cause) {
		super(cause);
	}

	public TransferInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}
