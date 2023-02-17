package ru.stqa.pft.sandbox;

import java.sql.SQLOutput;

public class program {
	public static void main(String [] args) {
		Point p1 = new Point(3,2);
		Point p2 = new Point(8,4);

		//вывод функции
		System.out.println("Расстояние между точкой А (" + p1.x +"," + p1.y +") и точкой В (" + p2.x +"," +p2.y + ") = " + distance(p1,p2));

		// вывод метода
		//System.out.println("Расстояние между точкой А (" + p1.x +"," + p1.y +") и точкой В (" + p2.x +"," +p2.y + ") = " +  p1.distance(p2));

	}

	//функция
	 public static double distance (Point p1, Point p2){
		return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
	}


}
	