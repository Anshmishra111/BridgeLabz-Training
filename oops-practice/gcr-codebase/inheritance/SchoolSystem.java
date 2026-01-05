package inheritance;

	class School_System {
	    String name;
	    int age;

	    School_System(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }
	}

	class Teacher extends School_System {
	    String subject;

	    Teacher(String name, int age, String subject) {
	        super(name, age);
	        this.subject = subject;
	    }

	    void displayRole() {
	        System.out.println("Role: Teacher");
	    }
	}

	class Student extends School_System {
	    String grade;

	    Student(String name, int age, String grade) {
	        super(name, age);
	        this.grade = grade;
	    }

	    void displayRole() {
	        System.out.println("Role: Student");
	    }
	}

	class Staff extends School_System {
	    String department;

	    Staff(String name, int age, String department) {
	        super(name, age);
	        this.department = department;
	    }

	    void displayRole() {
	        System.out.println("Role: Staff");
	    }
	}

