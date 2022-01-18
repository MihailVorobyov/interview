package ru.vorobev.interview.homework_01.task_03;


public class Triangle extends Shape {
	
	private double a;
	private double b;
	private double c;   // Основание треугольника
	private double angleAB;
	private double angleAC;
	private double angleBC;
	private double h;   // Высота
	
	private Triangle(double a, double b, double c, double angleAB, double angleAC, double angleBC, double h) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.angleAB = angleAB;
		this.angleAC = angleAC;
		this.angleBC = angleBC;
		this.h = h;
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getC() {
		return c;
	}
	
	public double getAngleAB() {
		return angleAB;
	}
	
	public double getAngleAC() {
		return angleAC;
	}
	
	public double getAngleBC() {
		return angleBC;
	}
	
	public double getH() {
		return h;
	}
	
	/**
	 *               angleAB
	 *                  *
	 *                * | *
	 *          a   *   |   *   b
	 *            *   h |     *
	 * angleAC  *   *   *   *   *  angleBC
	 *                  c
	 *
	 */
	@Override
	double getSquare() {
		
		if (a != 0 && c != 0 & angleAC != 0) {
			return 0.5 * a * c * Math.sin(angleAC);
		} else if (h != 0 && a != 0 && angleAC != 0) {
			return 0.5 * h * c;
		} else if(a != 0 && b != 0 && c != 0) {
			double p = (a + b + c) / 2;
			return Math.sqrt(p * (p - a) * (p - b) * (p - c));
		} else if (c != 0 && angleAC != 0 && angleBC != 0) {
			return (c * c / 2) * (Math.sin(angleAC) * Math.sin(angleBC)) / Math.sin(180 - (angleAC + angleBC));
		} else throw new RuntimeException("Неверные параметры или недостаточно параметров");
	}
	
	static class Builder {
		private double a;
		private double b;
		private double c;
		private double angleAB;
		private double angleAC;
		private double angleBC;
		private double h;
		private final Shape triangle = null;
		
		private Builder() {
		
		}
		
		public Builder withA(double a) {
			this.a = a;
			return this;
		}
		
		public Builder withB(double b) {
			this.b = b;
			return this;
		}
		
		public Builder withC(double c) {
			this.c = c;
			return this;
		}
		
		public Builder withAngleAB(double angleAB) {
			this.angleAB = angleAB;
			return this;
		}
		
		public Builder withAngleAC(double angleAC) {
			this.angleAC = angleAC;
			return this;
		}
		
		public Builder withAngleBC(double angleBC) {
			this.angleBC = angleBC;
			return this;
		}
		
		public Builder withH(double h) {
			this.h = h;
			return this;
		}
		
		public Shape build() {
			return new Triangle(this.a, this.b, this.c, this.angleAB, this.angleAC, this.angleBC, this.h);
		}
		
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
}
