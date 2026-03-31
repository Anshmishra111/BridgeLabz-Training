package scenariobased;

public class SentenceFormatter {
	    // Method to format the paragraph
	    public static String formatSentence(String input) {

	        // Step 1: Trim extra spaces
	        input = input.trim();

	        // Step 2: Replace multiple spaces with single space
	        input = input.replaceAll("\\s+", " ");

	        StringBuilder result = new StringBuilder();
	        boolean capitalizeNext = true;

	        for (int i = 0; i < input.length(); i++) {
	            char ch = input.charAt(i);

	            // Capitalize first letter of sentence
	            if (capitalizeNext && Character.isLetter(ch)) {
	                result.append(Character.toUpperCase(ch));
	                capitalizeNext = false;
	            } else {
	                result.append(ch);
	            }

	            // After punctuation, ensure next letter is capitalized
	            if (ch == '.' || ch == '?' || ch == '!') {
	                capitalizeNext = true;

	                // Add a space if next character is not space
	                if (i + 1 < input.length() && input.charAt(i + 1) != ' ') {
	                    result.append(' ');
	                }
	            }
	        }

	        return result.toString();
	    }

	    public static void main(String[] args) {

	        String paragraph =
	                "   hello   world.this is a test!how are   you?i am fine.   ";

	        String formattedText = formatSentence(paragraph);

	        System.out.println("Formatted Text:");
	        System.out.println(formattedText);
	    }
	}


