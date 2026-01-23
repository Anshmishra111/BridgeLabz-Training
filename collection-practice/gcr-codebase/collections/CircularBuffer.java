package collections;

class CircularBuffer {
    private int[] buffer;
    private int size;
    private int front;
    private int rear;
    private int count;

    public CircularBuffer(int size) {
        this.size = size;
        buffer = new int[size];
        front = 0;
        rear = 0;
        count = 0;
    }

    // Insert element (overwrite if full)
    public void insert(int value) {
        buffer[rear] = value;
        rear = (rear + 1) % size;

        if (count < size) {
            count++;
        } else {
            // Buffer full → overwrite oldest
            front = (front + 1) % size;
        }
    }

    // Display buffer contents
    public void display() {
        System.out.print("Buffer: [ ");
        for (int i = 0; i < count; i++) {
            System.out.print(buffer[(front + i) % size] + " ");
        }
        System.out.println("]");
    }

    // Main method to test
    public static void main(String[] args) {
        CircularBuffer cb = new CircularBuffer(3);

        cb.insert(1);
        cb.insert(2);
        cb.insert(3);
        cb.display();   // [1 2 3]

        cb.insert(4);
        cb.display();   // [2 3 4]

        cb.insert(5);
        cb.display();   // [3 4 5]
    }
}
