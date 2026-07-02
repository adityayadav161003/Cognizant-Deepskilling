import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q28_StreamAPI {
    public static void main(String[] args) {
        // Create a List of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 22, 33, 44);
        
        System.out.println("Original numbers list: " + numbers);
        
        // Filter even numbers and collect the result
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
                                           
        System.out.println("Filtered even numbers list: " + evenNumbers);
        
        // Additional stream processing: printing them individually using forEach
        System.out.print("Even numbers formatted: ");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(n -> System.out.print("[" + n + "] "));
        System.out.println();
    }
}
