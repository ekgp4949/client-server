package com.study.board;

import java.sql.Date;

public class Board {
	int no;
	String contents;
	Date createdDate;
	int viewCount;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
