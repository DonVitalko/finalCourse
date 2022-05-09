package homework_1;

import homework_1.lesson_1.Person;
import homework_1.lesson_3.Shape;
import homework_1.lesson_3.Square;

public class Main {
    public static void main(String[] args) {
         Person person = Person.newBuilder().firstName("Albert").lastName("Wagner").build();
        System.out.println(person);

        Shape square = new Square(2,3);
        System.out.println(square.squareArea());
        System.out.println(square.perimeter());
    }
}
