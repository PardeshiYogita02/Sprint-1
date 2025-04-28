package com.Hospital_Management_System.DaoImpl; // Package declaration for DAO implementation

import java.util.List; // Importing List interface

import org.hibernate.HibernateException; // For handling Hibernate-related exceptions
import org.hibernate.Session; // For establishing session with database
import org.hibernate.Transaction; // For transaction handling
import org.hibernate.query.Query; // For HQL queries

import com.Hospital_Management_System.Dao.ReceptionistDao; // Importing ReceptionistDao interface
import com.Hospital_Management_System.Entity.Receptionist; // Importing Receptionist entity
import com.Hospital_Management_System.util.HibernateUtil; // Importing Hibernate utility class

public class ReceptionistDaoImpl implements ReceptionistDao { // Implementation of ReceptionistDao interface

    @Override
    public Receptionist createReceptionist(Receptionist receptionist) { // Method to create and save a new receptionist
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
        	session.beginTransaction(); // Begin transaction
			session.save(receptionist); // Save receptionist object to the database
			session.getTransaction().commit(); // Commit the transaction
			return receptionist; // Return the saved receptionist
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if operation fails
    }

    @Override
    public List<Receptionist> getAllReceptionists() { // Method to fetch all receptionists
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
        	// Runs an HQL query (FROM Receptionist) to fetch all records from the Receptionist table.
            Query<Receptionist> query = session.createQuery("FROM Receptionist", Receptionist.class); // HQL query
            return query.list(); // Return list of receptionists
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if operation fails
    }

    @Override
    public Receptionist getReceptionistById(int receptionistId) { // Method to fetch a receptionist by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            return session.get(Receptionist.class, receptionistId); // Return receptionist by primary key
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if receptionist not found
    }

    @Override
    public Receptionist updateReceptionist(int receptionistId, Receptionist updatedReceptionist) { // Method to update a receptionist
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Receptionist receptionist = session.get(Receptionist.class, receptionistId); // Get existing receptionist by ID
            if (receptionist != null) { // Check if receptionist exists
                session.update(receptionist); // Update the receptionist
                tx.commit(); // Commit transaction
                return receptionist; // Return updated receptionist
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print exception stack trace
        }
        return null; // Return null if update fails
    }

    @Override
    public String deleteReceptionist(int receptionistId) { // Method to delete a receptionist
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Receptionist receptionist = session.get(Receptionist.class, receptionistId); // Get receptionist by ID
            if (receptionist != null) { // Check if receptionist exists
                session.delete(receptionist); // Delete the receptionist
                tx.commit(); // Commit transaction
                return "Receptionist deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return "Receptionist deletion failed"; // Return failure message
    }
}
