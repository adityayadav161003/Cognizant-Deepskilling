import java.util.Scanner;

public class Q03_EvenOrOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number % 2 == 0) {
                System.out.println(number + " is even.");
            } else {
                System.out.println(number + " is odd.");
            }
        } else {
            System.out.println("Error: Input is not a valid integer.");
        }
        scanner.close();
    }
}
