package app.web.pavelk.three;

import java.time.Instant;
import java.util.Objects;

//ошибки
//1 implements interface
//2 английские слова  Movable Движимый Stoppable Останавливаемый
//3 @Override open
//улучшения
//1 конструктор в abstract class Car с проверкой на нул
//2 конструктор в class Engine с дефолтными значениями

public class Main {

    public static void main(String[] args) {

        Lorry lorry1 = new Lorry(new Engine(null, null, null, null), "red", "bu", 1);
        System.out.println(lorry1);
        try {
            Lorry lorry2 = new Lorry(new Engine(null, null, null, null), "red", "bu", null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException " + e.getMessage());
        }

        LightWeightCar lightWeightCar1 = new LightWeightCar(new Engine(null, null, null, null), "red", "bu");
        System.out.println(lightWeightCar1);
        try {
            LightWeightCar lightWeightCar2 = new LightWeightCar(null, "red", "bu");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException " + e.getMessage());
        }

    }
}

class Engine {
    Long id;
    Integer age;
    String name;
    Instant date;

    public Engine(Long id, Integer age, String name, Instant date) {
        this.id = Objects.requireNonNullElse(id, 1l);
        this.age = Objects.requireNonNullElse(age, 0);
        this.name = Objects.requireNonNullElse(name, "");
        this.date = Objects.requireNonNullElse(date, Instant.now());
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}

interface Movable { //Движимый
    void move();
}

interface Stoppable {//Останавливаемый

    void stop();
}

abstract class Car {
    public Engine engine;
    private String color;
    private String name;

    public Car(Engine engine, String color, String name) {
        this.engine = Objects.requireNonNull(engine, "no null engine");
        this.color = Objects.requireNonNull(color, "no null color");
        this.name = Objects.requireNonNull(name, "no null name");
    }

    protected void start() {
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class LightWeightCar extends Car implements Movable {

    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    } //Свет Вес

    @Override
    void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

}

//Грузовик
class Lorry extends Car implements Movable, Stoppable {

    private Integer weight;

    public Lorry(Engine engine, String color, String name, Integer weight) {
        super(engine, color, name);
        this.weight = Objects.requireNonNull(weight, "no null weight");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }

    @Override
    void open() {

    }
}
