package com.tiemao.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.util.StringUtil;




/**
 * @author hlw
 * @date 2018年7月11日 10:37:00
 */
public class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		//对象所有字段全部列入
		objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
		//取消默认转换timestamps
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		//忽略空bean的错误
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		//所有日期格式为:"yyyy-MM-dd HH:mm:ss"
		objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
		
		//反序列化
		//忽略json字符串存在,java对象中不存在对应属性的情况
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static <T> String obj2str(T obj){
		if(obj == null){
			return null;
		}
		try {
			return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			logger.warn("parse obj to str error", e);
			return null;
		}
	}
	
	/**
	 * 输出格式化好的json
	 * @param obj
	 * @return
	 */
	public static <T> String obj2strPretty(T obj){
		if(obj == null){
			return null;
		}
		try {
			return obj instanceof String ? (String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (IOException e) {
			logger.warn("parse obj to str error", e);
			return null;
		}
	}
	
	public static <T> T str2obj(String str, Class<T> clazz){
		if(StringUtil.isEmpty(str) || clazz == null){
			return null;
		}
		try {
			return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str, clazz);
		} catch (IOException e) {
			logger.warn("parse str to obj error", e);
			return null;
		} 
	}
	
	public static <T> T str2obj(String str, TypeReference<T> typeReference){
		if(StringUtil.isEmpty(str) || typeReference == null){
			return null;
		}
		try {
			return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
		} catch (IOException e) {
			logger.warn("parse str to obj error", e);
			return null;
		} 
	}
	public static <T> T str2obj(String str, Class<?> collectionClass, Class<?>... elementClass){
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
		try {
			return objectMapper.readValue(str, javaType);
		} catch (IOException e) {
			logger.warn("parse str to obj error", e);
			return null;
		} 
	}
	
	
}
