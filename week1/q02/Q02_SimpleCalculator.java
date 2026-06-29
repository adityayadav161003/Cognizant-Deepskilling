import java.util.Scanner;

public class Q02_SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Simple Calculator ===");
        
        try {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            
            System.out.print("Choose an operation (+, -, *, /): ");
            char operation = scanner.next().charAt(0);
            
            double result = 0;
            boolean validOperation = true;
            
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        validOperation = false;
                    } else {
                        result = num1 / num2;
                    }
                    break;
                default:
                    System.out.println("Error: Invalid operation chosen.");
                    validOperation = false;
            }
            
            if (validOperation) {
                System.out.printf("Result: %.2f %c %.2f = %.2f\n", num1, operation, num2, result);
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numbers.");
        } finally {
            scanner.close();
        }
    }
}
