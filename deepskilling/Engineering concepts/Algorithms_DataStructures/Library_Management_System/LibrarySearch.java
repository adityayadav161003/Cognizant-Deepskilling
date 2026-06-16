import java.util.Arrays;

class Book implements Comparable<Book> {
    String title;
    public Book(String title) { this.title = title; }
    public int compareTo(Book o) { return this.title.compareTo(o.title); }
}

public class LibrarySearch {
    public static int linearSearch(Book[] arr, String title) {
        for (int i = 0; i < arr.length; i++) if (arr[i].title.equalsIgnoreCase(title)) return i;
        return -1;
    }
    public static int binarySearch(Book[] arr, String title) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int cmp = arr[m].title.compareToIgnoreCase(title);
            if (cmp == 0) return m;
            if (cmp < 0) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }
}
