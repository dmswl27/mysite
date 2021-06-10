/**
 * 
 */
package com.douzone.mysite.dto;

/**
 * @author eunji
 *
 */
public class JsonResult {
	private String result; // "success" or "fail"
	private Object date;    // if succcess, Data set
	private String message;  // if result, message set
	
	private JsonResult() {
		
	}
	private JsonResult(Object data) {
		result = "success";
		this.date = data;
	}
	private JsonResult(String message) {
		result = "fail";
		this.message = message;
	}
	public static JsonResult success(Object data) {
		return new JsonResult(data);
		
	}
	public static JsonResult fail(String message) {
		return new JsonResult(message);
		
	}
	public String getResult() {
		return result;
	}
	public Object getDate() {
		return date;
	}
	public String getMessage() {
		return message;
	}
	
}
