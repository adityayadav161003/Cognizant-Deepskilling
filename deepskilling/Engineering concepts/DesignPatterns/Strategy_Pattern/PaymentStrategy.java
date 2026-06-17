interface PaymentStrategy { void pay(double amt); }
class PayPalPayment implements PaymentStrategy { public void pay(double amt) { System.out.println("PayPal: " + amt); } }

class Context {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy s) { this.strategy = s; }
    public void executePayment(double amt) { strategy.pay(amt); }
}
