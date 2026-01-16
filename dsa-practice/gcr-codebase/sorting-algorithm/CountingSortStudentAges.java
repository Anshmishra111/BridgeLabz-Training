package sorting;

import java.util.Arrays;

public class CountingSortStudentAges {
	static void countingSort(int[] ages) {
		int minAge=10;
		int maxAge=18;
		int range=maxAge-minAge+1;
		int[] count=new int[range];
		int[] output=new int[ages.length];
		//step 1: store frequency of each age
		for(int age:ages) {
			count[age-minAge]++;
		}
		//step 2:cumulative count
		for(int i=1;i<range;i++) {
			count[i]+=count[i-1];
		}
		//step 3: place elements in correct position
		for(int i=ages.length-1;i>=0;i--) {
			int age=ages[i];
			output[count[age-minAge]-1]=age;
			count[age-minAge]--;
		}
		//copy sorted output back
		for(int i=0;i<ages.length;i++) {
			ages[i]=output[i];
		}
	}
	//main method
	public static void main(String[] args) {
		int[] ages={12, 15, 10, 14, 18, 13, 11, 16};
		
		countingSort(ages);
		System.out.println(Arrays.toString(ages));
	}

}
