package scenariobased;
import java.util.ArrayList;
import java.util.List;

class InvalidQuizSubmissionException extends Exception{
	public InvalidQuizSubmissionException(String message) {
		super(message);
	}
}
public class OnlineQuizPlatform {
	static List<Integer> userScores = new ArrayList<>();

    // Method to calculate score
    static int calculateScore(String[] correct, String[] user)
            throws InvalidQuizSubmissionException {

        if (correct.length != user.length) {
            throw new InvalidQuizSubmissionException("Answer count mismatch with quiz questions");
        }

        int score = 0;
        for (int i = 0; i < correct.length; i++) {
            if (correct[i].equalsIgnoreCase(user[i])) {
                score++;
            }
        }
        return score;
    }

    // Method to calculate grade
    static String getGrade(int score, int total) {
        double percentage = (score * 100.0) / total;

        if (percentage >= 80) return "A";
        else if (percentage >= 60) return "B";
        else if (percentage >= 40) return "C";
        else return "Fail";
    }

    // Method to process quiz
    static void processQuiz(String[] correct, String[] user) {
        try {
            int score = calculateScore(correct, user);
            userScores.add(score);

            String grade = getGrade(score, correct.length);

            System.out.println("Score: " + score + "/" + correct.length);
            System.out.println("Grade: " + grade);

        } catch (InvalidQuizSubmissionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        String[] correctAnswers = {
                "A", "B", "C", "D", "A"
        };

        String[] user1Answers = {
                "A", "B", "C", "D", "A"
        };

        String[] user2Answers = {
                "A", "C", "C", "D", "B"
        };

        String[] invalidUserAnswers = {
                "A", "B", "C"
        };

        // Process multiple users
        processQuiz(correctAnswers, user1Answers);
        processQuiz(correctAnswers, user2Answers);
        processQuiz(correctAnswers, invalidUserAnswers);

        // Display all scores
        System.out.println("\nAll User Scores:");
        for (int score : userScores) {
            System.out.println(score);
        }
    }
}


