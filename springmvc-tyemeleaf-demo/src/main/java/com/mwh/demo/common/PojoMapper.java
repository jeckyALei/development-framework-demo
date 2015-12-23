package com.mwh.demo.common;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * deserialize a json string to an object serialize an object to a json string
 * 
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
		JsonGenerator jg = jsonFactory.createJsonGenerator(sw);
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
				.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		objectMapper.setDateFormat(dateFormate);
		objectMapper.configure(
				SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
		return objectMapper;
	}

	public static String toJson(Object pojo) throws Exception {
		return toJson(pojo, false);
	}

}
