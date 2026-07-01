import java.util.Scanner;

public class Q16_PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            // Clean the string: remove non-alphanumeric characters and convert to lowercase
            String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            
            // Reverse the cleaned string
            String reversed = new StringBuilder(cleaned).reverse().toString();
            
            System.out.println("Cleaned string: \"" + cleaned + "\"");
            if (cleaned.equals(reversed)) {
                System.out.println("Result: The string is a palindrome.");
            } else {
                System.out.println("Result: The string is not a palindrome.");
            }
        } else {
            System.out.println("Error: No input found.");
        }
        
        scanner.close();
    }
}
