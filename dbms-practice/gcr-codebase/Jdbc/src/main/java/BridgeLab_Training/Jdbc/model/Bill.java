package BridgeLab_Training.Jdbc.model;

public class Bill {

    private int billId;
    private int visitId;
    private double totalAmount;
    private String paymentStatus;

    public Bill(int visitId, double totalAmount) {
        this.visitId = visitId;
        this.totalAmount = totalAmount;
        this.paymentStatus = "UNPAID";
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getVisitId() { return visitId; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
}