package linear_and_binary_search;

import java.util.HashSet;

public class RemoveDuplicatesUsingStringBuilder {
	public static void main(String[] args) {
		String input="programming";
		StringBuilder sb=new StringBuilder();
		HashSet<Character> seen=new HashSet<>();
		for(char ch:input.toCharArray()) {
			if(!seen.contains(ch)) {
				seen.add(ch);
				sb.append(ch);
			}
		}
		String output=sb.toString();
		System.out.println("Original String : " + input);
		System.out.println("Without Duplicates : " + output);
	}
}
