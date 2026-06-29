import java.util.Scanner;

public class Q09_GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter marks obtained (out of 100): ");
        
        if (scanner.hasNextDouble()) {
            double marks = scanner.nextDouble();
            
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 and 100.");
            } else {
                char grade;
                if (marks >= 90) {
                    grade = 'A';
                } else if (marks >= 80) {
                    grade = 'B';
                } else if (marks >= 70) {
                    grade = 'C';
                } else if (marks >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                System.out.println("Assigned Grade: " + grade);
            }
        } else {
            System.out.println("Error: Invalid marks entered.");
        }
        
        scanner.close();
    }
}
