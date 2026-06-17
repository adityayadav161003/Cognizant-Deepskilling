interface Notifier { void send(String msg); }
class EmailNotifier implements Notifier { public void send(String msg) { System.out.println("Email: " + msg); } }

abstract class NotifierDecorator implements Notifier {
    protected Notifier decorated;
    public NotifierDecorator(Notifier n) { this.decorated = n; }
    public void send(String msg) { decorated.send(msg); }
}

class SMSNotifier extends NotifierDecorator {
    public SMSNotifier(Notifier n) { super(n); }
    public void send(String msg) { super.send(msg); System.out.println("SMS: " + msg); }
}
