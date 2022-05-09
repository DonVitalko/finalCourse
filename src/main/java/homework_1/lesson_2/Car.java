package homework_1.lesson_2;

abstract class Car {
    private IEngine engine;
    //Заменил класс двигателя на интерфейс для дальнейшего расширения ПО без глобального изменения кода.
    private String color;
    private String name;

    abstract void open();

    public IEngine getEngine() {
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
}