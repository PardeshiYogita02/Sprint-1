package com.Hospital_Management_System.DaoImpl; // Package declaration for Doctor DAO implementation

import java.util.List; // Importing List for handling collections
import org.hibernate.HibernateException; // Exception class for handling Hibernate-related errors
import org.hibernate.Session; // Hibernate session for DB operations
import org.hibernate.Transaction; // Transaction for commit/rollback operations
import org.hibernate.query.Query; // For executing HQL queries
import com.Hospital_Management_System.Dao.DoctorDao; // Importing DoctorDao interface
import com.Hospital_Management_System.Entity.Doctor; // Importing Doctor entity
import com.Hospital_Management_System.util.HibernateUtil; // Utility class for getting Hibernate session

public class DoctorDaoImpl implements DoctorDao { // Implementation of DoctorDao interface

    @Override
    public Doctor createDoctor(Doctor doctor) { // Method to create and save a new doctor
        try (Session session = HibernateUtil.getSession()) { // Open a new Hibernate session
        	session.beginTransaction(); // Begin transaction
			session.save(doctor); // Save doctor entity to the database
			session.getTransaction().commit(); // Commit the transaction
			return doctor; // Return the saved doctor object
        } catch (HibernateException e) { // Catch any Hibernate-related exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if save fails
    }

    @Override
    public List<Doctor> getAllDoctors() { // Method to retrieve all doctors
        try (Session session = HibernateUtil.getSession()) { // Open a new session
            Query<Doctor> query = session.createQuery("FROM Doctor", Doctor.class); // HQL query to fetch all doctor records
            return query.list(); // Return the list of doctors
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if retrieval fails
    }

    @Override
    public Doctor getDoctorById(int doctorId) { // Method to get doctor by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            return session.get(Doctor.class, doctorId); // Fetch doctor by primary key
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print the exception
        }
        return null; // Return null if doctor not found
    }

    @Override
    public Doctor updateDoctor(int doctorId, Doctor updatedDoctor) { // Method to update existing doctor
        try (Session session = HibernateUtil.getSession()) { // Open a new session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Doctor doctor = session.get(Doctor.class, doctorId); // Fetch existing doctor by ID
            if (doctor != null) { // Check if doctor exists
                doctor.setDepartment(updatedDoctor.getDepartment()); // Update department
                doctor.setQualification(updatedDoctor.getQualification()); // Update qualification
                session.update(doctor); // Update doctor entity in session
                tx.commit(); // Commit the transaction
                return doctor; // Return the updated doctor
            }
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print exception
        }
        return null; // Return null if update fails
    }

    @Override
    public String deleteDoctor(int doctorId) { // Method to delete a doctor
        try (Session session = HibernateUtil.getSession()) { // Open a new session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Doctor doctor = session.get(Doctor.class, doctorId); // Fetch doctor by ID
            if (doctor != null) { // Check if doctor exists
                session.delete(doctor); // Delete doctor from session
                tx.commit(); // Commit the transaction
                return "Doctor deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print the exception
        }
        return "Doctor deletion failed"; // Return failure message
    }
}
