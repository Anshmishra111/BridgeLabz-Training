package linkedlist;

public class InventoryManagementSystem {
	    class ItemNode {
	        int itemId;
	        String itemName;
	        int quantity;
	        double price;
	        ItemNode next;

	        ItemNode(int itemId, String itemName, int quantity, double price) {
	            this.itemId = itemId;
	            this.itemName = itemName;
	            this.quantity = quantity;
	            this.price = price;
	            this.next = null;
	        }
	    }

	    ItemNode head = null;

	    // Add at Beginning
	    void addAtBeginning(int id, String name, int qty, double price) {
	        ItemNode newNode = new ItemNode(id, name, qty, price);
	        newNode.next = head;
	        head = newNode;
	    }

	    // Add at End
	    void addAtEnd(int id, String name, int qty, double price) {
	        ItemNode newNode = new ItemNode(id, name, qty, price);
	        if (head == null) {
	            head = newNode;
	            return;
	        }

	        ItemNode temp = head;
	        while (temp.next != null) {
	            temp = temp.next;
	        }
	        temp.next = newNode;
	    }

	    // Add at Specific Position
	    void addAtPosition(int pos, int id, String name, int qty, double price) {
	        if (pos == 1) {
	            addAtBeginning(id, name, qty, price);
	            return;
	        }

	        ItemNode temp = head;
	        for (int i = 1; i < pos - 1 && temp != null; i++) {
	            temp = temp.next;
	        }

	        if (temp == null) {
	            System.out.println("Invalid position.");
	            return;
	        }

	        ItemNode newNode = new ItemNode(id, name, qty, price);
	        newNode.next = temp.next;
	        temp.next = newNode;
	    }

	    // Remove by Item ID
	    void removeById(int id) {
	        if (head == null) return;

	        if (head.itemId == id) {
	            head = head.next;
	            System.out.println("Item removed.");
	            return;
	        }

	        ItemNode temp = head;
	        while (temp.next != null && temp.next.itemId != id) {
	            temp = temp.next;
	        }

	        if (temp.next != null) {
	            temp.next = temp.next.next;
	            System.out.println("Item removed.");
	        } else {
	            System.out.println("Item not found.");
	        }
	    }

	    // Update Quantity
	    void updateQuantity(int id, int newQty) {
	        ItemNode temp = head;
	        while (temp != null) {
	            if (temp.itemId == id) {
	                temp.quantity = newQty;
	                System.out.println("Quantity updated.");
	                return;
	            }
	            temp = temp.next;
	        }
	        System.out.println("Item not found.");
	    }

	    // Search by ID
	    void searchById(int id) {
	        ItemNode temp = head;
	        while (temp != null) {
	            if (temp.itemId == id) {
	                displayItem(temp);
	                return;
	            }
	            temp = temp.next;
	        }
	        System.out.println("Item not found.");
	    }

	    // Search by Name
	    void searchByName(String name) {
	        ItemNode temp = head;
	        boolean found = false;

	        while (temp != null) {
	            if (temp.itemName.equalsIgnoreCase(name)) {
	                displayItem(temp);
	                found = true;
	            }
	            temp = temp.next;
	        }

	        if (!found)
	            System.out.println("Item not found.");
	    }

	    // Total Inventory Value
	    void calculateTotalValue() {
	        double total = 0;
	        ItemNode temp = head;

	        while (temp != null) {
	            total += temp.quantity * temp.price;
	            temp = temp.next;
	        }

	        System.out.println("Total Inventory Value = " + total);
	    }

	    // -------- SORTING (MERGE SORT) --------

	    ItemNode mergeSort(ItemNode node, boolean sortByName, boolean ascending) {
	        if (node == null || node.next == null)
	            return node;

	        ItemNode middle = getMiddle(node);
	        ItemNode nextOfMiddle = middle.next;
	        middle.next = null;

	        ItemNode left = mergeSort(node, sortByName, ascending);
	        ItemNode right = mergeSort(nextOfMiddle, sortByName, ascending);

	        return sortedMerge(left, right, sortByName, ascending);
	    }

	    ItemNode sortedMerge(ItemNode a, ItemNode b, boolean sortByName, boolean asc) {
	        if (a == null) return b;
	        if (b == null) return a;

	        boolean condition;
	        if (sortByName) {
	            condition = asc ?
	                    a.itemName.compareToIgnoreCase(b.itemName) <= 0 :
	                    a.itemName.compareToIgnoreCase(b.itemName) > 0;
	        } else {
	            condition = asc ? a.price <= b.price : a.price > b.price;
	        }

	        ItemNode result;
	        if (condition) {
	            result = a;
	            result.next = sortedMerge(a.next, b, sortByName, asc);
	        } else {
	            result = b;
	            result.next = sortedMerge(a, b.next, sortByName, asc);
	        }
	        return result;
	    }

	    ItemNode getMiddle(ItemNode node) {
	        if (node == null) return node;

	        ItemNode slow = node, fast = node.next;
	        while (fast != null && fast.next != null) {
	            slow = slow.next;
	            fast = fast.next.next;
	        }
	        return slow;
	    }

	    void sortInventory(boolean sortByName, boolean ascending) {
	        head = mergeSort(head, sortByName, ascending);
	        System.out.println("Inventory sorted.");
	    }

	    // Display Inventory
	    void displayInventory() {
	        ItemNode temp = head;
	        if (temp == null) {
	            System.out.println("Inventory empty.");
	            return;
	        }
	        while (temp != null) {
	            displayItem(temp);
	            temp = temp.next;
	        }
	    }

	    void displayItem(ItemNode i) {
	        System.out.println("ID: " + i.itemId +
	                ", Name: " + i.itemName +
	                ", Qty: " + i.quantity +
	                ", Price: " + i.price);
	    }

	    // Main Method
	    public static void main(String[] args) {
	        InventoryManagementSystem ims = new InventoryManagementSystem();

	        ims.addAtEnd(101, "Keyboard", 10, 800);
	        ims.addAtEnd(102, "Mouse", 20, 400);
	        ims.addAtBeginning(100, "Monitor", 5, 12000);

	        ims.displayInventory();
	        ims.calculateTotalValue();

	        ims.updateQuantity(102, 30);
	        ims.searchByName("Mouse");

	        ims.sortInventory(true, true);   // Sort by Name Ascending
	        ims.displayInventory();

	        ims.sortInventory(false, false); // Sort by Price Descending
	        ims.displayInventory();
	    }
	}


