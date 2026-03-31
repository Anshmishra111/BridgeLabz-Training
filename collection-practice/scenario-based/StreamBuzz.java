package scenario_based;

import java.util.*;

//Data class
class CreatorStats {
 String creatorName;
 double[] weeklyLikes = new double[4];

 CreatorStats(String name, double[] likes) {
     this.creatorName = name;
     this.weeklyLikes = likes;
 }
}

public class StreamBuzz {

 public static List<CreatorStats> engagementBoard = new ArrayList<>();

 // Register creator
 public static void registerCreator(CreatorStats c) {
     engagementBoard.add(c);
     System.out.println("Creator registered successfully");
 }

 // Top post counts
 public static Map<String, Integer> getTopPostCounts(
         List<CreatorStats> list, double threshold) {

     Map<String, Integer> map = new LinkedHashMap<>();

     for (CreatorStats c : list) {
         int count = 0;
         for (double likes : c.weeklyLikes) {
             if (likes >= threshold) count++;
         }
         if (count > 0) map.put(c.creatorName, count);
     }
     return map;
 }

 // Average likes
 public static double calculateAverageLikes() {
     double sum = 0;
     int count = 0;

     for (CreatorStats c : engagementBoard) {
         for (double likes : c.weeklyLikes) {
             sum += likes;
             count++;
         }
     }
     return count == 0 ? 0 : sum / count;
 }

 // Main
 public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);

     while (true) {
         System.out.println("1. Register Creator");
         System.out.println("2. Show Top Posts");
         System.out.println("3. Calculate Average Likes");
         System.out.println("4. Exit");
         System.out.println("Enter your choice:");

         int choice = sc.nextInt();
         sc.nextLine();

         if (choice == 1) {
             System.out.println("Enter Creator Name:");
             String name = sc.nextLine();

             double[] likes = new double[4];
             System.out.println("Enter weekly likes (Week 1 to 4):");
             for (int i = 0; i < 4; i++)
                 likes[i] = sc.nextDouble();

             registerCreator(new CreatorStats(name, likes));

         } else if (choice == 2) {
             System.out.println("Enter like threshold:");
             double t = sc.nextDouble();

             Map<String, Integer> result =
                     getTopPostCounts(engagementBoard, t);

             if (result.isEmpty())
                 System.out.println("No top-performing posts this week");
             else
                 result.forEach((k, v) -> System.out.println(k + " - " + v));

         } else if (choice == 3) {
             System.out.println(
                     "Overall average weekly likes: " +
                     (int) calculateAverageLikes());

         } else if (choice == 4) {
             System.out.println(
                     "Logging off - Keep Creating with StreamBuzz!");
             break;
         }
     }
 }
}
