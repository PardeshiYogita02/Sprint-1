package com.Hospital_Management_System.DaoImpl; // Package declaration for Patient DAO implementation

import java.util.List; // Importing List interface for handling collections

import org.hibernate.HibernateException; // Exception class for handling Hibernate-related errors
import org.hibernate.Session; // Hibernate session for database operations
import org.hibernate.Transaction; // Transaction class for handling commit and rollback
import org.hibernate.query.Query; // For writing and executing HQL queries

import com.Hospital_Management_System.Dao.PatientDao; // Importing PatientDao interface
import com.Hospital_Management_System.Entity.Patient; // Importing Patient entity
import com.Hospital_Management_System.util.HibernateUtil; // Utility class for obtaining Hibernate sessions

public class PatientDaoImpl implements PatientDao { // Implementing the PatientDao interface

    @Override
    public Patient createPatient(Patient patient) { // Method to create a new patient record
        try (Session session = HibernateUtil.getSession()) // Opens a Hibernate session
        {
        	session.beginTransaction(); // Begin the transaction
			session.save(patient); // Save the patient entity to the database
			session.getTransaction().commit(); // Commit the transaction
			return patient; // Return the saved patient object
        } catch (HibernateException e) { // Catch any Hibernate exceptions
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return null; // Return null if saving fails
    }

    @Override
    public List<Patient> getAllPatients() { // Method to fetch all patient records
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
            Query<Patient> query = session.createQuery("FROM Patient", Patient.class); // HQL query to fetch all patients
            return query.list(); // Return list of patients
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if fetching fails
    }

    @Override
    public Patient getPatientById(int patientId) { // Method to fetch a patient by ID
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
            return session.get(Patient.class, patientId); // Retrieve patient using primary key
        } catch (HibernateException e) { // Catch any exceptions
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if patient not found
    }

    @Override
    public Patient updatePatient(int patientId, Patient updatedPatient) { // Method to update patient details
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Patient patient = session.get(Patient.class, patientId); // Fetch existing patient
            if (patient != null) { // Check if patient exists
                patient.setName(updatedPatient.getName()); // Update name
                patient.setDob(updatedPatient.getDob()); // Update date of birth
                patient.setGender(updatedPatient.getGender()); // Update gender
                patient.setMobileNumber(updatedPatient.getMobileNumber()); // Update mobile number
                patient.setAge(updatedPatient.getAge()); // Update age
                session.update(patient); // Apply the updates
                tx.commit(); // Commit the transaction
                return patient; // Return the updated patient
            }
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if update fails
    }

    @Override
    public String deletePatient(int patientId) { // Method to delete a patient
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Patient patient = session.get(Patient.class, patientId); // Retrieve the patient
            if (patient != null) { // Check if patient exists
                session.delete(patient); // Delete the patient
                tx.commit(); // Commit the transaction
                return "Patient deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Catch Hibernate exceptions
        	System.out.println("Patient ID is not Available"); // Custom message if ID not found
        }
        return "Patient deletion failed"; // Return failure message
    }
}
