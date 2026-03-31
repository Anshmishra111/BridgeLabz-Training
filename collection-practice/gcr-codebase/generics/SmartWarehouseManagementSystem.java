package generics;
import java.util.*;
//abstract base class
abstract class WarehouseItem{
	String name;
	WarehouseItem(String name){
		this.name=name;
	}
	abstract void display(); 
}
//item types
class Electronics extends WarehouseItem{
	Electronics(String name){
		super(name);
	}
	void display() {
		System.out.println("Electronics : " + name);
	}
}
class Groceries extends WarehouseItem{
	Groceries(String name){
		super (name);
	}
	void display() {
		System.out.println("Groceries : " + name);
	}
}
class Furniture extends WarehouseItem{
	Furniture(String name){
		super(name);
	}
	void display() {
		System.out.println("Furniture : " + name);
	}
}
//Generic  storage class
class Storage<T extends WarehouseItem>{
	private List<T> items=new ArrayList<>();
	void addItem(T item) {
		items.add(item);
		
	}
	List<T> getItems(){
		return items;
	}
}
//wildcard method
class WarehouseUtil{
	static void displayAll(List<? extends WarehouseItem> items) {
		for(WarehouseItem item:items) {
			item.display();
		}
	}
}
public class SmartWarehouseManagementSystem {
	public static void main(String[] args) {
		Storage<Electronics> electronicsStore=new Storage<>();
		electronicsStore.addItem(new Electronics("Laptop"));
		electronicsStore.addItem(new Electronics("Mobile"));
		
		Storage<Groceries> groceryStore=new Storage<>();
		groceryStore.addItem(new Groceries("Rice"));
		groceryStore.addItem(new Groceries("Milk"));
		System.out.println("----Electronics Item----");
		WarehouseUtil.displayAll(electronicsStore.getItems());
		
		System.out.println("---- Groceries ----");
        WarehouseUtil.displayAll(groceryStore.getItems());
	}

}
