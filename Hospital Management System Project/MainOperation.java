package com.Hospital_Management_System;
/*
import static com.Hospital_Management_System.AllOperations.*;//login visible

import java.util.Scanner;

public class MainOperation {

    static Scanner sc = new Scanner(System.in);

    // Dummy credentials for admin login
    private static final String ADMIN_USERNAME = "Yogita";
    private static final String ADMIN_PASSWORD = "Yogita123";

    // Admin login function
    public static boolean adminLogin() {
        System.out.println("========== Admin Login ==========");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("✅ Login Successful!\n");
            return true;
        } else {
            System.out.println("❌ Invalid credentials! Access Denied.");
            return false;
        }
    }

    // Main dashboard after successful login
    public static void dashboard() {
        while (true) {
            System.out.println("\n==========🏥 Hospital Management System Dashboard 🏥==========");

            System.out.println("1. Patient Operations");
            System.out.println("2. Employee Operations");
            System.out.println("3. Doctor Operations");
            System.out.println("4. Nurse Operations");
            System.out.println("5. Receptionist Operations");
            System.out.println("6. Room Operations");
            System.out.println("7. Test Report Operations");
            System.out.println("8. Billing Operations");
            System.out.println("9. Records Operations");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number between 1-10.");
                sc.next(); // Consume invalid input
                continue;
            }

            int input = sc.nextInt();
            sc.nextLine(); // Consume leftover newline

            switch (input) {
                case 1:
                    patientOperations();
                    break;
                case 2:
                    employeeOperations();
                    break;
                case 3:
                    doctorOperations();
                    break;
                case 4:
                    nurseOperations();
                    break;
                case 5:
                    receptionistOperations();
                    break;
                case 6:
                    roomOperations();
                    break;
                case 7:
                    testReportOperations();
                    break;
                case 8:
                    billingOperations();
                    break;
                case 9:
                    recordOperations();
                    break;
                case 10:
                    System.out.println("🔓 Logging out...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-10.");
            }

            System.out.println("=================================================================");
        }
    }

    public static void main(String[] args) {
        System.out.println("🏥 Welcome to Hospital Management System 🏥");

        boolean isLoggedIn = false;

        // Allow retry for login
        for (int attempts = 0; attempts < 3; attempts++) {
            if (adminLogin()) {
                isLoggedIn = true;
                break;
            } else {
                System.out.println("🔁 Try again (" + (2 - attempts) + " attempts left)\n");
            }
        }

        if (isLoggedIn) {
            dashboard();
            System.out.println("👋 Thank you for using the Hospital Management System !");
        } else {
            System.out.println("🚫 Maximum login attempts exceeded. Exiting...");
        }
    }
}
*/
import static com.Hospital_Management_System.AllOperations.*;//login invisible

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MainOperation {

    // Dummy credentials for admin login
    private static final String ADMIN_USERNAME = "Yogita";
    private static final String ADMIN_PASSWORD = "Yogita123";

    // Admin login function
    public static boolean adminLogin() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(null, "✅ Login Successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid Credentials!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login Cancelled.");
            return false;
        }
    }

    // Main dashboard after successful login
    public static void dashboard() {
        while (true) {
            System.out.println("\n==========🏥 Hospital Management System Dashboard 🏥==========");

            System.out.println("1. Patient Operations");
            System.out.println("2. Employee Operations");
            System.out.println("3. Doctor Operations");
            System.out.println("4. Nurse Operations");
            System.out.println("5. Receptionist Operations");
            System.out.println("6. Room Operations");
            System.out.println("7. Test Report Operations");
            System.out.println("8. Billing Operations");
            System.out.println("9. Records Operations");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number between 1-10.");
                sc.next(); // Consume invalid input
                continue;
            }

            int input = sc.nextInt();
            sc.nextLine(); // Consume leftover newline

            switch (input) {
                case 1:
                    patientOperations();
                    break;
                case 2:
                    employeeOperations();
                    break;
                case 3:
                    doctorOperations();
                    break;
                case 4:
                    nurseOperations();
                    break;
                case 5:
                    receptionistOperations();
                    break;
                case 6:
                    roomOperations();
                    break;
                case 7:
                    testReportOperations();
                    break;
                case 8:
                    billingOperations();
                    break;
                case 9:
                    recordOperations();
                    break;
                case 10:
                    System.out.println("🔓 Logging out...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-10.");
            }

            System.out.println("=================================================================");
        }
    }

    public static void main(String[] args) {
        System.out.println("🏥 Welcome to Hospital Management System 🏥");

        boolean isLoggedIn = false;

        // Allow retry for login
        for (int attempts = 0; attempts < 3; attempts++) {
            if (adminLogin()) {
                isLoggedIn = true;
                break;
            } else {
                System.out.println("🔁 Try again (" + (2 - attempts) + " attempts left)\n");
            }
        }

        if (isLoggedIn) {
            dashboard();
            System.out.println("👋 Thank you for using the Hospital Management System!");
        } else {
            System.out.println("🚫 Maximum login attempts exceeded. Exiting...");
        }
    }
}

