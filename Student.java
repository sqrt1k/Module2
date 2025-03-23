import java.util.List;

class Book{
    String name;
    int year;
    int lists;
    Book(String name, int year, int lists){
        this.name = name;
        this.year = year;
        this.lists = lists;
    }
    public String getName(){
        return name;
    }
    public int getYear(){
        return year;
    }
    public int getLists(){
        return lists;
    }
    public String toString(){
        return "Название: {" + name+ "} Год выхода: {"+year+"} Листы: {"+lists+"}";
    }
}

public class Student {
    String name;
    private List<Book> books;
    Student(String name, List<Book> books){
        this.name = name;
        this.books = books;
    }
    public String getName(){
        return name;
    }
    public List<Book> getBooks(){
        return books;
    }
    public String toString(){
        return "Имя студента: "+name+" Список книг:"+books;
    }
}

