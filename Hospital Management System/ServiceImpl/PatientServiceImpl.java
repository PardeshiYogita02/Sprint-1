package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import java.util.List; // Import List for handling collections

import com.Hospital_Management_System.Dao.PatientDao; // Import PatientDao interface
import com.Hospital_Management_System.DaoImpl.PatientDaoImpl; // Import PatientDaoImpl (implementation of PatientDao)
import com.Hospital_Management_System.Entity.Patient; // Import Patient entity
import com.Hospital_Management_System.service.PatientService; // Import PatientService interface

public class PatientServiceImpl implements PatientService { // Class implementing PatientService interface

    PatientDao patientDao = new PatientDaoImpl(); // Creates an object of PatientDaoImpl to interact with the database
    
    @Override
    public Patient createPatient(Patient patient) { // Method to create a new patient
        return patientDao.createPatient(patient); // Calls DAO method to create patient in the database
    }

    @Override
    public List<Patient> getAllPatients() { // Method to fetch all patients
        return patientDao.getAllPatients(); // Calls DAO method to fetch all patients
    }

    @Override
    public Record createRecord(Record record) { // Placeholder method for creating a record
        // This method should be implemented in a separate RecordService
        return null; // Returns null for now
    }

    @Override
    public Patient getPatientById(int patientId) { // Method to fetch a patient by their ID
        return patientDao.getPatientById(patientId); // Calls DAO method to get patient by ID
    }

    @Override
    public List<Record> getRecordsByPatientId(int patientId) { // Placeholder method to fetch records by patient ID
        // This method should be implemented in a separate RecordService
        return null; // Returns null for now
    }

    @Override
    public Patient updatePatient(int patientId, Patient updatedPatient) { // Method to update patient details
        return patientDao.updatePatient(patientId, updatedPatient); // Calls DAO method to update patient information
    }

    @Override
    public String deletePatient(int patientId) { // Method to delete a patient
        return patientDao.deletePatient(patientId); // Calls DAO method to delete patient
    }
}
