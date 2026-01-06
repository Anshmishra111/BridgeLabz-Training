package scenariobased;

public class InvoiceGenerator {
	    // Method to parse invoice and return tasks array
	    static String[] parseInvoice(String input) {
	        // Split tasks by comma
	        return input.split(",");
	    }

	    // Method to calculate total amount
	    static int getTotalAmount(String[] tasks) {

	        int total = 0;

	        for (String task : tasks) {
	            // Example task: "Logo Design - 3000 INR"
	            String[] parts = task.split("-");

	            // Extract amount part
	            String amountPart = parts[1].trim(); // "3000 INR"

	            // Extract numeric value
	            String amountString = amountPart.split(" ")[0];
	            int amount = Integer.parseInt(amountString);

	            total += amount;
	        }

	        return total;
	    }

	    public static void main(String[] args) {

	        String invoiceInput =
	                "Logo Design - 3000 INR, Web Page - 4500 INR";

	        // Parse invoice
	        String[] tasks = parseInvoice(invoiceInput);

	        // Display tasks
	        System.out.println("Invoice Details:");
	        for (String task : tasks) {
	            System.out.println(task.trim());
	        }

	        // Calculate total amount
	        int totalAmount = getTotalAmount(tasks);

	        System.out.println("\nTotal Invoice Amount: " + totalAmount + " INR");
	    }
	}


