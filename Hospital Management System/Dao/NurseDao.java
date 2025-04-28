package com.Hospital_Management_System.Dao; // Declares the package for the interface

import java.util.List; // Imports List interface from Java's util package

import com.Hospital_Management_System.Entity.Nurse; // Imports the Nurse entity class

// Interface for Nurse Data Access Object (DAO)
// Defines methods for CRUD operations on Nurse entities
public interface NurseDao {

    // Method to create a new Nurse record in the database
    Nurse createNurse(Nurse nurse);

    // Method to retrieve all Nurse records from the database
    List<Nurse> getAllNurses();

    // Method to retrieve a Nurse record by its ID from the database
    Nurse getNurseById(int nurseId);

    // Method to update a Nurse record in the database using its ID
    Nurse updateNurse(int nurseId, Nurse updatedNurse);

    // Method to delete a Nurse record from the database by its ID
    String deleteNurse(int nurseId);
}
