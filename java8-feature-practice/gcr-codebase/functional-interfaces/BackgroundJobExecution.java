package functional_interfaces;

public class BackgroundJobExecution {
    public static void main(String[] args) {

        Runnable job = () -> {
            System.out.println("Background job is running...");
        };

        Thread t = new Thread(job);
        t.start();

        System.out.println("Main thread continues execution");
    }
}
