package scenariobased;
//Custom Exception
class InvalidInvoiceFormatException extends Exception {
 public InvalidInvoiceFormatException(String message) {
     super(message);
 }
}

public class InvoiceGeneratorExceptionHandling {

 // Method to split invoice into tasks
 static String[] parseInvoice(String input)
         throws InvalidInvoiceFormatException {

     if (input == null || input.trim().isEmpty()) {
         throw new InvalidInvoiceFormatException("Invoice input is empty");
     }

     String[] tasks = input.split(",");

     // Validate each task format
     for (String task : tasks) {
         if (!task.contains("-")) {
             throw new InvalidInvoiceFormatException(
                     "Invalid format: Missing '-' in task -> " + task);
         }
     }
     return tasks;
 }

 // Method to calculate total amount
 static int getTotalAmount(String[] tasks)
         throws InvalidInvoiceFormatException {

     int total = 0;

     for (String task : tasks) {
         String[] parts = task.split("-");

         if (parts.length < 2) {
             throw new InvalidInvoiceFormatException(
                     "Invalid task format: " + task);
         }

         String amountPart = parts[1].trim(); // e.g. "3000 INR"
         String[] amountSplit = amountPart.split(" ");

         if (amountSplit.length == 0) {
             throw new InvalidInvoiceFormatException(
                     "Amount missing in task: " + task);
         }

         try {
             int amount = Integer.parseInt(amountSplit[0]);
             total += amount;
         } catch (NumberFormatException e) {
             throw new InvalidInvoiceFormatException(
                     "Invalid amount in task: " + task);
         }
     }
     return total;
 }

 public static void main(String[] args) {

     String invoiceInput =
             "Logo Design - 3000 INR, Web Page - 4500 INR";

     try {
         // Parse invoice
         String[] tasks = parseInvoice(invoiceInput);

         System.out.println("Invoice Details:");
         for (String task : tasks) {
             System.out.println(task.trim());
         }

         // Calculate total
         int totalAmount = getTotalAmount(tasks);
         System.out.println("\nTotal Invoice Amount: " + totalAmount + " INR");

     } catch (InvalidInvoiceFormatException e) {
         System.out.println("Error: " + e.getMessage());
     }
 }
}

