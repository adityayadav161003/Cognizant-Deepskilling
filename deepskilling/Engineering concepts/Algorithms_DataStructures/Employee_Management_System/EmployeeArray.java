class Employee {
    String id;
    String name;
    public Employee(String id, String name) { this.id = id; this.name = name; }
}

public class EmployeeArray {
    private Employee[] arr = new Employee[100];
    private int size = 0;

    public void add(Employee e) { if (size < arr.length) arr[size++] = e; }
    public Employee search(String id) {
        for (int i = 0; i < size; i++) if (arr[i].id.equals(id)) return arr[i];
        return null;
    }
    public void delete(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].id.equals(id)) {
                arr[i] = arr[size - 1];
                arr[size - 1] = null;
                size--;
                return;
            }
        }
    }
}
