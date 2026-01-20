package generics;
import java.util.*;
//category interfaces
interface Category{
	String getCategoryName();
}
class BookCategory implements Category{
	public String getCategoryName() {
		return "Books";
	}
}
class ClothingCategory implements Category{
	public String getCategoryName() {
		return "Clothing";
	}
}
class GadgetCategory implements Category{
	public String getCategoryName() {
		return "Gadgets";
	}
}
//generic product class
class Product<T extends Category>{
	String name;
	double price;
	T category;
	
	Product(String name,double price,T category){
		this.name=name;
		this.price=price;
		this.category=category;
	}
	void display() {
        System.out.println(name + " | ₹" + price + " | " + category.getCategoryName());
	
	}
	
}
class  DiscountUtil{
	static <T extends Product<?>> void applyDiscount(T product,double percentage) {
		product.price-=product.price*(percentage/100);
	}
}
public class DynamicOnlineMarkeplace {
	public static void main(String[] args) {
		Product<BookCategory> book=new Product<>("Java Programming",500,new BookCategory());
		Product<ClothingCategory> shirt=new Product<>("T-Shirt",800,new ClothingCategory());
		Product<GadgetCategory> phone=new Product<>("Smartphone",20000,new GadgetCategory());
		List<Product<?>> catalog=new ArrayList<>();
		catalog.add(book);
		catalog.add(shirt);
		catalog.add(phone);
		//apply discount
		DiscountUtil.applyDiscount(book,10);
		DiscountUtil.applyDiscount(phone,5);
		//display catalog
		for(Product<?>p:catalog) {
			p.display();
		}
	}

}
