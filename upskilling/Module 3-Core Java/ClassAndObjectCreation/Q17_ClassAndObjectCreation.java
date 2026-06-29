// Car class definition
class Car {
    private String make;
    private String model;
    private int year;

    // Constructor
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Method to display details
    public void displayDetails() {
        System.out.println("Car Details: " + year + " " + make + " " + model);
    }
}

public class Q17_ClassAndObjectCreation {
    public static void main(String[] args) {
        // Creating objects of the Car class
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Tesla", "Model 3", 2024);
        Car car3 = new Car("Ford", "Mustang", 1969);

        // Calling displayDetails() method on each object
        car1.displayDetails();
        car2.displayDetails();
        car3.displayDetails();
    }
}
