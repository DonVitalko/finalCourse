package homework_2;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("14");
        myArrayList.add("12");
        myArrayList.add("11");
        myArrayList.add("15");
        myArrayList.add("1");
        myArrayList.add("4");

        System.out.println(myArrayList);


        MyLinkedList<String> list = new MyLinkedList();
        list.insert("5");
        list.insert("2");
        list.insert("18");
        list.insert("10");

        System.out.println(list);


    }



}
