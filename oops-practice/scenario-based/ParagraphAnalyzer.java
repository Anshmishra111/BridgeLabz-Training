package scenariobased;

public class ParagraphAnalyzer {
	    // Method to analyze paragraph
	    public static void analyzeParagraph(String paragraph, String targetWord, String replacementWord) {

	        // Handle empty or space-only paragraph
	        if (paragraph == null || paragraph.trim().isEmpty()) {
	            System.out.println("The paragraph is empty or contains only spaces.");
	            return;
	        }

	        // Step 1: Trim and normalize spaces
	        paragraph = paragraph.trim().replaceAll("\\s+", " ");

	        // Step 2: Split into words
	        String[] words = paragraph.split(" ");

	        // Count words
	        int wordCount = words.length;

	        // Step 3: Find longest word
	        String longestWord = "";
	        for (String word : words) {
	            // Remove punctuation for accurate comparison
	            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
	            if (cleanWord.length() > longestWord.length()) {
	                longestWord = cleanWord;
	            }
	        }

	        // Step 4: Replace target word (case-insensitive)
	        String replacedParagraph = paragraph.replaceAll(
	                "(?i)\\b" + targetWord + "\\b",
	                replacementWord
	        );

	        // Display results
	        System.out.println("Word Count: " + wordCount);
	        System.out.println("Longest Word: " + longestWord);
	        System.out.println("Updated Paragraph:");
	        System.out.println(replacedParagraph);
	    }

	    public static void main(String[] args) {

	        String paragraph =
	                "Java is a powerful language. Java is widely used in enterprise applications.";

	        String targetWord = "java";
	        String replacementWord = "Python";

	        analyzeParagraph(paragraph, targetWord, replacementWord);
	    }
	}


