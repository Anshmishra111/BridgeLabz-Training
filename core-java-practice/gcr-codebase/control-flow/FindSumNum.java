import java.util.Scanner;

public class FindSumNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            double sum = 0.0;
            double value;
            value=sc.nextDouble();
        while (value != 0) {
            sum = sum + value;
            value = sc.nextDouble();
        }
        
        System.out.println("The total sum is " + sum);   
        sc.close();
     }

    
}
