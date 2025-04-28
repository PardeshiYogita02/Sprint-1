package com.Hospital_Management_System.Dao; // Specifies the package this interface belongs to

import java.util.List; // Imports the List interface for returning multiple Patient objects

import com.Hospital_Management_System.Entity.Patient; // Imports the Patient entity class

// Interface that defines CRUD operations for Patient entities
public interface PatientDao {

    // Creates a new Patient record in the database
    Patient createPatient(Patient patient);

    // Retrieves all Patient records from the database
    List<Patient> getAllPatients();

    // Retrieves a specific Patient using their unique ID
    Patient getPatientById(int patientId);

    // Updates the details of an existing Patient based on their ID
    Patient updatePatient(int patientId, Patient updatedPatient);

    // Deletes a Patient by their ID and returns a confirmation message
    String deletePatient(int patientId);
}
