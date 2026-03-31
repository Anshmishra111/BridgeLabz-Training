package scenariobased;
class ExamTimeExpiredException extends Exception{
	public ExamTimeExpiredException(String message) {
		super(message);
	}
}
interface EvaluationStrategy {
	int evaluate(Question question,String answer);
}
class ObjectiveEvaluation implements EvaluationStrategy{
	public int evaluate(Question question,String answer) {
		return question.getCorrectAnswer().equalsIgnoreCase(answer)?1:0;
	}
}
class DescriptiveEvaluation implements EvaluationStrategy{
	public int evaluate(Question question,String answer) {
		return answer.length()>10?2:0;
	}
}
class Question{
	private int id;
	private String text;
	private String correctAnswer;
	
	public Question(int id,String text,String correctAnswer) {
		this.id=id;
		this.text=text;
		this.correctAnswer=correctAnswer;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void displayQuestion() {
		System.out.println(id + ". " + text);
	}
}
class Studentss{
	private int studentId;
	private String name;
	public Studentss(int studentId,String name) {
		this.studentId=studentId;
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
class Exam{
	private Question[] questions=new Question[3];
	private int qCount=0;
	private int totalScore=0;
	private long startTime;
	public void createExam() {
		questions[qCount++] = new Question(1, "Java is platform independent? (yes/no)", "yes");
        questions[qCount++] = new Question(2, "What is OOP?", "object oriented programming");
        questions[qCount++] = new Question(3, "What is JVM?", "java virtual machine");
        startTime=System.currentTimeMillis();
        System.out.println("Exam created successfully.\n");
	}
	public void submitAnswer(int qIndex,String answer,EvaluationStrategy strategy)
			throws ExamTimeExpiredException {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) > 60000) { // 1 minute limit
            throw new ExamTimeExpiredException("Exam time expired!");
        }

        totalScore += strategy.evaluate(questions[qIndex], answer);
    }

    // Display Result
    public void showResult(Studentss student) {
        System.out.println("\n--- Result ---");
        System.out.println("Student : " + student.getName());
        System.out.println("Score   : " + totalScore);
    }

    public void showQuestions() {
        for (int i = 0; i < qCount; i++) {
            questions[i].displayQuestion();
        }
    }
}

// Main Class
public class OnlineExaminationSystem {
    public static void main(String[] args) {

        Studentss student = new Studentss(1, "Ansh");
        Exam exam = new Exam();

        exam.createExam();
        exam.showQuestions();

        try {
            // Polymorphism
            exam.submitAnswer(0, "yes", new ObjectiveEvaluation());
            exam.submitAnswer(1, "Object Oriented Programming", new DescriptiveEvaluation());
            exam.submitAnswer(2, "Java Virtual Machine", new DescriptiveEvaluation());

        } catch (ExamTimeExpiredException e) {
            System.out.println(e.getMessage());
        }

        exam.showResult(student);
    }
}



