package homework_1.lesson_2;

class Lorry extends Car implements Moveable {
    public void move(){
        System.out.println("Car is moving");
    }
    public void stop(){
        System.out.println("Car is stop");
    }

    @Override
    void open() {
    }
}
