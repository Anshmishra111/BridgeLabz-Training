import java.util.Scanner;
class SI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter p: ");

        double principal = sc.nextDouble();

        System.out.print("Enter r: ");

        double rate = sc.nextDouble();

        System.out.print("Enter t: ");

        double time = sc.nextDouble();

        double si = (principal * rate * time) / 100;
        System.out.println("Simple Interest = " + si);
    }
}
