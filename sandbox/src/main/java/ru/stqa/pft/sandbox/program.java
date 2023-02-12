package ru.stqa.pft.sandbox;

import java.sql.SQLOutput;

public class program {
	public static void main(String [] args) {
		Square s = new Square();
		Rectangle r=new Rectangle();
		Point p1 = new Point();
		Point p2 = new Point();
		r.a=2;
		r.b=3;
		s.l=5;
		p1.x=3;
		p2.x=8;
		p1.y=2;
		p2.y=4;
		System.out.println("площадь квадрата со стороной " + s.l + "=" + area(s));
		System.out.println("Прямоугольник со сторонами " +r.a +"," +r.b + "=" +area(r) );
		System.out.println(distance(p1,p2));
	}

	public static double area(Square s){
		return s.l*s.l;
	}

	public static double area (Rectangle r) {
		return r.a*r.b;

	}

	public static double distance (Point p1, Point p2){
		return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
	}
}
	