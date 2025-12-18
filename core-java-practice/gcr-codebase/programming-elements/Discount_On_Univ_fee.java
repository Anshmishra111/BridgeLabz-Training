public class  UnivFeeDiscount {
    public static void main(String[] args) {
        double fee = 125000;
        double discountPercent=10;
        double discount=(fee*discountPercent)/100;
        double finalFee = fee-discount;
        System.out.println("The discont amount is INR " + discount +" and final discounted fee is INR " + finalFee);
    }
}
