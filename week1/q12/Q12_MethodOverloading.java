public class Q12_MethodOverloading {
    // 1. Two integers
    public static int add(int a, int b) {
        return a + b;
    }
    
    // 2. Two doubles
    public static double add(double a, double b) {
        return a + b;
    }
    
    // 3. Three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static void main(String[] args) {
        System.out.println("Calling add(5, 10) [two integers]: " + add(5, 10));
        System.out.println("Calling add(5.5, 10.2) [two doubles]: " + add(5.5, 10.2));
        System.out.println("Calling add(5, 10, 15) [three integers]: " + add(5, 10, 15));
    }
}
