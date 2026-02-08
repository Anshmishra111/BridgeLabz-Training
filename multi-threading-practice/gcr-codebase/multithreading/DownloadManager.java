package multithreading;

class FileDownloader implements Runnable {
    String fileName;

    FileDownloader(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        for (int i = 0; i <= 100; i += 25) {
            try {
                Thread.sleep((int)(Math.random() * 1000)); // random delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[" + Thread.currentThread().getName() +
                    "] Downloading " + fileName + ": " + i + "%");
        }
    }
}

public class DownloadManager {
    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new FileDownloader("Document.pdf"));
        Thread t2 = new Thread(new FileDownloader("Image.jpg"));
        Thread t3 = new Thread(new FileDownloader("Video.mp4"));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("All downloads complete!");
    }
}
