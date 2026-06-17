class Student {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

class StudentView {
    public void printDetails(String name) { System.out.println("Student Name: " + name); }
}

class StudentController {
    private Student model;
    private StudentView view;
    public StudentController(Student m, StudentView v) { this.model = m; this.view = v; }
    public void updateView() { view.printDetails(model.getName()); }
}
