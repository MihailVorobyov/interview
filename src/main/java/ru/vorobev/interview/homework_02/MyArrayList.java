package ru.vorobev.interview.homework_02;

import java.util.AbstractList;

public class MyArrayList<E> extends AbstractList<E> {

	private final int INIT_CAPACITY = 20;
	private Object[] array;
	
	public MyArrayList() {
		this.array = new Object[INIT_CAPACITY];
	}
	
	public MyArrayList(int capacity) {
		this.array = new Object[capacity];
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		E e = null;
		try {
			e = (E) array[index];
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		}
		return e;
	}
	
	@Override
	public int size() {
		return array.length;
	}
}
