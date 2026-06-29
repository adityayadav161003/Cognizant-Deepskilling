import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class ReflectionTarget {
    public void greet(String name) {
        System.out.println("ReflectionTarget: Hello, " + name + "!");
    }

    public int add(int a, int b) {
        return a + b;
    }

    private void secretMethod() {
        System.out.println("ReflectionTarget: This is a private secret method!");
    }
}

public class Q39_ReflectionDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Reflection Demonstration ===");

        String className = "ReflectionTarget";

        try {
            // 1. Load the class using Class.forName()
            Class<?> clazz = Class.forName(className);
            System.out.println("Successfully loaded class: " + clazz.getName());

            // 2. Print all declared methods and their parameters
            System.out.println("\n--- Declared Methods ---");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.print("Method Name: " + method.getName() + " | Return Type: " + method.getReturnType().getSimpleName() + " | Parameters: ");
                
                Parameter[] parameters = method.getParameters();
                if (parameters.length == 0) {
                    System.out.print("None");
                } else {
                    for (int i = 0; i < parameters.length; i++) {
                        System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
                        if (i < parameters.length - 1) System.out.print(", ");
                    }
                }
                System.out.println();
            }

            // 3. Create an instance dynamically
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();

            // 4. Invoke methods dynamically using invoke()
            System.out.println("\n--- Invoking Methods Dynamically ---");

            // Invoke public greet(String)
            Method greetMethod = clazz.getMethod("greet", String.class);
            System.out.println("Invoking greet(\"Alice\")...");
            greetMethod.invoke(instance, "Alice");

            // Invoke public add(int, int)
            Method addMethod = clazz.getMethod("add", int.class, int.class);
            System.out.println("\nInvoking add(15, 27)...");
            Object sumResult = addMethod.invoke(instance, 15, 27);
            System.out.println("Result returned from add: " + sumResult);

            // Invoke private secretMethod() (Requires setting accessible to true)
            Method secretMethod = clazz.getDeclaredMethod("secretMethod");
            System.out.println("\nInvoking private secretMethod()...");
            secretMethod.setAccessible(true); // Bypass private access modifier!
            secretMethod.invoke(instance);

        } catch (Exception e) {
            System.err.println("Reflection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
