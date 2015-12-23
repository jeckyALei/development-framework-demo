package com.mwh.springboot.exception;
/**
 * 此异常类仅仅使用于传输服务
 * @author alei
 *
 */

public class TransferNotSupportException extends TransferInvalidException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferNotSupportException(String message) {
		super(message);
	}

	public TransferNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

}
