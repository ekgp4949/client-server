package com.study.util;

import java.util.Arrays;

public class ArrayList<E> {

	private final int DEFAULT_CAPACITY = 10;
	private Object[] list;
	private int size = 0;

	public ArrayList() {
		list = new Object[DEFAULT_CAPACITY];
	}

	public ArrayList(int c) {
		if(c > DEFAULT_CAPACITY) {
			list = new Object[c];
		} else {
			list = new Object[DEFAULT_CAPACITY];
		}
	}

	public void add(E e) {
		if(size >= list.length) {
			list = Arrays.copyOf(list, list.length + (list.length >> 1));
		}
		list[size++] = e;
	}

	public E get(int index) {
		if(index >= 0 && index < size) {
			return (E)list[index];
		}
		return null;
	}

	public E remove(int index) {
		if(index >= 0 && index < size) {
			E old = (E)list[index];
			int newSize = size - 1;
			System.arraycopy(list, index+1, list, index, newSize - index);
			list[size = newSize] = null;
			return old;
		}
		return null;
	}

	public int size() {
		return this.size;
	}

	public E set(int index, E element) {
		if(index >= 0 && index < size) {
			E old = (E)list[index];
			list[index] = element;
			return old;
		}
		return null;
	}
}
