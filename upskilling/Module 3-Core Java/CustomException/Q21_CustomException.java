import java.util.Scanner;

// Custom Exception class
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Q21_CustomException {
    // Method that throws custom exception
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age is less than 18. Access Denied.");
        } else {
            System.out.println("Age is valid. Access Granted.");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        
        if (scanner.hasNextInt()) {
            int age = scanner.nextInt();
            try {
                checkAge(age);
            } catch (InvalidAgeException e) {
                System.out.println("Caught Custom Exception: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Invalid input. Please enter an integer.");
        }
        
        scanner.close();
    }
}
