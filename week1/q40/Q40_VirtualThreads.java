import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Q40_VirtualThreads {
    private static final int VIRTUAL_THREAD_COUNT = 100_000;
    private static final int TRADITIONAL_THREAD_COUNT = 2_000; // Small count to avoid crashing JVM/OS

    public static void main(String[] args) {
        System.out.println("=== Java 21 Virtual Threads Performance Demo ===");
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("Java Version: " + System.getProperty("java.version"));

        // 1. Virtual Threads Test
        System.out.println("\n1. Launching " + VIRTUAL_THREAD_COUNT + " Virtual Threads...");
        AtomicInteger virtualCounter = new AtomicInteger(0);
        List<Thread> virtualThreads = new ArrayList<>(VIRTUAL_THREAD_COUNT);

        Instant startVirtual = Instant.now();
        for (int i = 0; i < VIRTUAL_THREAD_COUNT; i++) {
            Thread vThread = Thread.startVirtualThread(() -> {
                virtualCounter.incrementAndGet();
            });
            virtualThreads.add(vThread);
        }

        // Wait for all virtual threads to finish
        for (Thread vt : virtualThreads) {
            try {
                vt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant endVirtual = Instant.now();
        Duration durationVirtual = Duration.between(startVirtual, endVirtual);

        System.out.println("   Completed!");
        System.out.println("   Virtual Threads launched and completed: " + virtualCounter.get());
        System.out.println("   Time taken: " + durationVirtual.toMillis() + " ms (" + (durationVirtual.toMillis() / 1000.0) + " seconds)");

        // 2. Traditional Platform Threads Test
        System.out.println("\n2. Launching " + TRADITIONAL_THREAD_COUNT + " Traditional (Platform) Threads...");
        AtomicInteger traditionalCounter = new AtomicInteger(0);
        List<Thread> platformThreads = new ArrayList<>(TRADITIONAL_THREAD_COUNT);

        Instant startPlatform = Instant.now();
        for (int i = 0; i < TRADITIONAL_THREAD_COUNT; i++) {
            Thread pThread = new Thread(() -> {
                traditionalCounter.incrementAndGet();
            });
            platformThreads.add(pThread);
            pThread.start();
        }

        // Wait for all platform threads to finish
        for (Thread pt : platformThreads) {
            try {
                pt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant endPlatform = Instant.now();
        Duration durationPlatform = Duration.between(startPlatform, endPlatform);

        System.out.println("   Completed!");
        System.out.println("   Traditional Threads launched and completed: " + traditionalCounter.get());
        System.out.println("   Time taken: " + durationPlatform.toMillis() + " ms (" + (durationPlatform.toMillis() / 1000.0) + " seconds)");

        // 3. Performance Summary
        System.out.println("\n=== Performance Summary ===");
        double rateVirtual = (double) VIRTUAL_THREAD_COUNT / durationVirtual.toMillis() * 1000.0;
        double ratePlatform = (double) TRADITIONAL_THREAD_COUNT / durationPlatform.toMillis() * 1000.0;
        System.out.printf("Virtual Threads Rate: %.2f threads/second\n", rateVirtual);
        System.out.printf("Traditional Threads Rate: %.2f threads/second\n", ratePlatform);
        System.out.printf("Virtual threads are approx. %.1fx faster to create and run!\n", (rateVirtual / ratePlatform));
        System.out.println("Note: Launching 100,000 traditional threads would typically crash the JVM or cause an OutOfMemoryError due to OS stack limitations, while virtual threads ran seamlessly on the heap.");
    }
}
