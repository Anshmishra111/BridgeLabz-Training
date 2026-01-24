package streams;
import java.io.*;

public class BufferedStreams {

    private static final int BUFFER_SIZE = 4096; // 4 KB

    public static void main(String[] args) {

        String source = "largefile.dat";          // put a large file here (e.g. 100MB)
        String unbufferedDest = "copy_unbuffered.dat";
        String bufferedDest = "copy_buffered.dat";

        System.out.println("Starting File Copy...\n");

        copyUnbuffered(source, unbufferedDest);
        copyBuffered(source, bufferedDest);
    }

    // ================= Unbuffered Copy =================
    static void copyUnbuffered(String src, String dest) {
        long start = System.nanoTime();

        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Unbuffered Copy Time: " +
                    (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Error in unbuffered copy.");
            e.printStackTrace();
        }
    }

    // ================= Buffered Copy =================
    static void copyBuffered(String src, String dest) {
        long start = System.nanoTime();

        try (BufferedInputStream bis =
                     new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos =
                     new BufferedOutputStream(new FileOutputStream(dest))) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Buffered Copy Time:   " +
                    (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Error in buffered copy.");
            e.printStackTrace();
        }
    }
}
