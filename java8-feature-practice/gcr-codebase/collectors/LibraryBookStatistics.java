package collectors;

import java.util.*;
import java.util.stream.*;

// Book class
class Book {
    String title;
    String genre;
    int pages;

    Book(String title, String genre, int pages) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }
}

// Main class
public class LibraryBookStatistics {
    public static void main(String[] args) {

        List<Book> books = Arrays.asList(
            new Book("Book1", "Fiction", 300),
            new Book("Book2", "Fiction", 250),
            new Book("Book3", "Science", 400),
            new Book("Book4", "Science", 350),
            new Book("Book5", "History", 200)
        );

        Map<String, IntSummaryStatistics> stats =
            books.stream()
                .collect(Collectors.groupingBy(
                    b -> b.genre,
                    Collectors.summarizingInt(b -> b.pages)
                ));

        stats.forEach((genre, s) -> {
            System.out.println("Genre: " + genre);
            System.out.println("Total Pages: " + s.getSum());
            System.out.println("Average Pages: " + s.getAverage());
            System.out.println("Max Pages: " + s.getMax());
            System.out.println();
        });
    }
}
