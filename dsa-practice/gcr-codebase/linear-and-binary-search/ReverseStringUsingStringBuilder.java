package linear_and_binary_search;

public class ReverseStringUsingStringBuilder {
	public static void main(String[] args) {
String input="hello";
StringBuilder sb=new StringBuilder();
sb.append(input);
sb.reverse();
String reversed=sb.toString();
System.out.println("Original String : " + input);
System.out.println("Reversed String : " + reversed);
}

}
