package com.bit.jblog.utils.status;

import com.bit.jblog.utils.SimpleStatusEnum;

public enum BlogStatusCode implements SimpleStatusEnum {
	
	BLOG_MAKE_SUCCESS(101),
	BLOG_MAKE_FAIL(102),
	BLOG_UPDATE_SUCCESS(103),
	BLOG_UPDATE_FAIL(104),
	CATEGORY_ADD_SUCCESS(105),
	CATEGORY_ADD_FAIL(106),
	CATEGORY_DEL_SUCCESS(107),
	CATEGORY_DEL_FAIL(108),
	CATEGORY_HAS_POSTING(109),
	POST_SUCCESS(110),
	POST_FAIL(111),
	POST_DEL_SUCCESS(112),
	POST_DEL_FAIL(113),
	POST_UPDATE_SUCCESS(114),
	POST_UPDATE_FAIL(115),
	REPLY_WRITE_SUCCESS(116),
	REPLY_WRITE_FAIL(117),
	REPLY_UPDATE_SUCCESS(118),
	REPLY_UPDATE_FAIL(119),
	REPLY_DEL_SUCCESS(120),
	REPLY_DEL_FAIL(121);

	int code;

	private BlogStatusCode(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getKey() {
		return this.name();
	}
	
	
	
	
}
