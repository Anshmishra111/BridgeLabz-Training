package linkedlist;

public class TaskSchedulerCircularll {

	    // Node class
	    class TaskNode {
	        int taskId;
	        String taskName;
	        String dueDate;
	        int priority;
	        TaskNode next;

	        TaskNode(int taskId, String taskName, int priority, String dueDate) {
	            this.taskId = taskId;
	            this.taskName = taskName;
	            this.priority = priority;
	            this.dueDate = dueDate;
	            this.next = null;
	        }
	    }

	    TaskNode head = null;
	    TaskNode current = null;

	    // Add at Beginning
	    void addAtBeginning(int id, String name, int priority, String dueDate) {
	        TaskNode newNode = new TaskNode(id, name, priority, dueDate);

	        if (head == null) {
	            head = newNode;
	            newNode.next = head;
	            current = head;
	            return;
	        }

	        TaskNode temp = head;
	        while (temp.next != head) {
	            temp = temp.next;
	        }

	        newNode.next = head;
	        temp.next = newNode;
	        head = newNode;
	    }

	    // Add at End
	    void addAtEnd(int id, String name, int priority, String dueDate) {
	        TaskNode newNode = new TaskNode(id, name, priority, dueDate);

	        if (head == null) {
	            head = newNode;
	            newNode.next = head;
	            current = head;
	            return;
	        }

	        TaskNode temp = head;
	        while (temp.next != head) {
	            temp = temp.next;
	        }

	        temp.next = newNode;
	        newNode.next = head;
	    }

	    // Add at Specific Position
	    void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
	        if (pos == 1) {
	            addAtBeginning(id, name, priority, dueDate);
	            return;
	        }

	        TaskNode temp = head;
	        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
	            temp = temp.next;
	        }

	        TaskNode newNode = new TaskNode(id, name, priority, dueDate);
	        newNode.next = temp.next;
	        temp.next = newNode;
	    }

	    // Remove by Task ID
	    void removeTask(int taskId) {
	        if (head == null) {
	            System.out.println("Task list is empty.");
	            return;
	        }

	        TaskNode temp = head;
	        TaskNode prev = null;

	        do {
	            if (temp.taskId == taskId) {

	                if (temp == head) {
	                    TaskNode last = head;
	                    while (last.next != head) {
	                        last = last.next;
	                    }
	                    head = head.next;
	                    last.next = head;
	                } else {
	                    prev.next = temp.next;
	                }

	                System.out.println("Task removed successfully.");
	                return;
	            }

	            prev = temp;
	            temp = temp.next;

	        } while (temp != head);

	        System.out.println("Task not found.");
	    }

	    // View Current Task and Move to Next
	    void viewNextTask() {
	        if (current == null) {
	            System.out.println("No tasks available.");
	            return;
	        }

	        System.out.println("Current Task:");
	        displayTask(current);
	        current = current.next;
	    }

	    // Display All Tasks
	    void displayTasks() {
	        if (head == null) {
	            System.out.println("No tasks available.");
	            return;
	        }

	        TaskNode temp = head;
	        do {
	            displayTask(temp);
	            temp = temp.next;
	        } while (temp != head);
	    }

	    // Search by Priority
	    void searchByPriority(int priority) {
	        if (head == null) {
	            System.out.println("No tasks available.");
	            return;
	        }

	        TaskNode temp = head;
	        boolean found = false;

	        do {
	            if (temp.priority == priority) {
	                displayTask(temp);
	                found = true;
	            }
	            temp = temp.next;
	        } while (temp != head);

	        if (!found) {
	            System.out.println("No tasks found with this priority.");
	        }
	    }

	    void displayTask(TaskNode t) {
	        System.out.println("Task ID: " + t.taskId +
	                ", Name: " + t.taskName +
	                ", Priority: " + t.priority +
	                ", Due Date: " + t.dueDate);
	    }

	    // Main Method
	    public static void main(String[] args) {
	    	TaskSchedulerCircularll ts = new TaskSchedulerCircularll();

	        ts.addAtEnd(101, "Design Module", 1, "10-Feb-2026");
	        ts.addAtEnd(102, "Code Implementation", 2, "15-Feb-2026");
	        ts.addAtBeginning(100, "Requirement Analysis", 1, "05-Feb-2026");

	        ts.displayTasks();

	        ts.viewNextTask();
	        ts.viewNextTask();

	        ts.searchByPriority(1);

	        ts.removeTask(102);

	        ts.displayTasks();
	    }
	}


