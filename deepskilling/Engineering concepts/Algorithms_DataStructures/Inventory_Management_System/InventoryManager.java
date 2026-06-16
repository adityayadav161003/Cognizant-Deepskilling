import java.util.HashMap;

class Product {
    String productId;
    String productName;
    int quantity;
    double price;

    public Product(String id, String name, int q, double p) {
        this.productId = id;
        this.productName = name;
        this.quantity = q;
        this.price = p;
    }
}

public class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void add(Product p) { inventory.put(p.productId, p); }
    public void update(String id, int quantity) { if (inventory.containsKey(id)) inventory.get(id).quantity = quantity; }
    public void delete(String id) { inventory.remove(id); }

    // Analysis: HashMap has O(1) average time complexity for add, update, delete operations.
}
