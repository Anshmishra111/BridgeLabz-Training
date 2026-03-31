package generics;
import java.util.*;
abstract class CourseType{
	String courseName;
	CourseType(String courseName){
		this.courseName=courseName;
	}
	abstract void evaluate();
}
class ExamCourse extends CourseType{
	ExamCourse(String name){
		super(name);
	}
	void evaluate() {
		System.out.println(courseName + " evaluated by exam");
	}
}

class AssignmentCourse extends CourseType{
	AssignmentCourse(String name){
		super(name);
	}
	void evaluate() {
		System.out.println(courseName + " evaluated by assignments");
	}
}
class ResearchCourse extends CourseType{
	ResearchCourse(String name){
		super(name);
	}
	void evaluate() {
		System.out.println(courseName + " evaluated by Research");
	}
}
//generic course class
class Course<T extends CourseType>{
	private T course;
	Course(T course){
		this.course=course;
	}
	T getCourse() {
		return course;
	}
}
class CourseUtil{
	static void evaluateAll(List<? extends CourseType> courses) {
		for(CourseType c:courses) {
			c.evaluate();
		}
	}
}


public class MultilevelUniversityCourseManagementSystem {
 public static void main(String[] args) {
	 Course<ExamCourse> exam =new Course<>(new ExamCourse("Mathematics"));
	 Course<AssignmentCourse> assignment=new Course<>(new AssignmentCourse("Software Engineering"));
	 Course<ResearchCourse> research=new Course<>(new ResearchCourse("AI Research"));
	 
	 List<CourseType> courseList=new ArrayList<>();
	 courseList.add(exam.getCourse());
	 courseList.add(assignment.getCourse());
	 courseList.add(research.getCourse());
	 //evaluate all courses
	 CourseUtil.evaluateAll(courseList);
	 
 }
}
