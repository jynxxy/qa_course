package ru.stqa.pft.sandbox;

public class FirstProgram {

    public static void main(String[] args) {
        hello("word");
        hello("user");
        hello("Slawek");

        Square s = new Square(5);
        System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Powierzchnia prostokata o bokach " + r.a + " i " + r.b + " = " + r.area());

        Point p1 = new Point(5,5);
        Point p2 = new Point( 3,3);

        System.out.println("Distance is " + p1.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}