package com.Hospital_Management_System.DaoImpl; // Package declaration for DAO implementation

import java.util.List; // Importing List interface

import org.hibernate.HibernateException; // For handling Hibernate-related exceptions
import org.hibernate.Session; // For establishing session with database
import org.hibernate.Transaction; // For transaction handling
import org.hibernate.query.Query; // For HQL queries

import com.Hospital_Management_System.Dao.RecordsDao; // Importing RecordsDao interface
import com.Hospital_Management_System.util.HibernateUtil; // Importing Hibernate utility class

public class RecordsDaoImpl implements RecordsDao { // Implementation of RecordsDao interface

    @Override
    public Record createRecord(Record record) { // Method to create and save a new record
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
        	session.beginTransaction(); // Begin transaction
			session.save(record); // Save record object to the database
			session.getTransaction().commit(); // Commit the transaction
			return record; // Return the saved record
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if operation fails
    }

    @Override
    public List<Record> getAllRecords() { // Method to fetch all records
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Query<Record> query = session.createQuery("FROM Record", Record.class); // HQL query to fetch all records
            return query.list(); // Return list of records
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if operation fails
    }

    @Override
    public Record getRecordById(int recordId) { // Method to fetch a record by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            return session.get(Record.class, recordId); // Return record by primary key
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if record not found
    }

    @Override
    public Record updateRecord(int recordId, Record updatedRecord) { // Method to update a record
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Record record = session.get(Record.class, recordId); // Get existing record by ID
            if (record != null) { // Check if record exists
                session.update(record); // Update the record
                tx.commit(); // Commit transaction
                return record; // Return updated record
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print exception stack trace
        }
        return null; // Return null if update fails
    }

    @Override
    public String deleteRecord(int recordId) { // Method to delete a record
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Record record = session.get(Record.class, recordId); // Get record by ID
            if (record != null) { // Check if record exists
                session.delete(record); // Delete the record
                tx.commit(); // Commit transaction
                return "Record deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Handle exception
            e.printStackTrace(); // Print stack trace
        }
        return "Record deletion failed"; // Return failure message
    }
}
