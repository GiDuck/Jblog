package com.bit.jblog.utils.status;

import com.bit.jblog.utils.SimpleStatusEnum;

public enum MemberStatusCode implements SimpleStatusEnum {

	JOIN_SUCCESS(1), 
	JOIN_FAIL(2),
	AUTH_SUCCESS(3),
	AUTH_FAIL(4),
	LOGIN_SUCCESS(5),
	LOGIN_FAIL(6),
	ID_NOT_VALID(7),
	PWD_NOT_VALID(8),
	ACCOUNT_MOD_SUCCESS(9),
	ACCOUNT_MOD_FAIL(10),
	ACCOUNT_DEL_SUCCESS(11),
	ACCOUNT_DEL_FAIL(12);
	
	private int code;
	
	MemberStatusCode(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getKey() {
		return this.name();
	}
	
	
	
	
}
