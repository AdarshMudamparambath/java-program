class Calculator {
    // Method to add two integers
    int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two doubles
    double add(double a, double b) {
        return a + b;
    }
}

public class Overloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Call the add method with two integers
        System.out.println("Sum of 5 and 10: " + calc.add(5, 10));

        // Call the add method with three integers
        System.out.println("Sum of 5, 10, and 15: " + calc.add(5, 10, 15));

        // Call the add method with two doubles
        System.out.println("Sum of 5.5 and 10.5: " + calc.add(5.5, 10.5));
    }
}
