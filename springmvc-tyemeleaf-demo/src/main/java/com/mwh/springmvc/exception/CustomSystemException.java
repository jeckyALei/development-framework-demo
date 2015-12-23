package com.mwh.springmvc.exception;

public class CustomSystemException extends RuntimeException{

	private static final long serialVersionUID = -7936621829410590188L;

	public CustomSystemException() {
		super();
	}

	public CustomSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomSystemException(String message) {
		super(message);
	}

	public CustomSystemException(Throwable cause) {
		super(cause);
	}
	
}
