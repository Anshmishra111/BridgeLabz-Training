package sorting;

import java.util.Arrays;

public class BubbleSortStudentMarks {
public static void bubbleSort(int[] marks) {
	int n=marks.length;
	boolean swapped;
	for(int i=0;i<n-1;i++) {
		swapped =false;
		for(int j=0;j<n-i-1;j++) {
			if(marks[j]>marks[j+1]) {
				//swap
				int temp=marks[j];
				marks[j]=marks[j+1];
				marks[j+1]=temp;
				swapped =true;
			}
		}
		//if no swap array is already sorted
		if(!swapped) {
			break;
		}
	}
}
public static void main(String[] args) {
	int [] marks= {78,45,89,62,55};
	bubbleSort(marks);
	System.out.println(Arrays.toString(marks));
	
}
}
