import java.util.ArrayList;
import java.util.Scanner;

public class Q24_ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student Names Manager ===");
        System.out.println("Type names to add them to the list. Type 'done' to finish.");
        
        while (true) {
            System.out.print("Enter student name: ");
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) {
                    break;
                }
                if (!input.isEmpty()) {
                    studentNames.add(input);
                    System.out.println("Added: " + input);
                } else {
                    System.out.println("Name cannot be empty.");
                }
            } else {
                break;
            }
        }
        
        System.out.println("\n--- All Student Names Entered ---");
        if (studentNames.isEmpty()) {
            System.out.println("No student names were entered.");
        } else {
            for (int i = 0; i < studentNames.size(); i++) {
                System.out.println((i + 1) + ". " + studentNames.get(i));
            }
        }
        
        scanner.close();
    }
}
