package linear_and_binary_search;

public class LinearSearchWordInSentences {

    // Method to search for a word in sentences
    static String searchWord(String[] sentences, String word) {

        // Step 1: Traverse each sentence
        for (String sentence : sentences) {

            // Step 2: Check if sentence contains the word
            if (sentence.contains(word)) {
                return sentence; // Step 3: Return the sentence
            }
        }

        // Step 4: If word not found
        return "Not Found";
    }

    public static void main(String[] args) {

        String[] sentences = {
            "Java is a programming language",
            "Data structures are important",
            "Linear search is simple",
            "Algorithms improve efficiency"
        };

        String word = "search";

        String result = searchWord(sentences, word);

        System.out.println("Result: " + result);
    }
}
