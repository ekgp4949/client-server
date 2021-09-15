package com.study.handler;

import java.sql.Date;
import java.util.Map;
import java.util.Scanner;

import com.study.domain.Lesson;

public class LessonUpdateCommand implements Command {
	Scanner scan;
	Map<Integer, Lesson> lessonMap;

	public LessonUpdateCommand(Scanner scan, Map<Integer, Lesson> lessonMap) {
		this.scan = scan;
		this.lessonMap = lessonMap;
	}
	@Override
	public void execute() {
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
			}
		} else {
			System.out.println("해당 수업을 찾을 수 없습니다.");
		}
	}
}

