package ru.stqa.pft.sandbox;

public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public double distance(Point p1) {
        return Math.sqrt(Math.pow(x - p1.getX(), 2) + Math.pow(y - p1.getY(), 2));
    }

}
