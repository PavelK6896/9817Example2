package app.web.pavelk.two;

public class Main {
    public static void main(String[] args) {

        Circle circle1 = new CircleImpl(10);
        System.out.println(circle1.getName() + " getArea " + circle1.getArea() + " getRadius " + circle1.getRadius() + " getPerimeter " + circle1.getPerimeter());
        Circle circle2 = new CircleImpl(2);
        System.out.println(circle2.getName() + " getArea " + circle2.getArea() + " getRadius " + circle1.getRadius() + " getPerimeter " + circle2.getPerimeter());

        Square square = new SquareImpl(32);
        System.out.println(square.getName() + " getArea " + square.getArea() + " getSide " + square.getSide() + " getPerimeter " + square.getPerimeter());

        Triangle triangle = new TriangleImpl(15, 10, 20);
        System.out.println(triangle.getName() + " getArea " + triangle.getArea() + " getSide " + triangle.getSide(1) + " getPerimeter " + triangle.getPerimeter());
        System.out.println(triangle.getName() + " getArea " + triangle.getArea() + " getSide " + triangle.getSide(2) + " getPerimeter " + triangle.getPerimeter());
        System.out.println(triangle.getName() + " getArea " + triangle.getArea() + " getSide " + triangle.getSide(3) + " getPerimeter " + triangle.getPerimeter());

    }
}
