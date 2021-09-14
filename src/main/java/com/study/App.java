/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.study;

import java.util.Scanner;

import com.study.lesson.LessonHandler;

public class App {
	public String getGreeting() {
		return "Hello world.";
	}

	static Scanner scan = new Scanner(System.in); // 공유하는 것이므로 static으로 둠

	public static void main(String[] args) {

		LessonHandler lessonHandler = new LessonHandler(scan); // lessonHandler 하나 당 하나의 LessonMap 객체
		LessonHandler lessonHandler2 = new LessonHandler(scan); // lessonHandler 하나 당 하나의 LessonMap 객체

		String command;
		while(true) {
			command = prompt();
			if(command.equals("/lesson/add")) {
				lessonHandler.addLesson();
			} else if(command.equals("/lesson/list")) {
				lessonHandler.listLesson();
			} if(command.equals("/lesson2/add")) {
				lessonHandler2.addLesson();
			} else if(command.equals("/lesson2/list")) {
				lessonHandler2.listLesson();
			} else if(command.equals("quit")) {
				break;
			}

		}

		scan.close();
	}

	private static String prompt() {
		System.out.print("명령> ");
		return scan.nextLine().toLowerCase();
	}
}
