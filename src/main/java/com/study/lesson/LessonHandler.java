package com.study.lesson;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Logger;

public class LessonHandler {

	private final static Logger LOG = Logger.getLogger(LessonHandler.class.getName());
	Scanner scan;
	Map<Integer, Lesson> lessonMap = new HashMap<>();

	public LessonHandler(Scanner scan) {
		this.scan = scan;
	}


	public void addLesson() {
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

	public void listLesson() {
		for(Entry<Integer, Lesson> entry : lessonMap.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	public void detailLesson() {
		System.out.print("번호 > ");
		int no = Integer.parseInt(scan.nextLine());
		if(lessonMap.containsKey(no)) {
			System.out.println(lessonMap.get(no));
		} else {
			System.out.println("해당 수업을 찾을 수 없습니다.");
			LOG.info("해당 수업 없음");
		}
	}

	public void updateLesson() {
		System.out.print("번호 > ");
		int no = Integer.parseInt(scan.nextLine());
		if(lessonMap.containsKey(no)) {
			try {
				Lesson old = lessonMap.get(no);
				System.out.println(old);

				Lesson lesson = old.clone();
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
			} catch (Exception e) {
				System.out.println("변경 중 오류 발생!");
				LOG.info("Is Not Cloneable");
			}
		} else {
			System.out.println("해당 수업을 찾을 수 없습니다.");
		}
	}

	public void deleteLesson() {
		System.out.print("번호 > ");
		int no = Integer.parseInt(scan.nextLine());
		if(lessonMap.containsKey(no)) {
			lessonMap.remove(no);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("해당 수업을 찾을 수 없습니다.");
		}
	}


}
