import java.util.Scanner;

public class Q20_TryCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Division Handler ===");
        
        try {
            System.out.print("Enter the first integer (numerator): ");
            int numerator = scanner.nextInt();
            
            System.out.print("Enter the second integer (denominator): ");
            int denominator = scanner.nextInt();
            
            // This operation can throw ArithmeticException
            int result = numerator / denominator;
            System.out.println("Result of division: " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("Error caught: ArithmeticException - Division by zero is not allowed.");
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage() + ". Please enter valid integers.");
        } finally {
            scanner.close();
        }
    }
}
