import java.util.Scanner;

public class Q05_MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number for its multiplication table: ");
        if (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            System.out.println("Multiplication Table for " + number + ":");
            for (int i = 1; i <= 10; i++) {
                System.out.printf("%.2f x %d = %.2f\n", number, i, number * i);
            }
        } else {
            System.out.println("Error: Invalid number.");
        }
        scanner.close();
    }
}
