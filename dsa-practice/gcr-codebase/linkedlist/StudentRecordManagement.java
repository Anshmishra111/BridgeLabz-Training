package linkedlist;

public class StudentRecordManagement {
class Node{
	int rollno;
	String name;
	int age;
	char grade;
	Node next;
	Node(int rollno,String name,int age,char grade){
		this.rollno=rollno;
		this.name=name;
		this.age=age;
		this.grade=grade;
		this.next=null;
	}
	
	
}
Node head=null;

void addAtBeginning(int rollno, String name,int age,char grade) {
	Node newNode= new Node(rollno,name,age,grade);
	newNode.next=head;
	head=newNode;
	
}
void addAtEnd(int rollno,String name,int age,char grade) {
	Node newNode=new Node(rollno,name,age,grade);
	if(head==null) {
		head=newNode;
		return;
	}
	Node temp=head;
	if(temp.next!=null) {
		temp=temp.next;
	}
	temp.next=newNode;
	
}
void addAtPosition(int position, int rollNo, String name, int age, char grade) {
    if (position == 1) {
        addAtBeginning(rollNo, name, age, grade);
        return;
    }

    Node newNode = new Node(rollNo, name, age, grade);
    Node temp = head;

    for (int i = 1; i < position - 1 && temp != null; i++) {
        temp = temp.next;
    }

    if (temp == null) {
        System.out.println("Invalid position");
        return;
    }

    newNode.next = temp.next;
    temp.next = newNode;
}

// Delete by Roll Number
void deleteByRollNo(int rollNo) {
    if (head == null) {
        System.out.println("List is empty");
        return;
    }

    if (head.rollno == rollNo) {
        head = head.next;
        System.out.println("Student deleted");
        return;
    }

    Node temp = head;
    while (temp.next != null && temp.next.rollno != rollNo) {
        temp = temp.next;
    }

    if (temp.next == null) {
        System.out.println("Student not found");
    } else {
        temp.next = temp.next.next;
        System.out.println("Student deleted");
    }
}

// Search by Roll Number
void searchByRollNo(int rollNo) {
    Node temp = head;

    while (temp != null) {
        if (temp.rollNo == rollNo) {
            System.out.println("Student Found:");
            System.out.println(temp.rollNo + " " + temp.name + " " +
                    temp.age + " " + temp.grade);
            return;
        }
        temp = temp.next;
    }
    System.out.println("Student not found");
}

// Update Grade by Roll Number
void updateGrade(int rollNo, char newGrade) {
    Node temp = head;

    while (temp != null) {
        if (temp.rollno == rollNo) {
            temp.grade = newGrade;
            System.out.println("Grade updated");
            return;
        }
        temp = temp.next;
    }
    System.out.println("Student not found");
}

// Display all students
void displayStudents() {
    if (head == null) {
        System.out.println("No student records");
        return;
    }

    Node temp = head;
    System.out.println("\nStudent Records:");
    while (temp != null) {
        System.out.println(temp.rollno + " " + temp.name +
                " " + temp.age + " " + temp.grade);
        temp = temp.next;
    }
}

// Main method (Testing)
public static void main(String[] args) {

	StudentRecordManagement list = new StudentRecordManagement();

    list.addAtBeginning(101, "Ansh", 20, 'A');
    list.addAtEnd(102, "Riya", 21, 'B');
    list.addAtEnd(103, "Rahul", 22, 'C');
    list.addAtPosition(2, 104, "Neha", 19, 'A');

    list.displayStudents();

    list.searchByRollNo(102);
    list.updateGrade(103, 'B');
    list.deleteByRollNo(101);

    list.displayStudents();
}
}

