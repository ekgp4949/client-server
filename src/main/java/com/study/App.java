/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.study.context.ApplicationContextListener;
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

	Scanner scan = new Scanner(System.in); // 공유하는 것이므로 static으로 둠

	Stack<String> history = new Stack<>();

	Map<String, Command> commandMap = new HashMap<>();


	List<ApplicationContextListener> applicationContextListeners = new ArrayList<>();
	// 공유 context
	Map<String, Object> context = new HashMap<>();


	// Observer를 등록하는 메서드
	private void addApplicationContextListener(ApplicationContextListener listener) {
		applicationContextListeners.add(listener);
	}

	public static void main(String[] args) throws Exception{
		App app = new App();
		app.addApplicationContextListener(new DataLoaderListener());
		app.service();
	}

	public void service() throws Exception{
		for(ApplicationContextListener observer: applicationContextListeners) {
			observer.contextInitialized(context);
		}

		Map<Integer, Lesson> lessonMap = (Map<Integer, Lesson>) context.get("lessonMap");

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
				try {
					commandHandler.execute();
				} catch(Exception ex) {
					System.out.println("명령어 실행 중 오류 발생");
				}
			} else if(command.equals("quit")) {
				break;
			} else {
				System.out.println("실행할 수 없는 명령입니다.");
			}

		}

		scan.close();

		// 서비스를 종료하기 전에 등록된 모든 Observer를 호출하여 종료를 알린다.
		for (ApplicationContextListener listener : applicationContextListeners) {
			listener.contextDestroyed(context);
		}
	}

	private String prompt() {
		System.out.print("명령> ");
		return scan.nextLine().toLowerCase();
	}


}
