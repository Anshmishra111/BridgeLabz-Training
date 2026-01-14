package scenariobased;

// Custom Exception
class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

// Alert Interface
interface AlertService {
    void checkStock(Productss product) throws OutOfStockException;
}

// Product Class (OOP)
class Productss {
    private int productId;
    private String name;
    private int quantity;

    public Productss(int productId, String name, int quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters & Setters (Encapsulation)
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int qty) {
        this.quantity = qty;
    }

    public void displayProduct() {
        System.out.println(productId + " | " + name + " | Stock: " + quantity);
    }
}

// Inventory Class
class Inventory implements AlertService {

    private Productss[] products = new Productss[5];
    private int count = 0;

    // Add Product
    public void addProduct(Productss product) {
        if (count < products.length) {
            products[count++] = product;
            System.out.println("Product added: " + product.getName());
        } else {
            System.out.println("Inventory full!");
        }
    }

    // Update Stock
    public void updateStock(int productId, int newQty) {
        for (int i = 0; i < count; i++) {
            if (products[i].getProductId() == productId) {
                products[i].updateQuantity(newQty);
                System.out.println("Stock updated for " + products[i].getName());
                return;
            }
        }
        System.out.println("Product not found.");
    }

    // Display Inventory
    public void displayInventory() {
        System.out.println("\n--- Inventory Details ---");
        for (int i = 0; i < count; i++) {
            products[i].displayProduct();
        }
    }

    // Stock Alert
    public void checkStock(Productss product) throws OutOfStockException {
        if (product.getQuantity() <= 0) {
            throw new OutOfStockException("ALERT: " + product.getName() + " is out of stock!");
        } else if (product.getQuantity() <= 5) {
            System.out.println("LOW STOCK ALERT: " + product.getName());
        }
    }

    // Check Alerts for All Products
    public void checkAllStock() {
        for (int i = 0; i < count; i++) {
            try {
                checkStock(products[i]);
            } catch (OutOfStockException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

// Main Class
public class InventoryMnagementSystem {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        // Add Products
        Productss p1 = new Productss(101, "Keyboard", 10);
        Productss p2 = new Productss(102, "Mouse", 4);
        Productss p3 = new Productss(103, "Monitor", 0);

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);

        // Display Inventory
        inventory.displayInventory();

        // Update Stock
        inventory.updateStock(102, 2);

        // Check Stock Alerts
        System.out.println("\n--- Stock Alerts ---");
        inventory.checkAllStock();
    }
}
