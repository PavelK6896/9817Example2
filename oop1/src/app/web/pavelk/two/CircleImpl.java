package app.web.pavelk.two;


public class CircleImpl extends Figure implements Circle {

    double radius;

    public CircleImpl(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getArea() {
        if (super.getArea() == 0) {
            super.setArea(Math.PI * Math.sqrt(radius));
        }
        return super.getArea();
    }

    @Override
    public double getPerimeter() {
        if (super.getPerimeter() == 0)
            super.setPerimeter(2 * Math.PI * radius);
        return super.getPerimeter();
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

}
