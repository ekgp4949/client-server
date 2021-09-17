package com.study.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

	@Override
	public Queue<E> clone() {
		Queue<E> temp = new Queue<>();
		for(int i = 0; i < this.size(); i++) {
			temp.offer(this.get(i));
		}
		return temp;
	}

	public void offer(E e) {
		add(e);
	}

	public E poll() {
		if(this.size() > 0) {
			return remove(0);
		}
		return null;
	}

	public boolean empty() {
		return size() == 0;
	}

	public Iterator<E> iterator() {
		return new Iterator<>() {
			Queue<E> tmp = Queue.this.clone();
			@Override
			public E next() {
				return tmp.poll();
			}
			@Override
			public boolean hasNext() {
				return !tmp.empty();
			}
		};
	}
}
