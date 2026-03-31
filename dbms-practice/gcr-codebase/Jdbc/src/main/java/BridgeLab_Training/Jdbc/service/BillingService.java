package BridgeLab_Training.Jdbc.service;

import java.time.LocalDate;
import java.util.List;

public interface BillingService {

    int generateBill(int visitId, double additionalCharges);

    void recordPayment(int billId, String mode, double amount);

    List<String> viewOutstandingBills();

    List<String> revenueReport(LocalDate from, LocalDate to);
}