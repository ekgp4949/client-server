package com.study.util;

import java.lang.reflect.Array;

public class LinkedList<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public LinkedList() {
	}

	public void add(E e) {
		this.size++;
		// 새 노드부터 생성한다.
		Node<E> newNode = new Node<>(tail, e, null);
		if(head == null) {
			tail = head = newNode;
			return;
		}
		tail.next = newNode;
		tail = newNode;
	}

	public E get(int index) {
		if(index >= size || index < 0) {
			return null;
		}
		Node<E> item = head;
		for(int i = 1; i <= index; i++) {
			item = item.next;
		}
		return item.value;
	}

	public E remove(int index) {
		if(index >= size || index < 0) {
			return null;
		}

		this.size--;

		Node<E> item = head;

		// size == 0일 때, 다시 초기화해줘야함.
		if(this.size == 0) {
			head = tail = null;
			return item.value;
		}

		for(int i = 1; i <= index; i++) {
			item = item.next;
		}

		if(item == head) {
			head = item.next;
			head.prev = null; // 이전 노드는 null이어야 함
		} else if(item == tail) {
			tail = item.prev;
			tail.next = null; // 다음 노드는 null이어야 함
		} else {
			item.next.prev = item.prev;
			item.prev.next = item.next;
		}

		E deletedValue = item.value;;

		// 이하 GC 도와주기
		item.prev = null;
		item.next = null;
		item.value = null;

		return deletedValue;
	}

	public int size() {
		return size;
	}

	public E[] toArray(E[] a) {
		E[] arr = (E[]) Array.newInstance(a.getClass().getComponentType(), size);
		int i = 0;
		for(Node<E> node = head; node.next == null; node = node.next) {
			arr[i++] = node.value;
		}
		return arr;
	}

	class Node<E> {
		private Node<E> prev;
		private Node<E> next;
		private E value;

		private Node(Node<E> prev, E value, Node<E> next) {
			this.prev = prev;
			this.next = next;
			this.value = value;
		}
	}
}
