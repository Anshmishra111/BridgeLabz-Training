package inheritance;

public class OrderManagement {
	    int orderId;
	    String orderDate;

	    OrderManagement(int orderId, String date) {
	        this.orderId = orderId;
	        this.orderDate = date;
	    }

	    String getOrderStatus() {
	        return "Order placed.";
	    }
	}

	class ShippedOrder extends OrderManagement {
	    String trackingNumber;

	    ShippedOrder(int orderId, String date, String tracking) {
	        super(orderId, date);
	        this.trackingNumber = tracking;
	    }

	    @Override
	    String getOrderStatus() {
	        return "Order shipped. Tracking No: " + trackingNumber;
	    }
	}

	class DeliveredOrder extends ShippedOrder {
	    String deliveryDate;

	    DeliveredOrder(int orderId, String date, String tracking, String deliveryDate) {
	        super(orderId, date, tracking);
	        this.deliveryDate = deliveryDate;
	    }

	    @Override
	    String getOrderStatus() {
	        return "Order delivered on " + deliveryDate;
	    }
	}

