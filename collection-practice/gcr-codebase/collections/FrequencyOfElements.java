package collections;
import java.util.*;
public class FrequencyOfElements {
static Map<String,Integer> findFrequency(List<String> list){
	Map<String,Integer> freqMap=new HashMap<>();
	for(String item:list) {
		freqMap.put(item,freqMap.getOrDefault(item,0)+1);
	}
	return freqMap;
}
public static void main(String[] args) {
	List<String> items= Arrays.asList("apple","banana","apple","orange");
	Map<String,Integer>result=findFrequency(items);
	System.out.println(result);
}
}
