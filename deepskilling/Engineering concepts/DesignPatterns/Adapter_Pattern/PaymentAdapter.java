interface PaymentProcessor { void process(double amt); }
class ThirdPartyGateway { public void makePayment(double amt) { System.out.println("Paid " + amt); } }

class PaymentAdapter implements PaymentProcessor {
    private ThirdPartyGateway gateway;
    public PaymentAdapter(ThirdPartyGateway g) { this.gateway = g; }
    public void process(double amt) { gateway.makePayment(amt); }
}
