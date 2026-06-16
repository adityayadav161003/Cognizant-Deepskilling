import java.util.Arrays;

class Product implements Comparable<Product> {
    String id;
    String name;

    public Product(String id, String name) { this.id = id; this.name = name; }
    public int compareTo(Product o) { return this.id.compareTo(o.id); }
}

public class SearchFunction {
    public static int linearSearch(Product[] arr, String targetId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].id.equals(targetId)) return i;
        }
        return -1;
    }

    public static int binarySearch(Product[] arr, String targetId) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int cmp = arr[m].id.compareTo(targetId);
            if (cmp == 0) return m;
            if (cmp < 0) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }
    // Comparison: Linear Search is O(n), Binary Search is O(log n) but requires a sorted array.
}
