abstract class OrderProcess {
    public final void processOrder() { select(); payment(); deliver(); }
    public abstract void select();
    public abstract void payment();
    public void deliver() { System.out.println("Item delivered."); }
}

class ElectronicsOrder extends OrderProcess {
    public void select() { System.out.println("Select electronic item."); }
    public void payment() { System.out.println("Pay for electronics."); }
}
