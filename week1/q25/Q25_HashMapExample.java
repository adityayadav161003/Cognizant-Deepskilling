import java.util.HashMap;
import java.util.Scanner;

public class Q25_HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student ID to Name Mapping ===");
        System.out.println("1. Add Student Entry");
        System.out.println("2. Retrieve Student by ID");
        System.out.println("3. Exit");
        
        boolean running = true;
        while (running) {
            System.out.print("\nChoose an option (1, 2, 3): ");
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (option) {
                    case 1:
                        System.out.print("Enter Student ID (Integer): ");
                        if (scanner.hasNextInt()) {
                            int id = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            System.out.print("Enter Student Name: ");
                            String name = scanner.nextLine().trim();
                            
                            if (!name.isEmpty()) {
                                studentMap.put(id, name);
                                System.out.println("Added: ID " + id + " -> " + name);
                            } else {
                                System.out.println("Name cannot be empty.");
                            }
                        } else {
                            System.out.println("Invalid ID. ID must be an integer.");
                            scanner.nextLine(); // clear invalid input
                        }
                        break;
                    case 2:
                        System.out.print("Enter Student ID to search: ");
                        if (scanner.hasNextInt()) {
                            int id = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            if (studentMap.containsKey(id)) {
                                System.out.println("Student Found: ID " + id + " -> " + studentMap.get(id));
                            } else {
                                System.out.println("Student with ID " + id + " not found.");
                            }
                        } else {
                            System.out.println("Invalid ID. ID must be an integer.");
                            scanner.nextLine(); // clear invalid input
                        }
                        break;
                    case 3:
                        System.out.println("Exiting Student Map Manager.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid option. Please enter 1, 2, or 3.");
                scanner.nextLine(); // clear invalid input
            }
        }
        
        scanner.close();
    }
}
