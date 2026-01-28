package stack;

public class ImplementationStackUsingArray {
	private int[] stack;
	private int top;
	private int capacity;
	
	public ImplementationStackUsingArray(int size) {
		capacity=size;
		stack=new int[capacity];
		top=-1;
	}
		
		//push operation
		public void push(int value) {
			if(isFull()) {
				System.out.println("Stack over flow" + value);
				return;
			}
			stack[++top] = value;
     System.out.println(value + " pushed into stack");
		}
		//Pop operation
	    public void pop() {
	        if (isEmpty()) {
	            System.out.println("Stack Underflow Stack is empty");
            return;
        }
        int removed = stack[top--];
        System.out.println("Popped element: " + removed);
	    }
	    //peek operation
	    public void peek() {
	    	if(isEmpty()) {
	    		System.out.println("Stack is Empty");
	    		return;
	    	}
	    	System.out.println("top element" + stack[top]);
	    }
	    public boolean isEmpty() {
	    	return top==-1;
	    
	    }
	    public boolean isFull() {
	    	return top==capacity-1;
	    }
	    public void display() {
        if (isEmpty()) {
	            System.out.println("Stack is empty");
	            return;
	        }
	        System.out.print("Stack elements: ");
        for (int i = top; i >= 0; i--) {
	            System.out.print(stack[i] + " ");
        }
	        System.out.println();	  
	        }
			//main
		public static void main(String[] args) {
			ImplementationStackUsingArray stack=new ImplementationStackUsingArray(5);
			stack.push(10);
			stack.push(20);
			stack.push(30);
			
			stack.display();
			stack.peek();
			stack.pop();
			stack.display();
		}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
				
				
				
				
				
				
				
				
				
				
				
				
				
		
	