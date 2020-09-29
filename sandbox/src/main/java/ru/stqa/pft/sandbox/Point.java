package ru.stqa.pft.sandbox;

public class Point {
    double x, y;

    public Point(double xcoord, double ycoord) {
        this.x = xcoord;
        this.y = ycoord;
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
