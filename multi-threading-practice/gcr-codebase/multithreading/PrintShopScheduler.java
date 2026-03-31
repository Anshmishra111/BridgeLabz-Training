package multithreading;

class PrintJob implements Runnable {
    String jobName;
    int pages;
    int priority;

    PrintJob(String jobName, int pages, int priority) {
        this.jobName = jobName;
        this.pages = pages;
        this.priority = priority;
    }

    public void run() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= pages; i++) {
            try {
                Thread.sleep(100); // 100ms per page
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[" + priorityLabel() + "] Printing " +
                    jobName + " - Page " + i + " of " + pages);
        }

        long end = System.currentTimeMillis();
        System.out.println(jobName + " completed in " + (end - start) + " ms");
    }

    String priorityLabel() {
        if (priority >= 7) return "High Priority";
        if (priority >= 5) return "Medium Priority";
        return "Low Priority";
    }
}

public class PrintShopScheduler {
    public static void main(String[] args) throws Exception {

        long totalStart = System.currentTimeMillis();
        System.out.println("Starting print jobs...");

        Thread t1 = new Thread(new PrintJob("Job1", 10, 5));
        Thread t2 = new Thread(new PrintJob("Job2", 5, 8));
        Thread t3 = new Thread(new PrintJob("Job3", 15, 3));
        Thread t4 = new Thread(new PrintJob("Job4", 8, 6));
        Thread t5 = new Thread(new PrintJob("Job5", 12, 7));

        // Set thread priorities
        t1.setPriority(5);
        t2.setPriority(8);
        t3.setPriority(3);
        t4.setPriority(6);
        t5.setPriority(7);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for all jobs to finish
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        long totalEnd = System.currentTimeMillis();
        System.out.println("All jobs completed in " +
                (totalEnd - totalStart) + " ms");
    }
}
