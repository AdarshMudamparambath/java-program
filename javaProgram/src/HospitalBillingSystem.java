import java.io.*;
import java.util.Scanner;

class Patient {
    private String name;
    private int age;
    private String symptoms;
    private int daysInHospital;

    // Parameterized constructor
    public Patient(String name, int age, String symptoms, int daysInHospital) {
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.daysInHospital = daysInHospital;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for daysInHospital
    public int getDaysInHospital() {
        return daysInHospital;
    }
}

public class HospitalBillingSystem {
    public static void main(String[] args) {
        // Create a file to store patient details
        createPatientsFile();

        try (// Get user input for the patient name
        Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the patient name to retrieve details: ");
            String inputName = scanner.nextLine();

            // Retrieve patient details from the file
            Patient patient = retrievePatientDetails(inputName);

            // Calculate and display the bill for the patient
            if (patient != null) {
                calculateAndDisplayBill(patient);
            } else {
                System.out.println("Patient not found in the file.");
            }
        }
    }

    private static void createPatientsFile() {
        try (PrintWriter writer = new PrintWriter("patient_details.txt")) {
            // Writing details of 3 patients to the file
            writer.println("John Doe,25,Fever,3");
            writer.println("Alice Smith,35,Cough,2");
            writer.println("Bob Johnson,45,Headache,4");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Patient retrievePatientDetails(String inputName) {
        try (Scanner fileScanner = new Scanner(new File("patient_details.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] patientDetails = line.split(",");
                String name = patientDetails[0];
                int age = Integer.parseInt(patientDetails[1]);
                String symptoms = patientDetails[2];
                int daysInHospital = Integer.parseInt(patientDetails[3]);

                // Check if the current patient matches the input name
                if (name.equalsIgnoreCase(inputName)) {
                    return new Patient(name, age, symptoms, daysInHospital);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null; // Patient not found
    }

    private static void calculateAndDisplayBill(Patient patient) {
        // Assuming a daily rate of $100 for the hospital stay
        double dailyRate = 100.0;
        int daysInHospital = patient.getDaysInHospital();

        // Calculate the total bill
        double totalBill = dailyRate * daysInHospital;

        // Display the bill details
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Days in Hospital: " + daysInHospital);
        System.out.println("Daily Rate: $" + dailyRate);
        System.out.println("Total Bill: $" + totalBill);
    }
    
}