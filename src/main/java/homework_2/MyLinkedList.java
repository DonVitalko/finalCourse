package homework_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
    private Node first;
    private Node last;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class Iter implements Iterator<E>{
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    public void insertFirst(E item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        E temp = first.getValue();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return temp;
    }

    public void insertLast(E item) {
        Node newNode = new Node(last, item, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        E temp = last.getValue();
        if (last.getPrev() == null) {
            first = null;
        } else {
            last.getPrev().setNext(null);
        }
        last = last.getPrev();
        size--;
        return temp;
    }


    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return first.getValue();
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return last.getValue();
    }


    public void insert(E item) {
        insertFirst(item);
    }

    public void insert(int index, E item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index: " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }

        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        Node newNode = new Node(current, item, current.getNext());
        current.setNext(newNode);
        newNode.getNext().setPrev(newNode);
        size++;
    }

    public boolean remove(E item) {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        if (first.getValue().equals(item)) {
            removeFirst();
            return true;
        }

        Node current = first;
        while (current != null && !current.getValue().equals(item)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }

        if (current == last) {
            removeLast();
            return true;
        }

        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;
        return true;
    }

    public boolean contains(E item) {
        return index(item) > -1;
    }

    public int indexOf(E item) {
        return index(item);
    }

    private int index(E item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(item)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    private class Node {
        Node prev;
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node prev, E value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = first;
        while (current != null) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        return sb.toString();
    }
}