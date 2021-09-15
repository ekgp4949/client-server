/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.study.domain.Lesson;
import com.study.handler.Command;
import com.study.handler.LessonAddCommand;
import com.study.handler.LessonDeleteCommand;
import com.study.handler.LessonDetailCommand;
import com.study.handler.LessonListCommand;
import com.study.handler.LessonUpdateCommand;
import com.study.handler.PrintHistoryCommand;
import com.study.util.Stack;

public class App {
	private final static Logger logger = Logger.getLogger(App.class.getName());

	public String getGreeting() {
		return "Hello world.";
	}

	static Scanner scan = new Scanner(System.in); // 공유하는 것이므로 static으로 둠

	static Stack<String> history = new Stack<>();

	// 커맨드 패턴의 장점은 실행 로직을 숨길 수 있다(은닉화, 캡슐화), 추상화를 잘 사용할 수 있다. 호출자와 수신자 사이의 의존성을 제거한다. 코드의 재사용성을 높일 수 있다.
	// 단점은 각 명령에 대해 매번 클래스를 만들어야 하는 것이다.

	static Map<String, Command> commandMap = new HashMap<>();

	public static void main(String[] args) throws Exception{
		Map<Integer, Lesson> lessonMap = new HashMap<>();

		commandMap.put("/lesson/add", new LessonAddCommand(scan, lessonMap));
		commandMap.put("/lesson/list", new LessonListCommand(scan, lessonMap));
		commandMap.put("/lesson/update", new LessonUpdateCommand(scan, lessonMap));
		commandMap.put("/lesson/detail", new LessonDetailCommand(scan, lessonMap));
		commandMap.put("/lesson/delete", new LessonDeleteCommand(scan, lessonMap));
		commandMap.put("/history", new PrintHistoryCommand(history));

		String command;
		while(true) {
			command = prompt();
			history.push(command);
			Command commandHandler = commandMap.get(command);
			if(commandHandler != null) {
				commandHandler.execute();
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

	private static void printCommandHistory() {
		try {
			Stack<String> commandHistory = history.clone();
			int size = commandHistory.size();
			for(int i = 0; i < size ; i++) {
				System.out.println(commandHistory.pop());
			}
		} catch(Exception e) {
			logger.warning("명령어 출력 오류남");
		}
	}
}
