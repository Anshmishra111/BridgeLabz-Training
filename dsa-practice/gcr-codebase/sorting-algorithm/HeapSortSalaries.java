package sorting;

import java.util.Arrays;

public class HeapSortSalaries {

	static void heapSort(int[] salaries) {
		int n=salaries.length;
		//step 1 : build max heap
		for(int i=n/2-1;i>=0;i--) {
			heap(salaries,n,i);
		}
		//step 2: extract elements from heap one
		for(int i=n-1;i>0;i--) {
			int temp=salaries[0];
			salaries[0]=salaries[i];
			salaries[i]=temp;
			// heap reduced heap
			heap(salaries,i,0);
		}
	}
	//heap function(max heap)
	static void heap(int[] salaries,int n,int i) {
		int largest=i;
		int left=2*i+1;
		int right=2*i+2;
		if(left<n && salaries[left]>salaries[largest])
			largest=left;
		if(right<n && salaries[right] >salaries[largest])
			largest=right;
		if(largest!=i) {
			int swap=salaries[i];
			salaries[i]=salaries[largest];
			salaries[largest]=swap;
			
			heap(salaries,n,largest);
		}
		
	}
	public static void main(String[] args) {
		int[] salaries={50000, 70000, 45000, 90000, 60000};
		heapSort(salaries);
		System.out.println(Arrays.toString(salaries));
	}
	
}
