package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.TestReportDao; // Import TestReportDao interface
import com.Hospital_Management_System.DaoImpl.TestReportDaoImpl; // Import TestReportDaoImpl (implementation of TestReportDao)
import com.Hospital_Management_System.Entity.TestReport; // Import TestReport entity
import com.Hospital_Management_System.service.TestReportService; // Import TestReportService interface

public class TestReportServiceImpl implements TestReportService { // Class implementing TestReportService

    TestReportDao testReportDao = new TestReportDaoImpl(); // Creates an object of TestReportDaoImpl (which interacts with the DB).
    
    @Override
    public TestReport createTestReport(TestReport testReport) { // Method to create a new TestReport
        return testReportDao.createTestReport(testReport); // Delegate to TestReportDaoImpl for creation
    }

    @Override
    public TestReport getTestReportById(int testReportId) { // Method to fetch a TestReport by its ID
        return testReportDao.getTestReportById(testReportId); // Delegate to TestReportDaoImpl for fetching by ID
    }
}
