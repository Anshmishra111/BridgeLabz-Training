package design_patterns;
import java.util.ArrayList;
import java.util.List;

//SMART UNIVERSITY LIBRARY MANAGEMENT SYSTEM
//Patterns Used:
//1. Singleton  - LibraryCatalog
//2. Factory    - UserFactory
//3. Observer   - Notifications to users
//4. Builder    - Book creation

// OBSERVER
interface Observer {
    void update(String message);
}

// USER (FACTORY PRODUCT)
interface User extends Observer {
    void showRole();
}

//STUDENT
class Student implements User {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void showRole() {
        System.out.println(name + " is a Student");
    }

    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}

//FACULTY
class Faculty implements User {
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public void showRole() {
        System.out.println(name + " is a Faculty");
    }

    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}

//LIBRARIAN
class Librarian implements User {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void showRole() {
        System.out.println(name + " is a Librarian");
    }

    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}

//FACTORY
class UserFactory {
    public static User createUser(String type, String name) {
        switch (type.toLowerCase()) {
            case "student":
                return new Student(name);
            case "faculty":
                return new Faculty(name);
            case "librarian":
                return new Librarian(name);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}

//BUILDER
@SuppressWarnings("unused")
class Book {
    private String title;
    private String author;
	private String edition;
    private String genre;

    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.edition = builder.edition;
        this.genre = builder.genre;
    }

    public String getTitle() {
        return title;
    }

//    BOOK BUILDER
    public static class BookBuilder {
        private String title;
        private String author;
        private String edition;
        private String genre;

        public BookBuilder(String title) {
            this.title = title;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder edition(String edition) {
            this.edition = edition;
            return this;
        }

        public BookBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}

//SINGLETON + SUBJECT
class LibraryCatalog {
    private static LibraryCatalog instance;

    private List<Book> books = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private LibraryCatalog() {
    }

    public static synchronized LibraryCatalog getInstance() {
        if (instance == null) {
            instance = new LibraryCatalog();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers(book.getTitle());
    }

    private void notifyObservers(String bookTitle) {
        for (Observer o : observers) {
            o.update("New book added: " + bookTitle);
        }
    }
}

//MAIN APPLICATION
public class LibraryManagementSystem {
    public static void main(String[] args) {

        // Singleton
        LibraryCatalog catalog = LibraryCatalog.getInstance();

        // Factory
        User student = UserFactory.createUser("student", "Alice");
        User faculty = UserFactory.createUser("faculty", "Dr. Bob");
        User librarian = UserFactory.createUser("librarian", "Mr. John");

        student.showRole();
        faculty.showRole();
        librarian.showRole();

        // Observer registration
        catalog.addObserver(student);
        catalog.addObserver(faculty);

        // Builder
        Book book1 = new Book.BookBuilder("Design Patterns")
                .author("Gang of Four")
                .edition("2nd")
                .genre("Software Engineering")
                .build();

        Book book2 = new Book.BookBuilder("Data Structures")
                .author("Cormen")
                .build();

        // Adding books (triggers notification)
        catalog.addBook(book1);
        catalog.addBook(book2);
    }
}