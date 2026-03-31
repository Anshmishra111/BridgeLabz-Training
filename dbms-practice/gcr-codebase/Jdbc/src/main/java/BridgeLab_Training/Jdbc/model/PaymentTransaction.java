package BridgeLab_Training.Jdbc.model;

public class PaymentTransaction {

    private int billId;
    private String paymentMode;
    private double amount;

    public PaymentTransaction(int billId, String paymentMode, double amount) {
        this.billId = billId;
        this.paymentMode = paymentMode;
        this.amount = amount;
    }

    public int getBillId() { return billId; }
    public String getPaymentMode() { return paymentMode; }
    public double getAmount() { return amount; }
}