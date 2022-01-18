package ru.vorobev.interview.homework_01.task_03;

public class Circle extends Shape {
	
	private double r;
	
	public Circle(double r) {
		this.r = r;
	}
	
	public double getR() {
		return r;
	}
	
	@Override
	double getSquare() {
		return Math.PI * r * r;
	}
}
