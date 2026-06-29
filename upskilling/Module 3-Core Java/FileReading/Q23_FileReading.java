import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Q23_FileReading {
    public static void main(String[] args) {
        System.out.println("Reading contents of output.txt:");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: output.txt was not found. Please run Q22_FileWriting first to create it.");
        } catch (IOException e) {
            System.out.println("Error: An error occurred while reading output.txt: " + e.getMessage());
        }
    }
}
