package streams;

import java.io.*;

public class DataStreamsStoreAndRetrievePrimitiveData{
    public static void main(String[] args) {

        int rollNo = 101;
        String name = "Ansh";
        double gpa = 8.75;

        // -------- Writing primitive data --------
        try {
            DataOutputStream dos =
                    new DataOutputStream(new FileOutputStream("student.dat"));

            dos.writeInt(rollNo);
            dos.writeUTF(name);
            dos.writeDouble(gpa);

            dos.close();
            System.out.println("Student data stored successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // -------- Reading primitive data --------
        try {
            DataInputStream dis =
                    new DataInputStream(new FileInputStream("student.dat"));

            int r = dis.readInt();
            String n = dis.readUTF();
            double g = dis.readDouble();

            dis.close();

            System.out.println("\nRetrieved Student Details:");
            System.out.println("Roll No: " + r);
            System.out.println("Name: " + n);
            System.out.println("GPA: " + g);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
