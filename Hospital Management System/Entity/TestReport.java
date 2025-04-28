package com.Hospital_Management_System.Entity; // Declares the package to which this class belongs

import javax.persistence.*; // Imports necessary JPA annotations

@Entity // Marks this class as a JPA entity
@Table(name = "Test_Report") // Maps this class to the "Test_Report" table in the database
public class TestReport {

    @Id // Specifies that the following field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates value for the primary key (R_ID)
    @Column(name = "R_ID") // Specifies the column name in the database
    private int id; // Unique identifier for each test report

    @ManyToOne // Indicates that many test reports can be linked to one patient (test-report-to-patient relationship)
    @JoinColumn(name = "P_ID", referencedColumnName = "P_ID") // Foreign key column in Test_Report linking to Patient's P_ID
    private Patient patient; // Reference to the patient who underwent the test

    @Column(name = "Test_Type") // Maps to the "Test_Type" column in the database
    private String testType; // Type of the test performed (e.g., blood test, X-ray)

    @Column(name = "Result") // Maps to the "Result" column in the database
    private String result; // Result of the test

    // Default constructor required by JPA
    public TestReport() {
        // TODO Auto-generated constructor stub
    }

    // Parameterized constructor to create a TestReport with specified details
    public TestReport(Patient patient, String testType, String result) {
        this.patient = patient; // Sets the patient for the test report
        this.testType = testType; // Sets the type of the test performed
        this.result = result; // Sets the result of the test
    }

    // Getter and setter for id field (Primary Key)
    public int getId() {
        return id; // Returns the test report ID
    }

    public void setId(int id) {
        this.id = id; // Sets the test report ID
    }

    // Getter and setter for patient field (Foreign Key)
    public Patient getPatient() {
        return patient; // Returns the patient associated with the test report
    }

    public void setPatient(Patient patient) {
        this.patient = patient; // Sets the patient for this test report
    }

    // Getter and setter for testType field
    public String getTestType() {
        return testType; // Returns the type of the test
    }

    public void setTestType(String testType) {
        this.testType = testType; // Sets the type of the test
    }

    // Getter and setter for result field
    public String getResult() {
        return result; // Returns the result of the test
    }

    public void setResult(String result) {
        this.result = result; // Sets the result of the test
    }

    // Overrides the toString() method to return a string representation of the TestReport object
    @Override
    public String toString() {
        return "TestReport [id=" + id + ", patient=" + patient + ", testType=" + testType + ", result=" + result + "]";
        // Returns test report ID, associated patient, test type, and test result in string format
    }

    // Placeholder method for getting test result (could be customized as needed)
    public String getTestResult() {
        // TODO Auto-generated method stub
        return null; // Currently not implemented
    }
}
