package homework_1.lesson_3;

public class Square extends Shape{
    int x;
    int y;
    int squareArea;
    int perimeter;

    public Square(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int squareArea() {
        return this.squareArea = x * y;
    }

    @Override
    public int perimeter() {
        return this.perimeter = x * 2 + y * 2;
    }
}
