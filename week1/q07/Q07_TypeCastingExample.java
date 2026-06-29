public class Q07_TypeCastingExample {
    public static void main(String[] args) {
        // Double to int (Narrowing/Explicit casting)
        double originalDouble = 99.99;
        int castedInt = (int) originalDouble;
        System.out.println("Original double value: " + originalDouble);
        System.out.println("Casted int value (narrowing): " + castedInt);
        
        // Int to double (Widening/Implicit casting)
        int originalInt = 42;
        double castedDouble = originalInt; // implicit casting
        System.out.println("\nOriginal int value: " + originalInt);
        System.out.println("Casted double value (widening): " + castedDouble);
        
        // Explicitly casting int to double for demonstration
        double explicitCastedDouble = (double) originalInt;
        System.out.println("Explicitly casted double value: " + explicitCastedDouble);
    }
}
