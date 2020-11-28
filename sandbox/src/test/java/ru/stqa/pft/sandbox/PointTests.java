package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test (enabled = false)
    public void falseDistanceTest() {
        Point p3 = new Point(5, 7);
        Point p4 = new Point(3, 3);
        Assert.assertEquals(p3.distance(p4), 55);
    }

    @Test
    public void trueDistanceTest() {
        Point p5 = new Point(1, 1);
        Point p6 = new Point( 2, 2);
        Assert.assertEquals(p5.distance(p6), 1.4142135623730951);
    }
}
