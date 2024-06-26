class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // Method overriding
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    // Method overriding
    @Override
    void makeSound() {
        System.out.println("Cats meows");
    }
}

public class Overriding {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound(); // Output: Animal makes a sound

        Dog dog = new Dog();
        dog.makeSound(); // Output: Dog barks

        // Polymorphism: Reference of superclass to subclass object
        Animal anotherDog = new Dog();
        anotherDog.makeSound(); // Output: Dog barks
        
        // Create a Cat object and call its makeSound() method
        Cat cat = new Cat();
        cat.makeSound(); // Output: Cat meows
    }
}
