package com.Hospital_Management_System.service; // Package declaration for service

import java.util.List; // Import List for handling lists of bills
import java.util.Scanner; // Import Scanner for user input

import com.Hospital_Management_System.Entity.Bill; // Import Bill entity

public interface BillService { // BillService interface

    static Bill createBill(Scanner scanner) { // Static method to create a bill using scanner input
        // TODO Auto-generated method stub
        return null; // Method implementation placeholder
    }

    Bill getBillById(int billId); // Method to retrieve a bill by its ID

    void addBill(int patientId, double amount); // Method to add a bill using patient ID and amount

    List<Bill> getAllBills(); // Method to get a list of all bills

    static void viewBillDetails(Scanner scanner) { // Static method to view bill details using scanner input
        // TODO Auto-generated method stub
    }

    Bill createBill(Bill bill); // Method to create a bill by passing a Bill object
}
