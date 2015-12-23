package com.mwh.springboot.common.utils;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * deserialize a json string to an object serialize an object to a json string
 * 
 * @author bingo
 */
public class PojoMapper {
	private static final String FILTER_NAME = "_Filter_Name";
	private static final String JSON_DATE_FROMATE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * deserializea a json string to an object
	 * 
	 * @param <T>
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
			throws Exception {
		return (T) getObjectMapper().readValue(jsonAsString, pojoClass);
	}
	
	/**
	 * deserializea a json string to an object
	 * 
	 * @param <T>
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonAsString, TypeReference<T> type)
			throws Exception {
		return (T) getObjectMapper().readValue(jsonAsString, type);
	}
	
	public byte[] toByteArray(Object obj)throws Exception{
		
		return getObjectMapper().writeValueAsBytes(obj);
	}
	public <T> T fromByteArray(byte[] ba,TypeReference<T> type)throws Exception{
		
		return getObjectMapper().readValue(ba, type);
	}

	/**
	 * serializea an object to a json string
	 * 
	 * @param pojo
	 * @param prettyPrint
	 *            if true,enabling pretty-printing using the default pretty
	 *            printer
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object pojo, boolean prettyPrint,
			String... filterNames) throws Exception {
		ObjectMapper objectMapper = getObjectMapper();
		StringWriter sw = new StringWriter();
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator jg = jsonFactory.createGenerator(sw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		SimpleFilterProvider fp =  new SimpleFilterProvider();
		if (filterNames != null && filterNames.length > 0) {
			fp.addFilter(
					FILTER_NAME,
					SimpleBeanPropertyFilter.serializeAllExcept(filterNames));
		}else{
			fp.addFilter(
					FILTER_NAME,
					SimpleBeanPropertyFilter.serializeAllExcept(new String[]{}));
		}
		objectMapper.writer(fp).writeValue(jg,pojo);
		return sw.toString();
	}

	public static String toJson(Object pojo,String... filterNames)
			throws Exception {
		return toJson(pojo, false, filterNames);
	}

	private static ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormate = new SimpleDateFormat(JSON_DATE_FROMATE);
		objectMapper
				.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.setDateFormat(dateFormate);
//		objectMapper.configure(
//				SerializationConfig.collectFeatureDefaults(arg0).Feature.WRITE_ENUMS_USING_TO_STRING, true);
		return objectMapper;
	}

	public static String toJson(Object pojo) throws Exception {
		return toJson(pojo, false);
	}

}
