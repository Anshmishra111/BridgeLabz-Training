package collectors;
import java.util.*;
import java.util.stream.*;
class Student{
	String name;
	String grade;
	Student(String name,String grade){
		this.name=name;
		this.grade=grade;
	}
}

public class StudentResultGrouping {
	public static void main(String[] args) {
		List<Student> students=Arrays.asList(new Student("Amit","A"),
				new Student("Rahul","R"),
				new Student("Neha","N"),
				new Student("Priya", "C"),
	            new Student("Suman", "B"));
		Map<String,List<String>> result=
				students.stream()
				.collect(Collectors.groupingBy(s ->s.grade,
						Collectors.mapping(s ->s.name,Collectors.toList())));
		result.forEach((grade,names)->
		System.out.println(grade + " : " + names));
	}

}
