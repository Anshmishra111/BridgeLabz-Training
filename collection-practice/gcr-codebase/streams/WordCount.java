package streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {

    public static void main(String[] args) {

        String fileName = "input.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("input.txt not found in project folder!");
            return;
        }

        Map<String, Integer> wordCount = new HashMap<>();

        // -------- Read File --------
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                line = line.toLowerCase().replaceAll("[^a-z ]", "");
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word,
                                wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error while reading file.");
            e.printStackTrace();
        }

        // -------- Sort by Frequency --------
        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(wordCount.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        // -------- Display Top 5 --------
        System.out.println("\nTop 5 Most Frequent Words:");
        int count = 0;

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            count++;
            if (count == 5) break;
        }
    }
}


























