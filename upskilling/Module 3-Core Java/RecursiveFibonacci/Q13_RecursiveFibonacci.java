import java.util.Scanner;

public class Q13_RecursiveFibonacci {
    // Recursive method to find the nth Fibonacci number
    // Sequence starts: fib(1)=0, fib(2)=1, fib(3)=1, fib(4)=2, ...
    // Or fib(0)=0, fib(1)=1, fib(2)=1, ... We will use standard math: fib(1)=1, fib(2)=1, fib(3)=2, ...
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer n: ");
        
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Error: Please enter a positive integer (greater than 0).");
            } else {
                // Avoid too large numbers to prevent stack overflow or slow execution
                if (n > 40) {
                    System.out.println("Warning: Numbers larger than 40 can take significant time to calculate recursively.");
                }
                int result = fibonacci(n);
                System.out.println("The " + n + "-th Fibonacci number is: " + result);
            }
        } else {
            System.out.println("Error: Please enter a valid integer.");
        }
        
        scanner.close();
    }
}
