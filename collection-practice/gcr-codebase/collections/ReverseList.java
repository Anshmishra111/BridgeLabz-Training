package collections;
import java.util.*;
public class ReverseList {
static void reverseList(List<Integer> list) {
	int i=0,j=list.size()-1;
	while(i<j) {
		int temp=list.get(i);
		list.set(i, list.get(j));
		list.set(j,temp);
		i++;
		j--;
	}
}
public static void main(String[] args) {
	List<Integer> arrayList=new ArrayList<>(Arrays.asList(1,2,3,4,5));
	reverseList(arrayList);
	System.out.println(arrayList);
}
}
