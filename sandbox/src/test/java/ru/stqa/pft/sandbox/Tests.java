package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.program.distance;

public class Tests {
  @Test
  public void TestPoint() {
    Point p1 = new Point(3, 2);
    Point p2 = new Point(8, 4);
    Assert.assertEquals(distance(p1, p2), 5.385164807134504);

    //Assert.assertEquals(p1.distance(p2),5.385164807134504);
  }

  // первая точка в начале координат
  @Test
  public void TestPoint1() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(2, 4);
    Assert.assertEquals(distance(p1, p2), 4.47213595499958);
  }

  // вторая точка в начале координат
  @Test
  public void TestPoint2() {
    Point p1 = new Point(3, 5);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(distance(p1, p2), 5.830951894845301);
  }

  //обе точки в начале координат
  @Test
  public void TestPoint3() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(distance(p1, p2), 0);
  }

  // отрицательные значения
  @Test
  public void TestPoint4() {
    Point p1 = new Point(-2, 0);
    Point p2 = new Point(0, -4);
    Assert.assertEquals(distance(p1, p2), 4.47213595499958);
  }
}
