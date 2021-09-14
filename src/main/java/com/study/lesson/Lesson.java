package com.study.lesson;

import java.sql.Date;

public class Lesson {
	int no;
	String title;
	String contents;
	Date startDate;
	Date endDate;
	int totalHours;
	int dayHours;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}
	public int getDayHours() {
		return dayHours;
	}
	public void setDayHours(int dayHours) {
		this.dayHours = dayHours;
	}
	@Override
	public String toString() {
		return "Lesson [번호=" + no + ", 수업명=" + title + ", 수업내용=" + contents + ", 시작일=" + startDate
				+ ", 종료일=" + endDate + ", 총수업시간=" + totalHours + ", 일수업시간=" + dayHours + "]";
	}
	
	
}
