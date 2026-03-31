package stream_api;

import java.util.*;
import java.util.stream.*;

class Movie {
    String name;
    double rating;
    int year;

    Movie(String name, double rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }
}

public class Top5TrendingMovies {
    public static void main(String[] args) {

        List<Movie> movies = Arrays.asList(
            new Movie("Movie A", 8.9, 2024),
            new Movie("Movie B", 9.1, 2023),
            new Movie("Movie C", 8.5, 2025),
            new Movie("Movie D", 9.3, 2022),
            new Movie("Movie E", 8.8, 2025),
            new Movie("Movie F", 9.0, 2024),
            new Movie("Movie G", 8.2, 2023)
        );

        movies.stream()
            .filter(m -> m.rating >= 8.5)
            .sorted(Comparator
                .comparing((Movie m) -> m.rating).reversed()
                .thenComparing(m -> m.year).reversed()
            )
            .limit(5)
            .forEach(m ->
                System.out.println(m.name + " | Rating: " +
                                   m.rating + " | Year: " + m.year)
            );
    }
}
