package com.Hospital_Management_System.Dao; // Declares the package for the interface

import java.util.List; // Imports List interface from Java's util package

import com.Hospital_Management_System.Entity.Bill; // Imports the Bill entity class

// Interface for Bill Data Access Object (DAO)
// Defines methods for CRUD operations on Bill entities
public interface BillDao {

    // Method to create and save a new Bill record in the database
    Bill createBill(Bill bill);

    // Method to retrieve a list of all Bill records from the database
    List<Bill> getAllBills();

    // Method to retrieve a specific Bill record by its ID from the database
    Bill getBillById(int billId);

    // Method to update a Bill record in the database using its ID
    Bill updateBill(int billId, Bill updatedBill);

    // Method to delete a Bill record from the database by its ID
    String deleteBill(int billId);
}
