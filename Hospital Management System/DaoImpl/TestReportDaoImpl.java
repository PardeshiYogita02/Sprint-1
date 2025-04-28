package com.Hospital_Management_System.DaoImpl; // Package declaration for DAO implementation

import java.util.List; // Importing List interface

import org.hibernate.HibernateException; // For handling Hibernate-related exceptions
import org.hibernate.Session; // For establishing session with database
import org.hibernate.Transaction; // For transaction handling
import org.hibernate.query.Query; // For HQL queries

import com.Hospital_Management_System.Dao.TestReportDao; // Importing TestReportDao interface
import com.Hospital_Management_System.Entity.TestReport; // Importing TestReport entity
import com.Hospital_Management_System.util.HibernateUtil; // Importing Hibernate utility class

public class TestReportDaoImpl implements TestReportDao { // Implementation of TestReportDao interface

    // Creates a new Test Report in the database.
    @Override
    public TestReport createTestReport(TestReport testReport) { // Method to create and save a new Test Report
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            session.beginTransaction(); // Begin transaction
            session.save(testReport); // Save the Test Report object to the database
            session.getTransaction().commit(); // Commit the transaction
            return testReport; // Return the saved Test Report
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if operation fails
    }

    // Fetches all the Test Reports from the database.
    @Override
    public List<TestReport> getAllTestReports() { // Method to fetch all Test Reports
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Query<TestReport> query = session.createQuery("FROM TestReport", TestReport.class); // HQL query to fetch all Test Reports
            return query.list(); // Return list of Test Reports
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if operation fails
    }

    // Fetches a single Test Report from the database based on its ID.
    @Override
    public TestReport getTestReportById(int testReportId) { // Method to fetch Test Report by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            return session.get(TestReport.class, testReportId); // Return Test Report by primary key
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if Test Report not found
    }

    // Updates the details of an existing Test Report in the database.
    @Override
    public TestReport updateTestReport(int testReportId, TestReport updatedTestReport) { // Method to update Test Report details
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            TestReport testReport = session.get(TestReport.class, testReportId); // Get existing Test Report by ID
            if (testReport != null) { // Check if Test Report exists
                testReport.setTestType(updatedTestReport.getTestType()); // Update Test Type
                testReport.setResult(updatedTestReport.getResult()); // Update Test Result
                session.update(testReport); // Update the Test Report in the database
                tx.commit(); // Commit the transaction
                return testReport; // Return updated Test Report
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if update fails
    }

    // Deletes a Test Report from the database based on its ID.
    @Override
    public String deleteTestReport(int testReportId) { // Method to delete a Test Report by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            TestReport testReport = session.get(TestReport.class, testReportId); // Get Test Report by ID
            if (testReport != null) { // Check if Test Report exists
                session.delete(testReport); // Delete the Test Report
                tx.commit(); // Commit the transaction
                return "Test Report deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return "Test Report deletion failed"; // Return failure message if deletion fails
    }
}
