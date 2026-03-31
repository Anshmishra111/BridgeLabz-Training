package scenario_based;
import java.util.*;

//Custom Exception
class InvalidTimeFormatException extends Exception {
 public InvalidTimeFormatException(String message) {
     super(message);
 }
}

public class MovieSchedulerManager {

 private List<String> titles = new ArrayList<>();
 private List<String> times = new ArrayList<>();

 // Add movie
 public void addMovie(String title, String time) throws InvalidTimeFormatException {
     if (!isValidTime(time)) {
         throw new InvalidTimeFormatException("Invalid time format: " + time);
     }
     titles.add(title);
     times.add(time);
 }

 // Search movie using contains()
 public void searchMovie(String keyword) {
     try {
         for (int i = 0; i < titles.size(); i++) {
             if (titles.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                 System.out.println(
                     String.format("Found: %s at %s", titles.get(i), times.get(i))
                 );
                 return;
             }
         }
         System.out.println("Movie not found");
     } catch (IndexOutOfBoundsException e) {
         System.out.println("Search error: Invalid index access");
     }
 }

 // Display all movies
 public void displayAllMovies() {
     System.out.println("---- Movie Schedule ----");
     for (int i = 0; i < titles.size(); i++) {
         System.out.println(
             String.format("%d. %s - %s", i + 1, titles.get(i), times.get(i))
         );
     }
 }

 // Convert list to array for report
 public void generateReport() {
     String[] movieArray = titles.toArray(new String[0]);
     System.out.println("---- Movie Report ----");
     for (String movie : movieArray) {
         System.out.println(movie);
     }
 }

 // Time validation (HH:MM)
 private boolean isValidTime(String time) {
     try {
         String[] parts = time.split(":");
         int hh = Integer.parseInt(parts[0]);
         int mm = Integer.parseInt(parts[1]);
         return hh >= 0 && hh < 24 && mm >= 0 && mm < 60;
     } catch (Exception e) {
         return false;
     }
 }

 // Main method
 public static void main(String[] args) {

	 MovieSchedulerManager cinema = new MovieSchedulerManager();

     try {
         cinema.addMovie("Inception", "18:30");
         cinema.addMovie("Interstellar", "21:15");
         cinema.addMovie("Avatar", "25:99"); // invalid time
     } catch (InvalidTimeFormatException e) {
         System.out.println(e.getMessage());
     }

     cinema.displayAllMovies();
     cinema.searchMovie("Inter");
     cinema.generateReport();
 }
}
