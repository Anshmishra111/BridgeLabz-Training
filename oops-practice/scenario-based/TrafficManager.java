package scenariobased;
public class TrafficManager {

 static class VehicleNode {
     String vehicleNumber;
     VehicleNode next;

     VehicleNode(String vehicleNumber) {
         this.vehicleNumber = vehicleNumber;
         this.next = null;
     }
 }

 static class Roundabout {
     private VehicleNode head = null;

     // Add vehicle to roundabout
     public void addVehicle(String vehicleNumber) {
         VehicleNode newNode = new VehicleNode(vehicleNumber);

         if (head == null) {
             head = newNode;
             newNode.next = head;
         } else {
             VehicleNode temp = head;
             while (temp.next != head) {
                 temp = temp.next;
             }
             temp.next = newNode;
             newNode.next = head;
         }
         System.out.println(vehicleNumber + " entered the roundabout.");
     }

     // Remove vehicle from roundabout
     public void removeVehicle(String vehicleNumber) {
         if (head == null) {
             System.out.println("Roundabout is empty.");
             return;
         }

         VehicleNode curr = head;
         VehicleNode prev = null;

         do {
             if (curr.vehicleNumber.equals(vehicleNumber)) {
                 if (curr == head && curr.next == head) {
                     head = null;
                 } else {
                     if (curr == head) {
                         VehicleNode last = head;
                         while (last.next != head) {
                             last = last.next;
                         }
                         head = head.next;
                         last.next = head;
                     } else {
                         prev.next = curr.next;
                     }
                 }
                 System.out.println(vehicleNumber + " exited the roundabout.");
                 return;
             }
             prev = curr;
             curr = curr.next;
         } while (curr != head);

         System.out.println("Vehicle not found in roundabout.");
     }

     // Display roundabout state
     public void display() {
         if (head == null) {
             System.out.println("Roundabout is empty.");
             return;
         }

         System.out.print("Roundabout: ");
         VehicleNode temp = head;
         do {
             System.out.print(temp.vehicleNumber + " -> ");
             temp = temp.next;
         } while (temp != head);
         System.out.println("(back to start)");
     }

     public boolean isEmpty() {
         return head == null;
     }
 }

 // ---------- Queue (Waiting Vehicles) ----------
 static class VehicleQueue {
     private String[] queue;
     private int front = 0, rear = -1, size = 0;

     VehicleQueue(int capacity) {
         queue = new String[capacity];
     }

     // Enqueue
     public void enqueue(String vehicle) {
         if (size == queue.length) {
             System.out.println("Queue Overflow! Vehicle " + vehicle + " must wait outside.");
             return;
         }
         rear = (rear + 1) % queue.length;
         queue[rear] = vehicle;
         size++;
         System.out.println(vehicle + " added to waiting queue.");
     }

     // Dequeue
     public String dequeue() {
         if (size == 0) {
             System.out.println("Queue Underflow! No vehicles waiting.");
             return null;
         }
         String vehicle = queue[front];
         front = (front + 1) % queue.length;
         size--;
         return vehicle;
     }

     public boolean isEmpty() {
         return size == 0;
     }

     public void display() {
         if (size == 0) {
             System.out.println("Waiting Queue is empty.");
             return;
         }
         System.out.print("Waiting Queue: ");
         for (int i = 0; i < size; i++) {
             System.out.print(queue[(front + i) % queue.length] + " ");
         }
         System.out.println();
     }
 }

 // ---------- MAIN ----------
 public static void main(String[] args) {

     Roundabout roundabout = new Roundabout();
     VehicleQueue queue = new VehicleQueue(3);

     // Vehicles arrive
     queue.enqueue("CAR-101");
     queue.enqueue("CAR-102");
     queue.enqueue("CAR-103");
     queue.enqueue("CAR-104"); // Overflow

     System.out.println();

     // Move vehicles from queue to roundabout
     while (!queue.isEmpty()) {
         String vehicle = queue.dequeue();
         if (vehicle != null) {
             roundabout.addVehicle(vehicle);
         }
     }

     roundabout.display();
     System.out.println();

     // Vehicle exits
     roundabout.removeVehicle("CAR-102");
     roundabout.display();

     System.out.println();

     // New vehicles arrive
     queue.enqueue("CAR-105");
     queue.display();

     // Allow entry
     String nextVehicle = queue.dequeue();
     if (nextVehicle != null) {
         roundabout.addVehicle(nextVehicle);
     }

     roundabout.display();
 }
}
