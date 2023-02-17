package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.program.distance;

public class Tests {
@Test
    public void TestPoint(){
        Point p1 =new Point(3,2);
        Point p2=new Point(8,4);
        Assert.assertEquals(distance(p1,p2),5.385164807134504);
        //Assert.assertEquals(p1.distance(p2),5.385164807134504);


    }
}
