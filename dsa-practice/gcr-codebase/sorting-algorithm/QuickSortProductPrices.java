package sorting;
import java.util.Arrays;
public class QuickSortProductPrices {
public static void quickSort(double[] prices, int low,int high) {
	if(low<high) {
		int pi=partition(prices,low,high);
		quickSort(prices,low,pi-1);
		quickSort(prices,pi+1,high);
	}
}
private static int partition(double[] prices,int low,int high) {
	double pivot=prices[high];
	int i=low-1;
	for(int j=low;j<high;j++) {
		if(prices[j]<pivot) {
			i++;
			double temp=prices[i];
			prices[i]=prices[j];
			prices[j]=temp;
		}
	}
	double temp=prices[i+1];
	prices[i+1]=prices[high];
	prices[high]=temp;
	return i+1;
	
}
public static void main(String[] args) {
	double [] prices= {999.99, 299.50, 149.99, 799.00, 499.25};
	quickSort(prices,0,prices.length-1);
	System.out.println(Arrays.toString(prices));
}
}
