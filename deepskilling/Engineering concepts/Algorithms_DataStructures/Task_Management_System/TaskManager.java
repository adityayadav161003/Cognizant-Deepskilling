class Task {
    String taskId;
    String name;
    Task next;
    public Task(String id, String name) { this.taskId = id; this.name = name; }
}

public class TaskManager {
    private Task head = null;

    public void add(Task t) {
        t.next = head;
        head = t;
    }
    public Task search(String id) {
        Task curr = head;
        while (curr != null) {
            if (curr.taskId.equals(id)) return curr;
            curr = curr.next;
        }
        return null;
    }
    public void delete(String id) {
        if (head == null) return;
        if (head.taskId.equals(id)) { head = head.next; return; }
        Task curr = head;
        while (curr.next != null) {
            if (curr.next.taskId.equals(id)) {
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }
}
