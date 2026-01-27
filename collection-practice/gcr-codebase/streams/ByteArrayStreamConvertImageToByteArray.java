package streams;

import java.io.*;

public class ByteArrayStreamConvertImageToByteArray {
    public static void main(String[] args) {

        try {
            // Input image file
            FileInputStream fis = new FileInputStream("input.jpg");

            // Convert image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int data;
            while ((data = fis.read()) != -1) {
                baos.write(data);
            }

            byte[] imageBytes = baos.toByteArray();

            // Read from byte array
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);

            // Write to new image file
            FileOutputStream fos = new FileOutputStream("output.jpg");
            while ((data = bais.read()) != -1) {
                fos.write(data);
            }

            fis.close();
            baos.close();
            bais.close();
            fos.close();

            System.out.println("Image copied successfully using ByteArray streams.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
