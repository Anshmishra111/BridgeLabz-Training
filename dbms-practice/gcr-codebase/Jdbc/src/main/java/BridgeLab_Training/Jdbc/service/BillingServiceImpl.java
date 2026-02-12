package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.dao.BillingDAO;
import BridgeLab_Training.Jdbc.dao.BillingDAOImpl;
import BridgeLab_Training.Jdbc.model.PaymentTransaction;

import java.util.List;

public class BillingServiceImpl implements BillingService {

    private final BillingDAO dao = new BillingDAOImpl();

    @Override
    public int generateBill(int visitId, double additionalCharges) {

        double consultationFee = dao.getConsultationFeeByVisit(visitId);
        double total = consultationFee + additionalCharges;

        return dao.generateBill(visitId, total);
    }

    @Override
    public void recordPayment(int billId, String mode, double amount) {

        try {
            dao.markBillPaid(billId);
            dao.insertPayment(
                    new PaymentTransaction(billId, mode, amount)
            );
        } catch (Exception e) {
            throw new RuntimeException("Payment transaction failed", e);
        }
    }

    @Override
    public List<String> viewOutstandingBills() {
        return dao.getOutstandingBills();
    }

    @Override
    public List<String> revenueReport(java.time.LocalDate from,
                                      java.time.LocalDate to) {
        return dao.getRevenueReport(from, to);
    }
}