package ru.vorobev.interview.homework_01.task_03;

public class ShapeTest {
	public static void main(String[] args) {
		Shape circle = new Circle(5);
		Shape square = new Square(3);
		Shape triangle = Triangle.getBuilder()
			.withA(3)
			.withB(3)
			.withC(3)
			.build();
		
		System.out.println(circle.getSquare());
		System.out.println(square.getSquare());
		System.out.println(triangle.getSquare());
	}
}
