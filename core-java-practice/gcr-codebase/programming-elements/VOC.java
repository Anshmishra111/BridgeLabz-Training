import java.util.Scanner;
class VOC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter r: ");

        double radius = sc.nextDouble();

        System.out.print("Enter h: ");
        
        double height = sc.nextDouble();

        double volume = Math.PI * radius * radius * height;
        System.out.println("Volume of Cylinder = " + volume);
    }
}
