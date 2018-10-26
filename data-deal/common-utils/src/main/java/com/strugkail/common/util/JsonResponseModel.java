package com.strugkail.common.util;

import java.io.Serializable;

/***
 * 后台向客户端相应的 json model
 * 
 * @author bill.xu
 *
 */
public class JsonResponseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -873693755384635520L;

	/***
	 * 操作成功信息
	 */
	 public  static final String SUCCESS_MSG="操作成功";

 	/***
 	 * 操作失败信息
 	 */
      public  static final String FAIL_MSG="操作失败";
	
      /***
       * 操作失败信息
       */
      public  static final String FAIL_CODE="300";
      
      /***
       * 操作成功信息
       */
      public  static final String SUCCESS_CODE="200";
      
      
	/**
	 * 给客户端响应成功还是失败
	 * true
	 * false
	 */
	private boolean result;
	
	/**
	 * 消息体,操作成功提示文本,或者操作失败提示文本
	 */
	private String msg;
	
	/**
	 * 请求成功的一些 data 数据
	 */
	private Object data;
	
	
	private String code;
	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	@Override
	public String toString() {
		return "JsonResponseModel [result=" + result + ", msg=" + msg
				+ ", data=" + data + "]";
	}


	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	public JsonResponseModel success(Object data){
		
		this.setData(data);
		this.setMsg(SUCCESS_MSG);
		this.setCode(SUCCESS_CODE);
        this.setResult(true);		
		return this;
	}
	
	public JsonResponseModel fail(String msg){
		this.setCode(FAIL_CODE);
		this.setMsg(msg);
		this.setResult(false);
		return this;
	}

	public JsonResponseModel(boolean result, String msg, String code) {
		super();
		this.result = result;
		this.msg = msg;
		this.code = code;
	}
	
	public JsonResponseModel fail(String msg,String code){
		this.setMsg(msg);
		this.setCode(code);
		this.setResult(false);
		return this;
	}

	public JsonResponseModel() {
		super();
	}
	
	
	
}
