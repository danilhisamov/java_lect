package Lecture3;

public class Car {
    private Door door;
    private Engine engine;
    private Wheel wheel;

    public Car() {
        this.door = new Door();
        this.wheel = new Wheel();
        this.engine = new Engine(wheel);
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public void startDrive() {
        System.out.println("Car drive start");
        door.open();
        door.close();

        engine.start();
        engine.speedUp(1000);
    }

    public void speedUp(int val) {
        System.out.println("Car speed up");
        engine.speedUp(val);
    }

    public void speedDown(int val) {
        System.out.println("Car speed down");
        this.engine.speedDown(val);
    }

    public void stopDrive() {
        System.out.println("Car drive stop");
        this.speedDown(1000);
        this.engine.stop();
        this.door.open();
        this.door.close();
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.startDrive();
        car.speedUp(500);
        car.speedDown(500);
        car.stopDrive();
    }
}

class Door {
    private boolean open;

    Door() {
        this.open = false;
    }

    void open() {
        this.open = true;
        System.out.println("Door is open");
    }

    void close() {
        this.open = false;
        System.out.println("Door is close");
    }
}

class Engine {
    private boolean started;
    private int turnover; // обороты

    private Wheel wheel;

    Engine(Wheel wheel) {
        this.wheel = wheel;
        this.started = false;
        this.turnover = 0;
    }

    void start() {
        this.started = true;
        this.turnover = 1500;

        System.out.println("Engine started");
    }

    void stop() {
        this.started = false;
        this.turnover = 0;
    }

    void speedUp(int val) {
        this.turnover += val;
        System.out.println("Speed up. Engine turnover: " + this.turnover);

        this.wheel.setSpeed(this.wheel.getSpeed() + val / 2);
    }

    void speedDown(int val) {
        this.turnover -= val;
        System.out.println("Speed down. Engine turnover: " + this.turnover);

        this.wheel.setSpeed(this.wheel.getSpeed() - val / 2);
    }
}

class Wheel {
    private int speed;

    Wheel() {
        this.speed = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;

        System.out.println("Wheel speed: " + this.speed);
    }
}
