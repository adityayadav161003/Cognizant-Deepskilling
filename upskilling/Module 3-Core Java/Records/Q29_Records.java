import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Define a record named Person
record Person(String name, int age) {}

public class Q29_Records {
    public static void main(String[] args) {
        // Create instances and print them
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 17);
        Person person3 = new Person("Charlie", 32);
        Person person4 = new Person("Diana", 15);
        Person person5 = new Person("Evan", 21);

        System.out.println("=== Person Record Instances ===");
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
        System.out.println("Name of Person 1: " + person1.name());
        System.out.println("Age of Person 1: " + person1.age());
        
        // Use records in a List
        List<Person> people = Arrays.asList(person1, person2, person3, person4, person5);
        System.out.println("\nAll people: " + people);

        // Filter based on age using Streams (e.g., age >= 18)
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        System.out.println("\nAdults (age >= 18):");
        adults.forEach(p -> System.out.println(" - " + p.name() + " (" + p.age() + ")"));
    }
}
