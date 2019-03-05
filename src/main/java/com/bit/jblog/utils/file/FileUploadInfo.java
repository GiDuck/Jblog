package com.bit.jblog.utils.file;

public enum FileUploadInfo {
	
	PARENT_PATH("c:/"),
	UPLOAD_IMAGE_PATH("/uploads/images/");
	
	private String value;

	private FileUploadInfo(String value) {
		this.value = value;
	}




	public String getValue() {
		return value;
	}

	
	
	

}
