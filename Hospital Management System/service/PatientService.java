package com.Hospital_Management_System.service; // Package declaration for service

import java.util.List; // Import List class
import java.util.Scanner; // Import Scanner class

import com.Hospital_Management_System.Entity.Patient; // Import Patient entity

public interface PatientService { // Interface for PatientService

    static Patient createPatient(Scanner scanner) { // Static method to create a patient (stub method)
        return null; // Return null for now
    }

    List<Patient> getAllPatients(); // Method to get a list of all patients

    Record createRecord(Record record); // Method to create a new record

    Patient getPatientById(int patientId); // Method to fetch a patient by their ID

    List<Record> getRecordsByPatientId(int patientId); // Method to fetch records by patient ID

    Patient updatePatient(int patientId, Patient updatedPatient); // Method to update patient details

    String deletePatient(int patientId); // Method to delete a patient by their ID

    static void viewPatientDetails(Scanner scanner) { // Static method to view patient details (stub method)
    }

    Patient createPatient(Patient patient); // Method to create a new patient
}
