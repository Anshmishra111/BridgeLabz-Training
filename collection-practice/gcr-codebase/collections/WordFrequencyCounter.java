package collections;
import java.util.HashMap;
import java.util.Map;
public class WordFrequencyCounter {
public static void main(String[] args) {
	String input="Hello world, hello Java!";
	//convert to lowercase and remove punctuation
	input=input.toLowerCase().replaceAll("[^a-z ]", "");
	String[] words=input.split("\\s+");
	Map<String,Integer> wordCount=new HashMap<>();
	for(String word:words) {
		if(!word.isEmpty()) {
			wordCount.put(word,wordCount.getOrDefault(word, 0)+1);
		}
	}
	System.out.println(wordCount);
}
}
