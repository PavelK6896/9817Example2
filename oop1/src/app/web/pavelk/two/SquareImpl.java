package app.web.pavelk.two;

public class SquareImpl extends Figure implements Square {

    double side;

    public SquareImpl(double side) {
        this.side = side;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getArea() {
        if (super.getArea() == 0) {
            super.setArea(Math.pow(side, 2));
        }
        return super.getArea();
    }

    @Override
    public double getPerimeter() {
        if (super.getPerimeter() == 0)
            super.setPerimeter(side * 4);
        return super.getPerimeter();
    }

    @Override
    public double getSide() {
        return this.side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
