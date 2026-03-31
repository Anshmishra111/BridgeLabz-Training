import java.util.Scanner;
class CelsiusToFarenheit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double cel = sc.nextDouble();
        double fahren = (cel * 9 / 5) + 32;
        System.out.println("Fahrenheit = " + fahren);
        sc.close();
    }
}
