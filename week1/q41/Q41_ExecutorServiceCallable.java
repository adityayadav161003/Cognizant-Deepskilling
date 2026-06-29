import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Callable task that calculates the square of a number and simulates work
class SquareCalculator implements Callable<Integer> {
    private final int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        // Simulate a small delay (e.g., 200 milliseconds)
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() + " calculated square of " + number);
        return number * number;
    }
}

public class Q41_ExecutorServiceCallable {
    public static void main(String[] args) {
        System.out.println("=== ExecutorService and Callable Concurrency Demo ===");

        // Create a fixed thread pool with 3 threads
        int threadPoolSize = 3;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        System.out.println("Thread pool initialized with " + threadPoolSize + " threads.");

        // List to hold Future objects
        List<Future<Integer>> futureList = new ArrayList<>();

        // Submit 5 tasks to the executor
        System.out.println("\nSubmitting 5 tasks to calculate squares of numbers 1 through 5...");
        for (int i = 1; i <= 5; i++) {
            Callable<Integer> task = new SquareCalculator(i);
            Future<Integer> future = executor.submit(task);
            futureList.add(future);
        }

        // Collect results using Future.get()
        System.out.println("\nRetrieving results from Futures (blocking operations)...");
        int sumOfSquares = 0;
        try {
            for (int i = 0; i < futureList.size(); i++) {
                Future<Integer> future = futureList.get(i);
                // Future.get() blocks until the specific task finishes and returns result
                int result = future.get();
                System.out.println("Task " + (i + 1) + " result: " + result);
                sumOfSquares += result;
            }
            System.out.println("\nSum of all squares: " + sumOfSquares);
        } catch (Exception e) {
            System.err.println("An error occurred during thread execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Shut down the executor service
            System.out.println("\nShutting down the executor service...");
            executor.shutdown();
            System.out.println("Executor service shut down.");
        }
    }
}
