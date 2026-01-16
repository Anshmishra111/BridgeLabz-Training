package sorting;
import java.util.Arrays;
public class SelectionSortExamScores {
 static void selectionSort(int[] scores) {
int n=scores.length;
for(int i=0;i<n-1;i++) {
	int miniIndex=i;
	//find index of minimum element
	for(int j=i+1;j<n;j++) {
		if(scores[j]<scores[miniIndex]) {
			miniIndex=j;
		}
	}
	//swap if needed
	int temp=scores[miniIndex];
	scores[miniIndex]=scores[i];
	scores[i]=temp;
}
}
public static void main(String[] args) {
	int[] scores= {78, 92, 65, 88, 74};
    selectionSort(scores);
    System.out.println(Arrays.toString(scores));
}
}