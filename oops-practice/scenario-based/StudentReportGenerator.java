package scenariobased;
import java.util.ArrayList;
import java.util.List;

class InvalidMarkException extends Exception {
    public InvalidMarkException(String message) {
        super(message);
    }
}

// Student class
class Student {
    String name;
    String[] subjects;
    int[] marks;

    Student(String name, String[] subjects, int[] marks)
            throws InvalidMarkException {

        this.name = name;
        this.subjects = subjects;
        this.marks = marks;

        validateMarks();
    }

    // Validate marks
    private void validateMarks() throws InvalidMarkException {
        for (int mark : marks) {
            if (mark < 0 || mark > 100) {
                throw new InvalidMarkException(
                        "Marks must be between 0 and 100");
            }
        }
    }

    // Calculate average
    double calculateAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return sum / (double) marks.length;
    }

    // Assign grade
    String assignGrade() {
        double avg = calculateAverage();

        if (avg >= 80) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 40) return "C";
        else return "Fail";
    }

    // Display report card
    void displayReport() {
        System.out.println("\n----------------------------");
        System.out.println("Student Name: " + name);
        System.out.println("Subjects & Marks:");

        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + " : " + marks[i]);
        }

        System.out.printf("Average Marks: %.2f%n", calculateAverage());
        System.out.println("Grade: " + assignGrade());
        System.out.println("----------------------------");
    }
}

// Main class
public class StudentReportGenerator {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        String[] subjects = {"Maths", "Science", "English"};

        try {
            students.add(new Student(
                    "Ansh",
                    subjects,
                    new int[]{85, 78, 90}
            ));

            students.add(new Student(
                    "Riya",
                    subjects,
                    new int[]{65, 70, 60}
            ));

            students.add(new Student(
                    "Invalid",
                    subjects,
                    new int[]{110, 60, 50} // invalid marks
            ));

        } catch (InvalidMarkException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display reports
        for (Student s : students) {
            s.displayReport();
        }
    }
}

