package com.Hospital_Management_System;
import static com.Hospital_Management_System.AllOperations.*; // Importing all operations (for use in other methods)

import javax.swing.*; // Importing Swing library for GUI elements
import java.awt.*; // Importing AWT library for graphical components
import java.util.Scanner; // Importing Scanner for user input

public class MainOperation {

    // Dummy credentials for admin login
    private static final String ADMIN_USERNAME = "Yogita"; // Admin username
    private static final String ADMIN_PASSWORD = "Yogita123"; // Admin password

    // Admin login function
    public static boolean adminLogin() {
        JTextField usernameField = new JTextField(); // Creating text field for username input
        JPasswordField passwordField = new JPasswordField(); // Creating password field for password input

        Object[] message = { // Message to show in the login dialog
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION); // Show login dialog

        if (option == JOptionPane.OK_OPTION) { // If OK is clicked
            String username = usernameField.getText(); // Get username from the text field
            String password = new String(passwordField.getPassword()); // Get password from the password field

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) { // Check if credentials match
                JOptionPane.showMessageDialog(null, "✅ Login Successful!"); // Show success message
                return true; // Return true for successful login
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid Credentials!"); // Show error message for invalid credentials
                return false; // Return false for failed login
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login Cancelled."); // If user cancels login
            return false; // Return false for cancelled login
        }
    }

    // Main dashboard after successful login
    public static void dashboard() {
        while (true) { // Infinite loop for the dashboard menu
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
            System.out.print("Enter your choice: "); // Display menu options

            Scanner sc = new Scanner(System.in); // Create a scanner for user input
            if (!sc.hasNextInt()) { // Check if the input is a number
                System.out.println("❌ Invalid input! Please enter a number between 1-10."); // Show error if input is not a number
                sc.next(); // Consume invalid input
                continue; // Restart the loop to ask for valid input
            }

            int input = sc.nextInt(); // Get the user's choice
            sc.nextLine(); // Consume leftover newline

            switch (input) { // Switch case based on user's choice
                case 1:
                    patientOperations(); // Call patient operations
                    break;
                case 2:
                    employeeOperations(); // Call employee operations
                    break;
                case 3:
                    doctorOperations(); // Call doctor operations
                    break;
                case 4:
                    nurseOperations(); // Call nurse operations
                    break;
                case 5:
                    receptionistOperations(); // Call receptionist operations
                    break;
                case 6:
                    roomOperations(); // Call room operations
                    break;
                case 7:
                    testReportOperations(); // Call test report operations
                    break;
                case 8:
                    billingOperations(); // Call billing operations
                    break;
                case 9:
                    recordOperations(); // Call record operations
                    break;
                case 10:
                    System.out.println("🔓 Logging out..."); // Logout message
                    return; // Exit the loop and log out
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-10."); // Error message for invalid choice
            }

            System.out.println("=================================================================");
        }
    }

    public static void main(String[] args) {
        System.out.println("🏥 Welcome to Hospital Management System 🏥"); // Display welcome message

        boolean isLoggedIn = false; // Flag to track login status

        // Allow retry for login
        for (int attempts = 0; attempts < 3; attempts++) { // Allow up to 3 attempts
            if (adminLogin()) { // If login is successful
                isLoggedIn = true; // Set login status to true
                break; // Exit the loop if login is successful
            } else {
                System.out.println("🔁 Try again (" + (2 - attempts) + " attempts left)\n"); // Display retry message
            }
        }

        if (isLoggedIn) { // If login is successful
            dashboard(); // Show the dashboard
            System.out.println("👋 Thank you for using the Hospital Management System!"); // Display thank you message
        } else {
            System.out.println("🚫 Maximum login attempts exceeded. Exiting..."); // Display exit message if login fails
        }
    }
}
