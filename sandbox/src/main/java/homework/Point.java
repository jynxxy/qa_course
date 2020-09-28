package homework;

public class Point {
    double x, y;

    Point(double xcoord, double ycoord) {
        this.x = xcoord;
        this.y = ycoord;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2)));
    }

    public static void main(String[] args) {
        Point p1 = new Point(5,5);
        Point p2 = new Point( 3,3);

        System.out.println("Distance is " + distance(p1,p2));
    }
}
