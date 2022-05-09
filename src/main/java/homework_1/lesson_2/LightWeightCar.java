package homework_1.lesson_2;

class LightWeightCar extends Car implements Moveable {
    @Override
    void open() {
        System.out.println("Car is open");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

