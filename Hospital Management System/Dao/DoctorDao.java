package com.Hospital_Management_System.Dao; // Declares the package for the interface

import java.util.List; // Imports List interface from Java's util package

import com.Hospital_Management_System.Entity.Doctor; // Imports the Doctor entity class

// Interface for Doctor Data Access Object (DAO)
// Defines methods for CRUD operations on Doctor entities
public interface DoctorDao {

    // Method to create and save a new Doctor record in the database
    Doctor createDoctor(Doctor doctor);

    // Method to retrieve a list of all Doctor records from the database
    List<Doctor> getAllDoctors();

    // Method to retrieve a specific Doctor record by its ID from the database
    Doctor getDoctorById(int doctorId);

    // Method to update a Doctor record in the database using its ID
    Doctor updateDoctor(int doctorId, Doctor updatedDoctor);

    // Method to delete a Doctor record from the database by its ID
    String deleteDoctor(int doctorId);
}
