// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
import java.util.Random;
public class VotingEligibility {
    

    static int[] generateAges(int n) {
        int[] ages = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            ages[i] = rand.nextInt(90) + 10;
        }
        return ages;
    }
    static String[][] checkVotingEligibility(int[] ages) {
        String[][] result = new String[ages.length][2];

        for (int i = 0; i < ages.length; i++) {
            result[i][0] = String.valueOf(ages[i]);
            if (ages[i] < 0) {
                result[i][1] = "false";
            } else if (ages[i] >= 18) {
                result[i][1] = "true";
            } else {
                result[i][1] = "false";
            }
        }
        return result;
    }
    static void displayResult(String[][] arr) {
        System.out.println("\nAge\tCan Vote");
        System.out.println("----------------");

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + "\t" + arr[i][1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        int[] ages = generateAges(n);
        String[][] result = checkVotingEligibility(ages);
        displayResult(result);

        sc.close();
    }
}

    

