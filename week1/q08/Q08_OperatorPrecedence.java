public class Q08_OperatorPrecedence {
    public static void main(String[] args) {
        // Expression 1: Multiplicative (*) has higher precedence than Additive (+)
        int expression1 = 10 + 5 * 2; 
        System.out.println("Expression: 10 + 5 * 2");
        System.out.println("Result: " + expression1);
        System.out.println("Explanation: '*' has higher precedence than '+'. So, 5 * 2 = 10, then 10 + 10 = 20.");
        
        // Expression 2: Parentheses override operator precedence
        int expression2 = (10 + 5) * 2;
        System.out.println("\nExpression: (10 + 5) * 2");
        System.out.println("Result: " + expression2);
        System.out.println("Explanation: Parentheses '()' have the highest precedence. So, (10 + 5) = 15, then 15 * 2 = 30.");
        
        // Expression 3: Division, multiplication, modulus, and addition
        int expression3 = 100 - 20 / 4 * 3 + 15 % 4;
        System.out.println("\nExpression: 100 - 20 / 4 * 3 + 15 % 4");
        System.out.println("Result: " + expression3);
        System.out.println("Explanation:");
        System.out.println("1. '/', '*', and '%' have equal precedence and are evaluated left-to-right (associativity):");
        System.out.println("   - 20 / 4 = 5");
        System.out.println("   - 5 * 3 = 15");
        System.out.println("   - 15 % 4 = 3");
        System.out.println("2. The expression simplifies to: 100 - 15 + 3");
        System.out.println("3. '-' and '+' have equal precedence, evaluated left-to-right:");
        System.out.println("   - 100 - 15 = 85");
        System.out.println("   - 85 + 3 = 88");
    }
}
