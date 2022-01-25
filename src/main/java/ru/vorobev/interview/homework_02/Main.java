package ru.vorobev.interview.homework_02;

public class Main {
	
	public static void main(String[] args) {
		MyArrayList<Integer> myArrayList = new MyArrayList<>();
		
		for (int i = 0; i < 128; i++) {
			myArrayList.add(i);
		}
		
		System.out.println(myArrayList);
	}
}
