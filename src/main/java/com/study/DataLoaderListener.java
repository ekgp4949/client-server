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

import com.study.context.ApplicationContextListener;
import com.study.domain.Lesson;

public class DataLoaderListener implements ApplicationContextListener{

	Map<Integer, Lesson> lessonMap = new HashMap<>();
	@Override
	public void contextInitialized(Map<String, Object> context) {
		System.out.println("contextInitialized");
		readLessonBin();

		context.put("lessonMap", lessonMap);
	}

	@Override
	public void contextDestroyed(Map<String, Object> context) {
		writeLessonBin();
		System.out.println("contextDestroyed");
	}

	/**
	 * lesson binary 읽는 작업
	 * */
	private void readLessonBin() {
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
	private void writeLessonBin() {
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
	private void readLessonCsv() {
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
	private void writeLessonCsv() {
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
