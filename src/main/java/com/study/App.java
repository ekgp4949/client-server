/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	static Map<Integer, Lesson> lessonMap = new HashMap<>();

	public static void main(String[] args) throws Exception{

		//readLessonCsv();
		readLessonBin();

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

		// writeLessonCsv();
		writeLessonBin();

		scan.close();
	}

	private static String prompt() {
		System.out.print("명령> ");
		return scan.nextLine().toLowerCase();
	}

	/**
	 * lesson binary 읽는 작업
	 * */
	private static void readLessonBin() {
		ObjectInputStream in = null;
		try {
			File file = new File("lesson.bin");
			if(!file.exists()) {
				file.createNewFile();
				return;
			}
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

			Iterator<Lesson> iterator = ((ArrayList<Lesson>) in.readObject()).iterator();
			while(iterator.hasNext()) {
				Lesson lesson = iterator.next();
				lessonMap.put(lesson.getNo(), lesson);
			}


		} catch(Exception e) {
			System.out.println("수업 데이터 읽는 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {in.close();} catch (Exception e) {};
		}
	}

	/**
	 * lesson binary 쓰는 작업
	 * */
	private static void writeLessonBin() {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream("lesson.bin")))) {

			// 첫 번째로 데이터의 개수(int)를 먼저 출력한다.
			List<Lesson> list = new ArrayList<>();
			lessonMap.forEach((key, lesson) -> {
				list.add(lesson);
			});
			out.writeObject(list);
		} catch(Exception e) {
			System.out.println("수업 데이터 쓰는 중 오류 발생");
		}
	}

	/**
	 * lesson.csv 읽는 작업
	 * */
	private static void readLessonCsv() {
		BufferedReader reader = null;
		FileReader in = null;

		try {
			File file = new File("lesson.csv");
			if(!file.exists()) {
				file.createNewFile();
			}
			in = new FileReader(file);
			reader = new BufferedReader(in);
			String line = null;
			while((line = reader.readLine())!= null) {
				String[] values = line.split(",");

				Lesson lesson = Lesson.builder()
						.no(Integer.parseInt(values[0]))
						.title(values[1])
						.contents(values[2])
						.startDate(Date.valueOf(values[3]))
						.endDate(Date.valueOf(values[4]))
						.totalHours(Integer.parseInt(values[5]))
						.dayHours(Integer.parseInt(values[0])).build();

				lessonMap.put(lesson.getNo(), lesson);
			}
		} catch(Exception e) {
			System.out.println("수업 데이터 읽는 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {reader.close();} catch (Exception e) {};
			try {in.close();} catch (Exception e) {};
		}
	}

	/**
	 * lesson csv 쓰는 작업
	 * */
	private static void writeLessonCsv() {
		BufferedWriter writer = null;
		FileWriter out = null;
		try {
			File file = new File("lesson.csv");
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new FileWriter(file);
			writer = new BufferedWriter(out);
			for(Lesson lesson : lessonMap.values()) {
				writer.write(lesson.csv()+"\n");
			}
			writer.flush();
		} catch(Exception e) {
			System.out.println("수업 데이터 쓰는 중 오류 발생");
		} finally {
			try {writer.close();} catch (Exception e) {}
			try {out.close();} catch (Exception e) {}
		}
	}
}
