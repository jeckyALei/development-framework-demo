package com.mwh.demo.exception;

import java.util.Map;

public class CustomValidateDomainException extends RuntimeException {

	private static final long serialVersionUID = 4345504160068164233L;
	private Map<String, String> validateMap = null;

	public CustomValidateDomainException(Map<String, String> validateMap) {
		super();
		this.validateMap = validateMap;
	}

	public Map<String, String> getValidateMap() {
		return validateMap;
	}

}
