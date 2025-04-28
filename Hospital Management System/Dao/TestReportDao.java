package com.Hospital_Management_System.Dao; // Declares the package for the interface

import java.util.List; // Imports List interface from Java's util package

import com.Hospital_Management_System.Entity.TestReport; // Imports the TestReport entity class

// Interface for TestReport Data Access Object (DAO)
// Defines methods for CRUD operations on TestReport entities
public interface TestReportDao {

    // Method to create a new TestReport record in the database
    TestReport createTestReport(TestReport testReport);

    // Method to retrieve all TestReport records from the database
    List<TestReport> getAllTestReports();

    // Method to retrieve a TestReport record by its ID from the database
    TestReport getTestReportById(int testReportId);

    // Method to update a TestReport record in the database using its ID
    TestReport updateTestReport(int testReportId, TestReport updatedTestReport);

    // Method to delete a TestReport record from the database by its ID
    String deleteTestReport(int testReportId);
}
