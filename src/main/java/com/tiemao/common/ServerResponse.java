package com.tiemao.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author hlw
 * @date 2018年7月5日 16:09:20
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
	private String msg;
	private int code;
	private T data;
	
	private ServerResponse(int code) {
		this.code = code;
	}
	private ServerResponse(int code, T data) {
		this.code = code;
		this.data = data;
	}
	//T泛型传string走这个方法
	private ServerResponse(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	private ServerResponse(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * 设定200是成功状态码
	 * @return 返回是否成功
	 * json序列化时忽略此方法
	 */
	@JsonIgnore
	public boolean isSuccess(){
		return this.code == ResponseCode.SUCCESS.getCode();
	}
	
	public int getCode(){
		return code;
	} 
	
	public String getMsg(){
		return msg;
	}

	public T getData(){
		return data;
	}
	
	public static <T>ServerResponse<T> createBySuccess(){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T>ServerResponse<T> createBySuccessMsg(String msg){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}
	
	public static <T>ServerResponse<T> createBySuccess(T data){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}
	
	public static <T>ServerResponse<T> createBySuccess(String msg, T data){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}
	
	public static <T>ServerResponse<T> createByError(){
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
	}
	
	public static <T>ServerResponse<T> createByErrorMsg(String msg){
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
	}
	
	public static <T>ServerResponse<T> createByErrorCodeMsg(int code, String msg){
		return new ServerResponse<T>(code, msg);
	}
}
