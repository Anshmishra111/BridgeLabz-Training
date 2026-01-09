package linkedlist;

public class TextEditorUndoRedo {
	    class TextState {
	        String content;
	        TextState prev;
	        TextState next;

	        TextState(String content) {
	            this.content = content;
	            this.prev = null;
	            this.next = null;
	        }
	    }

	    private TextState head = null;
	    private TextState tail = null;
	    private TextState current = null;
	    private int size = 0;
	    private final int MAX_SIZE = 10;

	    void addState(String text) {
	        TextState newNode = new TextState(text);

	        // If undo was used, remove redo history
	        if (current != null && current.next != null) {
	            current.next.prev = null;
	            current.next = null;
	            tail = current;
	            size = countNodes();
	        }

	        if (head == null) {
	            head = tail = current = newNode;
	            size = 1;
	            return;
	        }

	        tail.next = newNode;
	        newNode.prev = tail;
	        tail = newNode;
	        current = newNode;
	        size++;

	        // Maintain fixed history size
	        if (size > MAX_SIZE) {
	            head = head.next;
	            head.prev = null;
	            size--;
	        }
	    }

	    // Undo operation
	    void undo() {
	        if (current == null || current.prev == null) {
	            System.out.println("Nothing to undo.");
	            return;
	        }
	        current = current.prev;
	        System.out.println("Undo performed.");
	    }

	    // Redo operation
	    void redo() {
	        if (current == null || current.next == null) {
	            System.out.println("Nothing to redo.");
	            return;
	        }
	        current = current.next;
	        System.out.println("Redo performed.");
	    }

	    // Display current text
	    void displayCurrentState() {
	        if (current == null) {
	            System.out.println("Editor is empty.");
	        } else {
	            System.out.println("Current Text: " + current.content);
	        }
	    }

	    // Helper to recount nodes after cleanup
	    int countNodes() {
	        int cnt = 0;
	        TextState temp = head;
	        while (temp != null) {
	            cnt++;
	            temp = temp.next;
	        }
	        return cnt;
	    }

	    // Main method (Demo)
	    public static void main(String[] args) {
	        TextEditorUndoRedo editor = new TextEditorUndoRedo();

	        editor.addState("Hello");
	        editor.addState("Hello World");
	        editor.addState("Hello World!");
	        editor.displayCurrentState();

	        editor.undo();
	        editor.displayCurrentState();

	        editor.undo();
	        editor.displayCurrentState();

	        editor.redo();
	        editor.displayCurrentState();

	        editor.addState("Hello Java");
	        editor.displayCurrentState();

	        editor.redo(); // Should not redo
	    }
	}


