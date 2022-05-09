package homework_1.lesson_2;

public class Engine implements IEngine{
    //сделал имплементацию от интерфейса для дальнейшего масштабирования ПО.

    //перенес метод старт из класса car в класс Engine, так
    public void start() {
        System.out.println("Car starting");
    }
}
