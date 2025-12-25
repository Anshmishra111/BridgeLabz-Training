package method;
import java.util.Scanner;
public class NumberPropertiesAnalyzer {
    public static boolean isPositive(int num) {
        return num >= 0;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static int compare(int a, int b) {
        return Integer.compare(a, b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            nums[i] = sc.nextInt();
        }

        for (int num : nums) {
            if (isPositive(num)) {
                System.out.print(num + " is Positive and ");
                System.out.println(isEven(num) ? "Even" : "Odd");
            } else {
                System.out.println(num + " is Negative");
            }
        }

        int comparison = compare(nums[0], nums[4]);
        if (comparison == 0)
            System.out.println("First and Last elements are Equal");
        else if (comparison > 0)
            System.out.println("First element is Greater than Last");
        else
            System.out.println("First element is Less than Last");
    }
}

