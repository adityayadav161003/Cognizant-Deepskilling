import java.util.Scanner;

public class Q15_StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to reverse: ");
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            // Method 1: Using StringBuilder
            String reversedBuilder = new StringBuilder(input).reverse().toString();
            
            // Method 2: Using a loop (for verification of manual loop logic)
            char[] charArray = input.toCharArray();
            StringBuilder reversedLoop = new StringBuilder();
            for (int i = charArray.length - 1; i >= 0; i--) {
                reversedLoop.append(charArray[i]);
            }
            
            System.out.println("Reversed string (using StringBuilder): " + reversedBuilder);
            System.out.println("Reversed string (using manual loop): " + reversedLoop.toString());
        } else {
            System.out.println("Error: No input found.");
        }
        scanner.close();
    }
}
