package com.bit.jblog.vo;

import java.util.Date;

public class ReplyVo {

	private int no;
	private String content;
	private Date reg_date;
	private int post_no;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	@Override
	public String toString() {
		return "ReplyVo [no=" + no + ", content=" + content + ", reg_date=" + reg_date + ", post_no=" + post_no + "]";
	}

}
