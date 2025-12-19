import java.util.Scanner;
public class IntO{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x, y, z;
        System.out.print("Enter value of x: ");
        x = sc.nextInt();
        System.out.print("Enter value of y: ");
        y = sc.nextInt();
        System.out.print("Enter value of z: ");
        z = sc.nextInt();
        int result1 =  x+ y * z;
        int result2 = x * y + z;
        int result3 = z + x / y;
        int result4 = x % y +z;
        System.out.println("The results of Int Operations are " + result1 + ", " + result2 + ", " + result3 + ", and " + result4);
        sc.close();
    }
}

    