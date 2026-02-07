package stream_api;
import java.util.*;
import java.util.stream.*;
public class StockPriceLogger {
	  public static void main(String[] args) {
	  List<Double> stockPrices=Arrays.asList(125.50, 130.75, 128.40, 132.10, 129.90);
	  stockPrices.stream().forEach(price -> System.out.println("Stock price: " + price));
	  }
}
