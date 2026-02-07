package collectors;
import java.util.*;
import java.util.stream.*;
public class WordFrequencyCounter {
public static void main(String[] args) {
	String paragraph="java is easy and java is powerful";
	Map<String,Integer> wordCount=Arrays.stream(paragraph.split(" "))
			.collect(Collectors.toMap(word ->word,
					word ->1,
					(oldValue,newValue)-> oldValue+newValue));
	wordCount.forEach((word,count)->
	System.out.println(word + " : " + count));
}
}
