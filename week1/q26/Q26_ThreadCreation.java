// Method 1: Extending Thread class
class ThreadA extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[Thread A (Extends Thread)] Message count: " + i);
            try {
                Thread.sleep(100); // sleep for 100ms
            } catch (InterruptedException e) {
                System.out.println("Thread A was interrupted.");
            }
        }
    }
}

// Method 2: Implementing Runnable interface
class RunnableB implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[Thread B (Implements Runnable)] Message count: " + i);
            try {
                Thread.sleep(120); // sleep for 120ms
            } catch (InterruptedException e) {
                System.out.println("Thread B was interrupted.");
            }
        }
    }
}

public class Q26_ThreadCreation {
    public static void main(String[] args) {
        System.out.println("=== Thread Creation Demo ===");

        // Instantiating thread classes
        ThreadA threadA = new ThreadA();
        Thread threadB = new Thread(new RunnableB());

        // Starting both threads (calls their run() methods in parallel)
        System.out.println("Starting Thread A and Thread B...");
        threadA.start();
        threadB.start();

        // Wait for both threads to finish to coordinate main output
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Both threads have finished execution.");
    }
}
