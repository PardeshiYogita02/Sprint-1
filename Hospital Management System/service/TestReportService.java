package com.Hospital_Management_System.service; // Package declaration for service

import com.Hospital_Management_System.Entity.TestReport; // Importing TestReport entity

public interface TestReportService { // TestReportService interface

    TestReport createTestReport(TestReport testReport); // Method to create a new test report

    TestReport getTestReportById(int testReportId); // Method to get a test report by its ID
}
