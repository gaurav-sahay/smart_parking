package org.smart.parking.raspi.driver;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONConverter {

	
	public static String convertToJson(Object obj) {
		String json = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		return json;
	}

	
	public static Object convertToObject(String json, Class<?> clazz) {
		Object object = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();			
			object = (Object) objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		return object;
	}
	
}