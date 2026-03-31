package scenariobased;

//custom exception
class PaymentFailedException extends Exception{
	public PaymentFailedException(String message) {
		super(message);
	}
}
//payment interface 
interface Payment{
	void pay(double amount) throws PaymentFailedException;
}
class CardPayment implements Payment{
	public void pay(double amount) throws PaymentFailedException{
		if(amount<=0) {
			throw new PaymentFailedException("card payment failed");
			
		}
		System.out.println("Paid " + amount + " using card");
	}
}
//UPI Payment

class UpiPayment implements Payment{
	public void pay(double amount) throws PaymentFailedException{
		if(amount<100) {
			throw new PaymentFailedException("UPI Payment Failed!"); 
		}
		System.out.println("Paid  " + amount + " using UPI");
	}
}
//wallet payment

class WalletPayment implements Payment{
	public void pay(double amount) throws PaymentFailedException{
        System.out.println("Paid ₹" + amount + " using Wallet");

	}
}
//product class
class Product{
	String name;
	int productId;
	double price;
	
	public Product(String name,int productId,double price) {
		this.name=name;
		this.productId=productId;
		this.price=price;
		
	}
	public void displayProduct() {
		System.out.println(productId + "|" + name + "|" + price);
	}
}
//customer class
class Customer{
	int customerId;
	private String name;
	public Customer(int customerId,String name) {
		this.customerId=customerId;
		this.name=name;
	}
	//getter for private field
	public String getName() {
		return name;
	}
}
//order class
class Order{
	int orderId;
    Product product;
    Customer customer;
    String status;

    public Order(int orderId, Product product, Customer customer) {
        this.orderId = orderId;
        this.product = product;
        this.customer = customer;
        this.status = "PLACED";
}
    public void cancelOrder(){
    	status="CANCELLED";
    	System.out.println("Order cancelled successfully");
    }
    public void displayOrder(){
    	System.out.println("Order ID : " + orderId);
        System.out.println("Customer : " + customer.getName());
        System.out.println("Product  : " + product.name);
        System.out.println("Price    : ₹" + product.price);
        System.out.println("Status   : " + status);
    }
}

public class EcommerceOrderManagementSystem {
	public static void main(String[] args) {
		//product catalog
		Product p1=new Product("Laptop",101,55000);
		Product p2=new Product("Mobile",102,18000);
		
		System.out.println("------Product Catalog");
		p1.displayProduct();
		p2.displayProduct();
		
		//customer
		Customer customer=new Customer(1,"Ansh");
		
		//order placement
		Order order=new Order(5001,p1,customer);
		//Payment
		Payment payment=new UpiPayment();
		
		//display order
		System.out.println("\n----Order Details");
     	order.displayOrder();
     	
     	//payment
     	try {
     		payment.pay(p1.price);
     		System.out.println("Order Placed Successfully");
     	}catch(PaymentFailedException e){
     		System.out.println(e.getMessage());
     		
     	}
		//cancel order
		System.out.println("\n----Cancel Order---");
		order.displayOrder();
		order.cancelOrder();
	}

}
