package method;
import java.util.Scanner;
public class YoungestAndTallestFriend {
    public static int findIndexOfMin(int[] arr) {
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[minIndex]) minIndex = i;
        return minIndex;
    }

    public static int findIndexOfMax(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[maxIndex]) maxIndex = i;
        return maxIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = { "Amar", "Akbar", "Anthony" };
        int[] ages = new int[3], heights = new int[3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter age of " + names[i] + ": ");
            ages[i] = sc.nextInt();
            System.out.print("Enter height of " + names[i] + ": ");
            heights[i] = sc.nextInt();
        }

        System.out.println("Youngest: " + names[findIndexOfMin(ages)]);
        System.out.println("Tallest: " + names[findIndexOfMax(heights)]);
    }
}


