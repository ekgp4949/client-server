package com.study.handler;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.study.domain.Lesson;

public class LessonListCommand implements Command {
	Scanner scan;
	Map<Integer, Lesson> lessonMap;

	public LessonListCommand(Scanner scan, Map<Integer, Lesson> lessonMap) {
		this.scan = scan;
		this.lessonMap = lessonMap;
	}
	@Override
	public void execute() {
		for(Entry<Integer, Lesson> entry : lessonMap.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
}
