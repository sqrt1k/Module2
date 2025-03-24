import org.w3c.dom.Node;

import java.util.*;

class MyHashSet {
    private static final int SIZE = 16;
    private static final float LOAD = 0.8f;

    private Node[] buckets;
    private int size;

    public MyHashSet(int min, int max) {
        buckets = new Node[SIZE];
        size = 0;
    }

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }


    public void add(int value) {
        if (contains(value)) {
            return;
        }
        int index = getIndex(value);
        Node newNode = new Node(value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size += 1;
        if (size > LOAD * buckets.length) {
            rehash();
        }
    }

    public void remove(int value) {
        int index = getIndex(value);
        Node head = buckets[index];
        Node prev = null;

        while (head != null) {
            if (head.value == value) {
                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size -= 1;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    private int getIndex(int value) {
        return hash(value) % (buckets.length - 1);
    }

    private void rehash() {
        Node[] oldBuckets = buckets;
        buckets = new Node[2 * oldBuckets.length];
        size = 0;

        for (Node head : oldBuckets) {
            while (head != null) {
                add(head.value);
                head = head.next;
            }
        }
    }

    private int hash(int value) {
        int hash = value * buckets.length;
        return hash ^ (hash >>> 16);
    }

    public boolean contains(int value) {
        int index = getIndex(value);
        Node head = buckets[index];
        //Проверка на существование элемента
        while (head != null) {
            if (head.value == value) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}


class MyArrayList {
    private static final int SIZE = 10;

    private int[] mas = new int[SIZE];
    private int elements = 0;

    MyArrayList() {
    }

    public void add(int x) {
        mas[elements] = x;
        elements += 1;
    }

    public int get(int i) {
        checkIndex(i);
        return mas[i];
    }

    public void remove(int x) {
        checkIndex(x);
        for (int i = x; i < elements; i++) {
            mas[i] = mas[i + 1];
        }
        elements -= 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(mas, elements));
    }

    public void addAll(int[] x) {
        for (int i = 0; i < x.length; i++) {
            mas[elements] = x[i];
            elements += 1;
        }
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= elements) {
            throw new IndexOutOfBoundsException("Index: " + i + "elements:" + elements);
        }
    }

}

public class Main {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet(1, 10);
        myHashSet.add(5);
        myHashSet.add(8);
        System.out.println(myHashSet.contains(8));
        myHashSet.remove(8);
        System.out.println(myHashSet.contains(8));
        System.out.println(myHashSet.contains(5));

        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list.toString());
        list.add(7);
        System.out.println(list.toString());
        list.remove(3);
        System.out.println(list.toString());
        System.out.println(list.get(3));
        list.addAll(new int[]{8, 9, 10});
        System.out.println(list.toString());
        /////////////////////
        System.out.println("Задание 2");
        /////////////////////
        List<Student> students = Arrays.asList(
                new Student("Александр", Arrays.asList(
                        new Book("Книга 1", 1998, 200),
                        new Book("Книга 2", 1995, 158),
                        new Book("Книга 3", 2001, 1000),
                        new Book("Книга 4", 2015, 250),
                        new Book("Книга 5", 1994, 100)
                )),
                new Student("Павел", Arrays.asList(
                        new Book("Гарри Поттер 1", 1997, 500),
                        new Book("Гарри Поттер 2", 1998, 565),
                        new Book("Гарри Поттер 3", 1999, 458),
                        new Book("Гарри Поттер 4", 2000, 478),
                        new Book("Гарри Поттер 5", 2001, 588)
                ))
        );
        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted((x1, x2) -> Integer.compare(x1.getLists(), x2.getLists()))
                .filter(book -> book.getYear() > 2000)
                .distinct()
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска книги:" + year),
                        () -> System.out.println("Книга не найдена")
                );
    }
}