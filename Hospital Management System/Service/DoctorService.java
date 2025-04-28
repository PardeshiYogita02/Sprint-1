package com.Hospital_Management_System.service; // Package declaration for service

import java.util.List; // Import List to handle a collection of doctors

import com.Hospital_Management_System.Entity.Doctor; // Import Doctor entity

public interface DoctorService { // DoctorService interface

    Doctor createDoctor(Doctor doctor); // Method to create a new doctor

    Doctor getDoctorById(int doctorId); // Method to retrieve a doctor by ID

    void saveDoctor(Doctor doctor); // Method to save doctor details

    List<Doctor> getAllDoctors(); // Method to retrieve all doctors
}
