package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.PaymentTransaction;

import java.time.LocalDate;
import java.util.List;

public interface BillingDAO {

    double getConsultationFeeByVisit(int visitId);

    int generateBill(int visitId, double totalAmount);

    void markBillPaid(int billId);

    void insertPayment(PaymentTransaction payment);

    List<String> getOutstandingBills();

    List<String> getRevenueReport(LocalDate from, LocalDate to);
}