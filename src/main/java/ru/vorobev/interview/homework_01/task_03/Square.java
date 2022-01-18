package ru.vorobev.interview.homework_01.task_03;

public class Square extends Shape {
	private double a;
	
	public Square(double a) {
		this.a = a;
	}
	
	public double getA() {
		return a;
	}
	
	@Override
	double getSquare() {
		return a * a;
	}
}
