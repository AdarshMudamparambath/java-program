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

    // Method to subtract two integers
    int subtract(int a, int b) {
        return a - b;
    }

    // Method to subtract two doubles
    double subtract(double a, double b) {
        return a - b;
    }

    // Method to multiply two integers
    int multiply(int a, int b) {
        return a * b;
    }

    // Method to multiply two doubles
    double multiply(double a, double b) {
        return a * b;
    }

    // Method to divide two integers
    int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }

    // Method to divide two doubles
    double divide(double a, double b) {
        if (b != 0.0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}

public class Overloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Test addition methods
        System.out.println("Sum of 5 and 10: " + calc.add(5, 10));
        System.out.println("Sum of 5, 10, and 15: " + calc.add(5, 10, 15));
        System.out.println("Sum of 5.5 and 10.5: " + calc.add(5.5, 10.5));

        // Test subtraction methods
        System.out.println("Difference of 10 and 5: " + calc.subtract(10, 5));
        System.out.println("Difference of 10.5 and 5.5: " + calc.subtract(10.5, 5.5));

        // Test multiplication methods
        System.out.println("Product of 5 and 10: " + calc.multiply(5, 10));
        System.out.println("Product of 5.5 and 10.5: " + calc.multiply(5.5, 10.5));

        // Test division methods
        System.out.println("Division of 10 by 2: " + calc.divide(10, 2));
        System.out.println("Division of 10.5 by 2.5: " + calc.divide(10.5, 2.5));
    }
}
