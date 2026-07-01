import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Pineapple");
        fruits.add("Cherry");
        
        System.out.println("Original list: " + fruits);
        
        // Sorting lexicographically (alphabetical order) using a lambda expression
        Collections.sort(fruits, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted alphabetically: " + fruits);
        
        // Sorting by string length using a lambda expression
        Collections.sort(fruits, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sorted by string length: " + fruits);
        
        // Sorting in reverse alphabetical order using lambda expression
        Collections.sort(fruits, (s1, s2) -> s2.compareTo(s1));
        System.out.println("Sorted in reverse alphabetical order: " + fruits);
    }
}
