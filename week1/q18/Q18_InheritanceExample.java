// Base class Animal
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a generic sound");
    }
}

// Subclass Dog extending Animal
class Dog extends Animal {
    // Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class Q18_InheritanceExample {
    public static void main(String[] args) {
        // Instantiate both classes
        Animal genericAnimal = new Animal();
        Dog myDog = new Dog();

        // Call their methods
        System.out.print("Calling Animal.makeSound(): ");
        genericAnimal.makeSound();

        System.out.print("Calling Dog.makeSound(): ");
        myDog.makeSound();
        
        // Polymorphism demo
        System.out.print("Calling Polymorphic Animal.makeSound() (assigned to a Dog instance): ");
        Animal polyDog = new Dog();
        polyDog.makeSound();
    }
}
