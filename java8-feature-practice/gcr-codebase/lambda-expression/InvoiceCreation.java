package lambda_expression;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Invoice {
    int transactionId;

    Invoice(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Invoice generated for Transaction ID: " + transactionId;
    }
}

public class InvoiceCreation {

    public static void main(String[] args) {

        List<Integer> transactionIds = Arrays.asList(1001, 1002, 1003, 1004);

        // Create Invoice objects using constructor reference
        List<Invoice> invoices = transactionIds.stream()
                                                .map(Invoice::new) // constructor reference
                                                .collect(Collectors.toList());

        invoices.forEach(System.out::println);
    }
}
