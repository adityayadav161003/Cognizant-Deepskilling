import java.util.Scanner;
import java.util.Random;

public class Q10_NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int targetNumber = random.nextInt(100) + 1; // 1 to 100
        int attempts = 0;
        int guess = 0;
        
        System.out.println("=== Number Guessing Game ===");
        System.out.println("I have selected a random number between 1 and 100.");
        System.out.println("Can you guess what it is?");
        
        while (guess != targetNumber) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                attempts++;
                
                if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else if (guess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                    System.out.println("Total attempts: " + attempts);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clear the invalid input
            }
        }
        
        scanner.close();
    }
}
