package com.mwh.springmvc.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.embracesource.infinispan.common.serialization.SerializationFactory;
import com.mwh.springmvc.exception.CustomSystemException;
import com.mwh.springmvc.exception.CustomValidateDomainException;
import com.mwh.springmvc.util.Base64Util;

public abstract class ResponseJSONHelp {
	private static final String _JSON_ATT = "_json_att";
	private static final String CONTENT_TYPE_CONTENT = "application/json;charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String DOT = ".";

	/**
	 * 构建字符串类型的响应实体
	 * 
	 * @param jsonObject
	 * @param filterNames
	 * @return
	 */
	public static ResponseEntity<String> buildResponseEntity(Object jsonObject,
			String... filterNames) {
		String content = JsonUtils.toJson(jsonObject, filterNames);
		return buildStringResponseEntity(content);
	}

	/**
	 * 构建字符串类型的响应实体
	 * 
	 * @param content
	 * @return
	 */
	public static ResponseEntity<String> buildStringResponseEntity(
			String content) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(CONTENT_TYPE, CONTENT_TYPE_CONTENT);
		return new ResponseEntity<String>(content, responseHeaders,
				HttpStatus.OK);
	}

	/**
	 * bean validator 使用标准的Bean校验实体的值是否符合规则
	 * 
	 * @param validator
	 * @param model
	 */
	public static void checkDomainThrowException(Validator validator,
			Object model, String prefix) {
		if (validator == null || model == null) {
			throw new IllegalArgumentException("checkDomain arguments is null");
		}
		Set<ConstraintViolation<Object>> set = validator.validate(model);
		Map<String, String> map = new HashMap<String, String>();
		for (ConstraintViolation<Object> constraintViolation : set) {
			System.out.println(constraintViolation.getMessage());
		}
		if (StringUtils.isEmpty(prefix)) {
			for (ConstraintViolation<?> failure : set) {
				String name = failure.getPropertyPath().toString();
				String realName = MessageSourceExpand.getMessage(name, name);
				map.put(realName, failure.getMessage());
			}
		} else {
			for (ConstraintViolation<?> failure : set) {
				String name = prefix + DOT
						+ failure.getPropertyPath().toString();
				String realName = MessageSourceExpand.getMessage(name, name);
				map.put(realName, failure.getMessage());
			}
		}
		if (map.size() > 0) {
			throw new CustomValidateDomainException(map);
		}
	}

	/**
	 * bean validator 使用标准的Bean校验实体的值是否符合规则
	 * 
	 * @param validator
	 * @param model
	 * @param prefix
	 * @param ignores
	 *            要忽略的约束,key是属性名，value是要忽略的约束的class数组
	 */
	public static void checkDomainThrowException(Validator validator,
			Object model, String prefix, Map<String, Class[]> ignores) {
		if (validator == null || model == null) {
			throw new IllegalArgumentException("checkDomain arguments is null");
		}
		Set<ConstraintViolation<Object>> set = validator.validate(model);
		Iterator<ConstraintViolation<Object>> it = set.iterator();
		while (it.hasNext()) {
			ConstraintViolation<Object> error = it.next();
			for (String key : ignores.keySet()) {
				Class[] constraints = ignores.get(key);
				for (Class constraintClass : constraints) {
					if (key.equals(error.getPropertyPath().toString())
							&& error.getConstraintDescriptor().getAnnotation()
									.annotationType() == constraintClass) {
						it.remove();
					}
				}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(prefix)) {
			for (ConstraintViolation<?> failure : set) {
				String name = failure.getPropertyPath().toString();
				String realName = MessageSourceExpand.getMessage(name, name);
				map.put(realName, failure.getMessage());
			}
		} else {
			for (ConstraintViolation<?> failure : set) {
				String name = prefix + DOT
						+ failure.getPropertyPath().toString();
				String realName = MessageSourceExpand.getMessage(name, name);
				map.put(realName, failure.getMessage());
			}
		}
		if (map.size() > 0) {
			throw new CustomValidateDomainException(map);
		}
	}

	public static Map<String, Object> getAttributes() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String jsonAttributes = request.getParameter(_JSON_ATT);
		try {
			if (!StringUtils.isEmpty(jsonAttributes)) {
				byte[] bytes = Base64Util.decode(jsonAttributes);
				if (bytes != null) {
					bytes = SerializationFactory.getESCompressBytes()
							.decompressBytes(bytes);
					Map<String, Object> map = (Map<String, Object>) SerializationFactory
							.getESSerialization().toObject(bytes);
					return map;
				}
			}
		} catch (Exception e) {
			throw new CustomSystemException(e);
		}
		return null;
	}

	/**
	 * 取得旧的responseJSON对象
	 * 
	 * @return ResponseJSON 如果原来不存在，返回一个新的
	 */
	public static ResponseJSON getResponseJSON() {
		ResponseJSON responseJSON = ResponseJSON.instance();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String jsonAttributes = request.getParameter(_JSON_ATT);
		try {
			if (!StringUtils.isEmpty(jsonAttributes)) {
				byte[] bytes = Base64Util.decode(jsonAttributes);
				if (bytes != null) {
					bytes = SerializationFactory.getESCompressBytes()
							.decompressBytes(bytes);
					Map<String, Object> map = (Map<String, Object>) SerializationFactory
							.getESSerialization().toObject(bytes);
					Iterator<String> it = map.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next();
						responseJSON.addAttribute(key, map.get(key));
					}
					return responseJSON;
				}
			}
		} catch (Exception e) {
			throw new CustomSystemException(e);
		}
		return responseJSON;
	}

	public static void setAttributes(ResponseJSON responseJSON) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.setAttribute(_JSON_ATT, responseJSON.getAttributes());
	}
}
