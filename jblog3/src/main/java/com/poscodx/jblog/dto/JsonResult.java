package com.poscodx.jblog.dto;

import com.poscodx.jblog.dto.JsonResult;

public class JsonResult {
	private String result; // success or fail
	private String message;  // fail?set
	private Object data; // success?set
	
	private JsonResult(Object data) {
		this.data = data;
		result = "success";
	}
	
	private JsonResult(String message) {
		this.message = message;
		result = "fail";
	}
	
	public static JsonResult success (Object data){
		return new JsonResult(data);
			
		}
	public static JsonResult fail (String message){
		return new JsonResult(message);
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
	
}
