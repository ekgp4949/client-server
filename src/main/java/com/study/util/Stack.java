package com.study.util;

import java.util.Arrays;

public class Stack<E> implements Cloneable {
	private static final int DEFUALT_SIZE = 10;

	private Object[] list;
	private int size = 0;

	@Override
	public Stack<E> clone() {
		// 깊은 복사 해줘야함.
		Stack<E> stack = new Stack<>();
		for(int i = 0; i < size; i++) {
			stack.push((E)list[i]);
		}
		return stack;
	}

	public Stack() {
		list = new Object[DEFUALT_SIZE];
	}

	public void push(E e) {
		if(size >= DEFUALT_SIZE) {
			list = Arrays.copyOf(list, list.length + (list.length >> 1));
		}
		list[size++] = e;
	}

	public E pop() {
		if(size == 0) {
			return null;
		}
		E element = (E)list[--size];
		return element;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		return new Iterator<>() {
			Stack<E> tmp = Stack.this.clone();
			@Override
			public E next() {
				return tmp.pop();
			}
			@Override
			public boolean hasNext() {
				return !tmp.isEmpty();
			}
		};
	}
}
