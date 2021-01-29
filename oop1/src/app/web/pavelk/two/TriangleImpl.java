package app.web.pavelk.two;

public class TriangleImpl extends Figure implements Triangle {

    double side1;
    double side2;
    double side3;

    public TriangleImpl(double side1) {
        this.side1 = side1;
        this.side2 = side1;
        this.side3 = side1;
    }

    public TriangleImpl(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getArea() {
        if (super.getArea() == 0) {
            double p2 = (side1 + side2 + side3) / 2;
            super.setArea(Math.sqrt(Math.abs(p2 * (p2 - side1) * (p2 - side2) * (p2 - side3))));
        }
        return super.getArea();
    }

    @Override
    public double getPerimeter() {
        if (super.getPerimeter() == 0)
            super.setPerimeter(side1 + side2 + side3);
        return super.getPerimeter();
    }

    @Override
    public double getSide(int num) {
        switch (num) {
            case 1:
                return side1;
            case 2:
                return side2;
            case 3:
                return side3;
            default:
                throw new NumberFormatException("error num");
        }
    }

    public void setSide(double side, int num) {

        switch (num) {
            case 1:
                this.side1 = side;
                break;
            case 2:
                this.side2 = side;
                break;
            case 3:
                this.side3 = side;
                break;
            default:
                throw new NumberFormatException("error num");

        }
    }

}
