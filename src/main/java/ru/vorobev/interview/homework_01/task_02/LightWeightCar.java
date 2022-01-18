package ru.vorobev.interview.homework_01.task_02;

class LightWeightCar extends Car {
	
	@Override
	void open() {
		System.out.println("Car is open");
	}
	
	@Override
	public void move() {
		System.out.println("Car is moving fast");
	}
	
	@Override
	public void stop() {
		System.out.println("Car is stop fast");
	}
}
