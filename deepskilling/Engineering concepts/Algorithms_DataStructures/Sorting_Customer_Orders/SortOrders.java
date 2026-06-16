class Order {
    String id;
    double totalPrice;
    public Order(String id, double price) { this.id = id; this.totalPrice = price; }
}

public class SortOrders {
    public static void bubbleSort(Order[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].totalPrice > arr[j+1].totalPrice) {
                    Order temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    public static void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Order[] arr, int low, int high) {
        double pivot = arr[high].totalPrice;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].totalPrice < pivot) {
                i++;
                Order temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Order temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    // Bubble sort is O(n^2), Quick sort is O(n log n) average.
}
