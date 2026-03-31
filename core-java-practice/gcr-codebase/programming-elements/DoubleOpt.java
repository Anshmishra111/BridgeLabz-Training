import java.util.Scanner;
public class DoubleOpt{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double x, y, z;
        System.out.print("Enter value of x: ");
        x = sc.nextDouble();
        System.out.print("Enter value of y: ");
        y = sc.nextDouble();
        System.out.print("Enter value of z: ");
        z = sc.nextDouble();
        double result1 =  x+ y * z;
        double result2 = x * y + z;
        double result3 = z + x / y;
        double result4 = x % y +z;
        System.out.println("The results of  Double Operations are " + result1 + ", " + result2 + ", " + result3 + ", and " + result4);
        sc.close();
    }
}

    