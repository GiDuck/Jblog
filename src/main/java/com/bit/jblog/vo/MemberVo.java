package com.bit.jblog.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberVo {

	private int no;
	private String id;
	private String name;
	private String password;
	private Date join_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", join_date="
				+ join_date + "]";
	}
	
	
	
	
	
	
	
}
