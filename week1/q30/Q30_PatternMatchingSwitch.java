public class Q30_PatternMatchingSwitch {
    // Method that accepts Object and uses pattern matching in switch
    public static String getObjectTypeInfo(Object obj) {
        return switch (obj) {
            case null -> "The object is null.";
            case Integer i -> "The object is an Integer: " + i + " (Value squared: " + (i * i) + ")";
            case String s -> "The object is a String: \"" + s + "\" (Length: " + s.length() + ")";
            case Double d -> "The object is a Double: " + d + " (Value halved: " + (d / 2.0) + ")";
            case Boolean b -> "The object is a Boolean: " + b + " (Inverse: " + (!b) + ")";
            default -> "The object is of an unknown type: " + obj.getClass().getSimpleName();
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Pattern Matching for switch (Java 21) ===");

        Object[] testObjects = {
            10,
            "Hello, Java 21!",
            3.14159,
            true,
            new Object(),
            null
        };

        for (Object obj : testObjects) {
            System.out.println("Testing object: " + (obj == null ? "null" : obj));
            System.out.println("Result: " + getObjectTypeInfo(obj));
            System.out.println();
        }
    }
}
