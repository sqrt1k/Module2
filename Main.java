//Класс MyHashSet основан на работе стандартного HashSet и наследуется от AbstractSet. Работает идентично оригинальному классу
//Класс MyArrayList это моя попытка сделать упрощённый ArrayList на основе массивов. И реализующим методы add, addAll, remove, get
import java.util.*;

class MyHashSet extends AbstractSet<Integer>{
    private final HashSet<Integer> hashset = new HashSet<>();
    private final int max;
    private final int min;
    public MyHashSet(int min, int max){
        this.min = min;
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    public int getMin(){
        return min;
    }

    @Override
    public Iterator<Integer> iterator() {
        return hashset.iterator();
    }

    @Override
    public int size() {
        return hashset.size();
    }
    private void checkAddArgument(Integer x) {
        if (x == null) throw new IllegalArgumentException("null нельзя положить в коллекцию");
        if (x < min) throw new IllegalArgumentException("Меньше минимального значения");
        if (max < x) throw new IllegalArgumentException("Больше максимального значения");
    }
    @Override
    public boolean add(Integer a) {
        checkAddArgument(a);
        return hashset.add(a);
    }
    @Override
    public boolean remove(Object o) {
        return hashset.remove(o);
    }
}
class MyArrayList{
    private int[] mas = new int[10];
    private int elements = 0;

    MyArrayList() {
    }

    void add(Integer x) {
        mas[elements] = x;
        elements += 1;
    }

    int get(int i) {
        return mas[i];
    }

    void remove(int x) {
        for (int i = x; i < elements; i++) {
            mas[i] = mas[i + 1];
        }
        elements -= 1;
    }

    public String toString() {
        return Arrays.toString(mas);
    }

    void addAll(int[] x) {
        for (int i = 0; i < x.length; i++) {
            mas[elements] = x[i];
            elements += 1;
        }
    }

}
public class Main {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet(1, 10);
        myHashSet.add(5);
        myHashSet.add(8);
        System.out.println(myHashSet); //Добавляем в коллекцию элементы 5 и 8 и выводим на экран
        myHashSet.remove(8);
        System.out.println(myHashSet);//Убираем из коллекции элемент 8 и выводим коллекцию на экран

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