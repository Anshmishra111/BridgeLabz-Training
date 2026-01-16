package sorting;

import java.util.Arrays;

public class MergeSortBookPrices {
public static void mergeSort(double[] prices,int left,int right ) {
	if(left<right) {
		//find middle
		int mid=(left+right)/2;
		//sort first and second halves
		mergeSort(prices,left,mid);
		mergeSort(prices,mid+1,right);
		//merge the sorted halves
		merge(prices,left,mid,right);
	}
}
//merge two subarrays
public static void merge(double[]prices,int left,int mid,int right) {
	int n1=mid-left+1;
	int n2=right-mid;
	
	double[] L=new double[n1];
	double[] R=new double[n2];
	//copy data to temp arrays
	System.arraycopy(prices, left, L, 0, n1);
	System.arraycopy(prices, mid+1, R, 0, n2);
	int i=0,j=0,k=left;
	while(i<n1 && j<n2) {
		if(L[i]<=R[j]) {
			prices[k++]=L[i++];
		}else {
			prices[k++]=R[j++];
		}
	}
	while(i<n1) prices[k++]=L[i++];
	while(j<n2) prices[k++]=R[j++];
}
public static void main(String[] args) {
	double[] prices= {499.50, 299.99, 150.75, 799.00, 249.25};
	mergeSort(prices,0,prices.length-1);
	System.out.println(Arrays.toString(prices));
	
}
}
