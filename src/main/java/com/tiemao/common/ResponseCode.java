package com.tiemao.common;

/**
 * @author hlw
 * 响应编码
 */
public enum ResponseCode {
	
	SUCCESS(200,"success"),
	ERROR(500,"error"),
	NEED_LOGIN(10,"need_login"),
	ILLEGAL_ARGUMENT(100,"illegal_argument");
	
	private final int code;
	private final String msg;
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	ResponseCode(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
}
