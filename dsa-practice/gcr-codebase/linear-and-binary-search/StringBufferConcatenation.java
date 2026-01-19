package linear_and_binary_search;

public class StringBufferConcatenation {
public static void main(String[] args) {
	String[] words= {"Java"," ","is"," ","powerful"};
	//step 1:create string buffer object
	StringBuffer buffer=new StringBuffer();
	//Step 2: append each string
	for(String word:words) {
		buffer.append(word);
	}
	//step3: convert to string
	String result=buffer.toString();
	System.out.println("Concatenated string: " + result);
}
}
