package com.bit.jblog.utils;

public class StatusEnum {
	
	private int code;
	private String key;

	public StatusEnum(SimpleStatusEnum e) {
		this.code = e.getCode();
		this.key = e.getKey();
	}

	public int getCode() {
		return code;
	}
	
	public String getKey() {
		return key;
	}
	


	
	
	

}
