package method;
import java.util.Scanner;
public class StudentVoteChecker {
    public boolean canStudentVote(int age) {
        if (age < 0) {
            return false;
        } else {
            return age >= 18;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentVoteChecker checker = new StudentVoteChecker();

        int[] ages = new int[10];

        for (int i = 0; i < 10; i++) {
            System.out.print("Enter age of student " + (i + 1) + ": ");
            ages[i] = sc.nextInt();
        }

        System.out.println("Voting Eligibility Results:");
        for (int i = 0; i < 10; i++) {
            boolean result = checker.canStudentVote(ages[i]);
            String message = result ? "can vote." : "cannot vote.";
            System.out.println("Student " + (i + 1) + " with age " + ages[i] + " " + message);
        }
        sc.close();
    }
}


