package com.bit.jblog.vo;

import java.util.Date;

public class PostVo {

	private int no;
	private String title;
	private String content;
	private Date reg_date;
	private int category_no;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getCateogry_no() {
		return category_no;
	}
	public void setCateogry_no(int cateogry_no) {
		this.category_no = cateogry_no;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", cateogry_no=" + category_no + "]";
	}
	
	
	
	
	
	
}
