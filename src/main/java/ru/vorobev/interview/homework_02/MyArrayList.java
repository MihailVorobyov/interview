package ru.vorobev.interview.homework_02;

import java.util.AbstractList;

public class MyArrayList<E> extends AbstractList<E> {

	private final int INIT_CAPACITY = 20;
	private Object[] array;
	private int size;
	
	public MyArrayList() {
		this.array = new Object[INIT_CAPACITY];
		size = 0;
	}
	
	public MyArrayList(int capacity) {
		this.array = new Object[capacity];
		size = 0;
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
	public boolean add(E e) {
		if (size == array.length) {
			Object[] newArray = new Object[size() + size()/2];
			System.arraycopy(array, 0, newArray, 0, size());
			array = newArray;
		}
		array[size++] = e;
		
		return true;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(get(i));
			if (i != size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
}
