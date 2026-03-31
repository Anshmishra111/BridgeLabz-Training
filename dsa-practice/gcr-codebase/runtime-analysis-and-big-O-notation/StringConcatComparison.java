package runtime_analysis_and_big_O_notation;


//Q3_StringConcatComparison.java
//Compare String, StringBuilder, StringBuffer concatenation

public class StringConcatComparison {
 public static String concatWithString(int n) {
     String s = "";
     for (int i = 0; i < n; i++) s += "a";
     return s;
 }

 public static String concatWithBuilder(int n) {
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < n; i++) sb.append("a");
     return sb.toString();
 }

 public static String concatWithBuffer(int n) {
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < n; i++) sb.append("a");
     return sb.toString();
 }
}