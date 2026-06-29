import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Q22_FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to write to output.txt: ");
        
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            // Writing to output.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write(input);
                System.out.println("Success: Data has been successfully written to output.txt.");
            } catch (IOException e) {
                System.out.println("Error: An error occurred while writing to output.txt: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No input entered.");
        }
        
        scanner.close();
    }
}
