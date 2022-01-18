package ru.vorobev.interview.homework_01.task_02;

class Lorry extends Car {
	
	@Override
	public void move() {
		System.out.println("Car is moving slowly");
	}
	
	@Override
	public void stop() {
		System.out.println("Car is stop slowly");
	}
	
	@Override
	void open() {
		System.out.println("Open Lorry");
	}
}
