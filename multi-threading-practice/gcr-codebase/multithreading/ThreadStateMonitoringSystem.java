package multithreading;

import java.util.Date;

class TaskRunner extends Thread {

    TaskRunner(String name) {
        super(name);
    }

    public void run() {
        // RUNNABLE (computation)
        for (int i = 0; i < 1000000; i++) {
            int x = i * i;
        }

        // TIMED_WAITING
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StateMonitor extends Thread {
    Thread t1, t2;
    int count1 = 0, count2 = 0;

    StateMonitor(Thread t1, Thread t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("[Monitor] " + t1.getName() +
                        " is in " + t1.getState() +
                        " state at " + new Date());

                System.out.println("[Monitor] " + t2.getName() +
                        " is in " + t2.getState() +
                        " state at " + new Date());

                count1++;
                count2++;

                if (t1.getState() == Thread.State.TERMINATED &&
                    t2.getState() == Thread.State.TERMINATED) {
                    break;
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Summary:");
        System.out.println(t1.getName() + " went through multiple states");
        System.out.println(t2.getName() + " went through multiple states");
    }
}

public class ThreadStateMonitoringSystem {
    public static void main(String[] args) {

        TaskRunner task1 = new TaskRunner("Task-1");
        TaskRunner task2 = new TaskRunner("Task-2");

        // NEW state
        System.out.println("[Main] " + task1.getName() +
                " is in " + task1.getState());

        StateMonitor monitor = new StateMonitor(task1, task2);

        task1.start();   // RUNNABLE
        task2.start();
        monitor.start();
    }
}
