package runtime_analysis_and_big_O_notation;

import java.io.*;
public class FileReadingComparison {

    static void readUsingFileReader(String file) throws Exception {
        FileReader fr = new FileReader(file);
        while (fr.read() != -1) {}
        fr.close();
    }

    static void readUsingInputStreamReader(String file) throws Exception {
        InputStreamReader isr =
                new InputStreamReader(new FileInputStream(file));
        while (isr.read() != -1) {}
        isr.close();
    }

    public static void main(String[] args) throws Exception {

    	String filePath = "largefile.txt";
// large file

        long start = System.currentTimeMillis();
        readUsingFileReader(filePath);
        System.out.println("FileReader Time: " +
                (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        readUsingInputStreamReader(filePath);
        System.out.println("InputStreamReader Time: " +
                (System.currentTimeMillis() - start) + " ms");
    }
}
