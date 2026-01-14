package scenariobased;
class CourseLimitExceededException extends Exception{
	public CourseLimitExceededException(String message) {
		super(message);
	}
}
//Abstarction
interface RegistartionService{
	void enrollCourse(String course) throws CourseLimitExceededException;
	void dropCourse(String course);
	void viewCoursesAndGrades();
}
//parent class(inheritance)
class Person{
	protected int id;
	protected String name;
	
	public Person(int id,String name) {
		this.id=id;
		this.name=name;
	}
}
//student class(encapsulation)
class Students extends Person implements RegistartionService{
	private String[] courses=new String[3];
	private int courseCount=0;
	private int[] grades=new int[3];
	public Students(int id,String name) {
		super(id,name);
	}
	//enroll course
	public void enrollCourse(String course) throws CourseLimitExceededException{
		if(courseCount>=3) {
			throw new CourseLimitExceededException("Maximum course limit reached!"); 
		}
		courses[courseCount]=course;
		grades[courseCount]=0;
		courseCount++;
		System.out.println("enrolled in course" + course);
	}
	//Drop Course
	public void dropCourse(String course) {
		for(int i=0;i<courseCount;i++) {
			if(courses[i].equals(course)) {
				System.out.println("Dropped course: " + course);
				courses[i]=courses[courseCount-1];
				grades[i]=grades[courseCount-1];
				courseCount--;
				return;
			}
		}
		System.out.println("Course not found");
	}
	//Assign grade
	public void assignGrade(String course,int grade) {
		for(int i=0;i<courseCount;i++) {
			if(courses[i].equals(course)) {
				grades[i]=grade;
				return;
			}
		}
	}
	//view courses anf grades
	public void viewCoursesAndGrades() {
		System.out.println("\nStudentId: " + id);
		System.out.print("Name  : " + name);
		System.out.println("Courses & Grades");
		if(courseCount==0) {
			System.out.print("No  Courses  enrolled");
			for(int i=0;i<courseCount;i++) {
				System.out.println(courses[i] + " Grade: " + grades[i]);
			}
		}
	}
	//main class
	
}
public class StudentCourseRegistrartionSystem {
	public static void main(String[] args) {

        Students student = new Students(1, "Ansh");

        try {
            student.enrollCourse("Java");
            student.enrollCourse("DBMS");
            student.enrollCourse("Operating Systems");
            student.enrollCourse("Cloud Computing"); // Exception
        } catch (CourseLimitExceededException e) {
            System.out.println(e.getMessage());
        }

        student.assignGrade("Java", 85);
        student.assignGrade("DBMS", 78);

        student.viewCoursesAndGrades();

        student.dropCourse("DBMS");
        student.viewCoursesAndGrades();
}
}
