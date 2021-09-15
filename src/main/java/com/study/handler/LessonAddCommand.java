package com.study.handler;

import java.sql.Date;
import java.util.Map;
import java.util.Scanner;

import com.study.domain.Lesson;

public class LessonAddCommand implements Command {

	Scanner scan;
	Map<Integer, Lesson> lessonMap;

	public LessonAddCommand(Scanner scan, Map<Integer, Lesson> lessonMap) {
		this.scan = scan;
		this.lessonMap = lessonMap;
	}

	@Override
	public void execute() {
		Lesson lesson = new Lesson();
		System.out.print("번호? ");
		lesson.setNo(Integer.parseInt(scan.nextLine()));
		System.out.print("수업명? ");
		lesson.setTitle(scan.nextLine());
		System.out.print("수업내용? ");
		lesson.setContents(scan.nextLine());
		System.out.print("시작일? ");
		lesson.setStartDate(Date.valueOf(scan.nextLine()));
		System.out.print("종료일? ");
		lesson.setEndDate(Date.valueOf(scan.nextLine()));
		System.out.print("총수업시간? ");
		lesson.setTotalHours(Integer.parseInt(scan.nextLine()));
		System.out.print("일수업시간? ");
		lesson.setDayHours(Integer.parseInt(scan.nextLine()));
		lessonMap.put(lesson.getNo(), lesson);
		System.out.println("저장하였습니다.");
	}
}
