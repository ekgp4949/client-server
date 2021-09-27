package com.study.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private int no;
	private String title;
	private String contents;
	private Date startDate;
	private Date endDate;
	private int totalHours;
	private int dayHours;

	@Override
	public String toString() {
		return "Lesson [번호=" + no + ", 수업명=" + title + ", 수업내용=" + contents + ", 시작일=" + startDate
				+ ", 종료일=" + endDate + ", 총수업시간=" + totalHours + ", 일수업시간=" + dayHours + "]";
	}

	@Override
	public Lesson clone() throws CloneNotSupportedException {
		return (Lesson)super.clone();
	}

	public String csv() {
		return String.valueOf(this.no)+","+this.title+","+this.contents
				+","+ String.valueOf(startDate)+","+String.valueOf(this.endDate)
				+","+String.valueOf(this.totalHours)+","+String.valueOf(this.dayHours);
	}

}
