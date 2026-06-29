import java.util.Scanner;

public class Q14_ArraySumAndAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Error: Number of elements must be greater than zero.");
            } else {
                double[] array = new double[n];
                System.out.println("Enter " + n + " elements:");
                double sum = 0;
                boolean valid = true;
                
                for (int i = 0; i < n; i++) {
                    System.out.print("Element " + (i + 1) + ": ");
                    if (scanner.hasNextDouble()) {
                        array[i] = scanner.nextDouble();
                        sum += array[i];
                    } else {
                        System.out.println("Error: Invalid number.");
                        valid = false;
                        break;
                    }
                }
                
                if (valid) {
                    double average = sum / n;
                    System.out.printf("Sum of elements: %.2f\n", sum);
                    System.out.printf("Average of elements: %.2f\n", average);
                }
            }
        } else {
            System.out.println("Error: Invalid number of elements.");
        }
        
        scanner.close();
    }
}
