package scenariobased;

public class EduQuiz {
static int calculateScore(String[] correct,String[] student) {
	int score =0;
	for(int i=0;i<correct.length;i++) {
		if(student[i].equalsIgnoreCase(correct[i])) {
			score++;
		}
	}
	return score;
}
public static void main(String[] args) {
	String[] correctAnswers = {
            "A", "C", "B", "D", "A",
            "B", "C", "D", "A", "C"};
	 String[] studentAnswers = {
             "A", "c", "B", "D", "b",
             "B", "C", "a", "A", "C"
     };
	
	for (int i = 0; i < correctAnswers.length; i++) {
        if (studentAnswers[i].equalsIgnoreCase(correctAnswers[i])) {
            System.out.println("Question " + (i + 1) + ": Correct");
        } else {
            System.out.println("Question " + (i + 1) + ": Incorrect");
        }
    }

    // Calculate score
    int score = calculateScore(correctAnswers, studentAnswers);
    double percentage = (score / 10.0) * 100;

    System.out.println("\nTotal Score: " + score + "/10");
    System.out.println("Percentage: " + percentage + "%");

    // Pass / Fail
    if (percentage >= 50) {
        System.out.println("Result: PASS");
    } else {
        System.out.println("Result: FAIL");
    }
}

}

