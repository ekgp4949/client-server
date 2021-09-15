package com.study.handler;

import java.util.Map;
import java.util.Scanner;

import com.study.domain.Lesson;

public class LessonDetailCommand implements Command {
	Scanner scan;
	Map<Integer, Lesson> lessonMap;

	public LessonDetailCommand(Scanner scan, Map<Integer, Lesson> lessonMap) {
		this.scan = scan;
		this.lessonMap = lessonMap;
	}

	@Override
	public void execute() {
		System.out.print("번호 > ");
		int no = Integer.parseInt(scan.nextLine());
		if(lessonMap.containsKey(no)) {
			System.out.println(lessonMap.get(no));
		} else {
			System.out.println("해당 수업을 찾을 수 없습니다.");
		}
	}
}
