package com.Hospital_Management_System.DaoImpl; // Package declaration for DAO implementation

import java.util.List; // Importing List for handling multiple results
import org.hibernate.HibernateException; // Exception for handling Hibernate errors
import org.hibernate.Session; // Hibernate session object
import org.hibernate.Transaction; // Handles transactions
import org.hibernate.query.Query; // Used to run HQL queries
import com.Hospital_Management_System.Dao.BillDao; // Importing Bill DAO interface
import com.Hospital_Management_System.Entity.Bill; // Importing Bill entity
import com.Hospital_Management_System.util.HibernateUtil; // Hibernate utility for sessions

public class BillDaoImpl implements BillDao { // Implementation of BillDao interface

    @Override
    public Bill createBill(Bill bill) { // Method to create and save a new Bill
        try (Session session = HibernateUtil.getSession()) { // Open a session using HibernateUtil
        	session.beginTransaction(); // Begin a new transaction
			session.save(bill); // Save the Bill object
			session.getTransaction().commit(); // Commit the transaction
			return bill; // Return the saved Bill
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if operation fails
    }

    @Override
    public List<Bill> getAllBills() { // Method to retrieve all Bill records
        try (Session session = HibernateUtil.getSession()) { // Open a Hibernate session
            Query<Bill> query = session.createQuery("FROM Bill", Bill.class); // HQL query to fetch all bills
            return query.list(); // Return the list of bills
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if operation fails
    }

    @Override
    public Bill getBillById(int billId) { // Method to fetch a Bill by its ID
        try (Session session = HibernateUtil.getSession()) { // Open a session
            return session.get(Bill.class, billId); // Retrieve Bill using primary key
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print the exception stack trace
        }
        return null; // Return null if operation fails
    }

    @Override
    public Bill updateBill(int billId, Bill updatedBill) { // Method to update a Bill by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Bill bill = session.get(Bill.class, billId); // Fetch Bill by ID
            if (bill != null) { // Check if Bill exists
                bill.setAmount(updatedBill.getAmount()); // Set new amount
                session.update(bill); // Update Bill in database
                tx.commit(); // Commit transaction
                return bill; // Return updated Bill
            }
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print exception stack trace
        }
        return null; // Return null if update fails
    }

    @Override
    public String deleteBill(int billId) { // Method to delete a Bill by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Bill bill = session.get(Bill.class, billId); // Fetch Bill by ID
            if (bill != null) { // Check if Bill exists
                session.delete(bill); // Delete Bill
                tx.commit(); // Commit transaction
                return "Bill deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print exception stack trace
        }
        return "Bill deletion failed"; // Return failure message
    }
}
