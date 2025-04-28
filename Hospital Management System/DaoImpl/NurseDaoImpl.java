package com.Hospital_Management_System.DaoImpl; // Package declaration for Nurse DAO implementation

import java.util.List; // Importing List for handling multiple Nurse entities
import org.hibernate.HibernateException; // Handles Hibernate-related exceptions
import org.hibernate.Session; // Hibernate session for database interaction
import org.hibernate.Transaction; // Manages transaction boundaries
import org.hibernate.query.Query; // Used for creating and executing HQL queries
import com.Hospital_Management_System.Dao.NurseDao; // Import NurseDao interface
import com.Hospital_Management_System.Entity.Nurse; // Import Nurse entity
import com.Hospital_Management_System.util.HibernateUtil; // Utility for Hibernate session management

public class NurseDaoImpl implements NurseDao { // Implementation of NurseDao interface

    @Override
    public Nurse createNurse(Nurse nurse) { // Create a new Nurse record
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
        	session.beginTransaction(); // Begin a new transaction
			session.save(nurse); // Save the Nurse object to DB
			session.getTransaction().commit(); // Commit the transaction
			return nurse; // Return the saved nurse
        } catch (HibernateException e) { // Handle Hibernate exceptions
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if operation fails
    }

    @Override
    public List<Nurse> getAllNurses() { // Retrieve all Nurse records
        try (Session session = HibernateUtil.getSession()) { // Open a session
            Query<Nurse> query = session.createQuery("FROM Nurse", Nurse.class); // HQL to fetch all Nurse records
            return query.list(); // Return list of Nurse objects
        } catch (HibernateException e) { // Handle exceptions
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null on failure
    }

    @Override
    public Nurse getNurseById(int nurseId) { // Fetch a Nurse by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            return session.get(Nurse.class, nurseId); // Retrieve Nurse by primary key
        } catch (HibernateException e) { // Exception handling
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if Nurse not found
    }

    @Override
    public Nurse updateNurse(int nurseId, Nurse updatedNurse) { // Update Nurse data
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Nurse nurse = session.get(Nurse.class, nurseId); // Fetch Nurse by ID
            if (nurse != null) { // If Nurse exists
                session.update(nurse); // Update the Nurse
                tx.commit(); // Commit transaction
                return nurse; // Return updated Nurse
            }
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if Nurse not found or update fails
    }

    @Override
    public String deleteNurse(int nurseId) { // Delete a Nurse by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Start transaction
            Nurse nurse = session.get(Nurse.class, nurseId); // Retrieve Nurse by ID
            if (nurse != null) { // If Nurse exists
                session.delete(nurse); // Delete the Nurse
                tx.commit(); // Commit transaction
                return "Nurse deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return "Nurse deletion failed"; // Return failure message
    }
}
