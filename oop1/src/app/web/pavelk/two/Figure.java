package app.web.pavelk.two;

import java.util.UUID;

public abstract class Figure {
    private String name;
    private double area;
    private double perimeter;

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public String getName() {
        if (this.name == null)
            this.setName(UUID.randomUUID().toString());
        return name;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }


}
