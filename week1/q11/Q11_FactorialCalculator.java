import java.util.Scanner;

public class Q11_FactorialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            
            if (number < 0) {
                System.out.println("Error: Factorial is not defined for negative numbers.");
            } else {
                long factorial = 1; // using long to support values up to 20
                for (int i = 1; i <= number; i++) {
                    factorial *= i;
                }
                System.out.println("Factorial of " + number + " is " + factorial + ".");
            }
        } else {
            System.out.println("Error: Please enter a valid integer.");
        }
        
        scanner.close();
    }
}
