package com.Hospital_Management_System.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/*
 * Represents a Doctor entity in the Hospital Management System.
 * Each doctor is associated with exactly one employee via E_ID.
 */
@Entity
@Table(name = "Doctor")
public class Doctor {
	 /*
     * Doctor's ID which is also the Employee ID (shared primary key).
     */
    @Id
    @Column(name = "E_ID")  // Employee ID should not be auto-generated here
    private int id;
    /*
     * One-to-one mapping with Employee.
     * This ensures each doctor is also an employee.
     * The @MapsId annotation links this entity's primary key with the employee's ID.
     */
    @OneToOne
    @MapsId  // Ensures Doctor ID is same as Employee ID
    @JoinColumn(name = "E_ID", referencedColumnName = "E_ID", nullable = false)
    private Employee employee;
    /*
     * Department in which the doctor works (e.g., Cardiology, Neurology).
     * Can be null; defaults to "General Medicine" in constructor logic if not provided.
     */
    @Column(nullable = true)
    private String department;
    /*
     * Doctor's educational qualification (e.g., MBBS, MD).
     * This field is mandatory.
     */
    @Column(name = "Qualification", nullable = false)
    private String qualification;
    /*
     * Default no-argument constructor required by JPA.
     */
    public Doctor() {}

    public Doctor(Employee employee, String department, String qualification) {
        if (employee == null) {
            throw new IllegalArgumentException("❌ Error: Employee cannot be null for a Doctor.");
        }
        this.employee = employee;
        this.id = employee.getId(); // Ensuring E_ID is same as Employee ID
        this.department = (department != null && !department.isEmpty()) ? department : "General Medicine";
        this.qualification = (qualification != null && !qualification.isEmpty()) ? qualification : "MBBS";
    }
    // Getter for Doctor ID
    public int getId() {
        return id;
    }
 // Getter for linked Employee
    public Employee getEmployee() {
        return employee;
    }
 // Setter for Employee
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    // Getter for Department
    public String getDepartment() {
        return department;
    }
 // Setter for Department
    public void setDepartment(String department) {
        this.department = department;
    }
 // Getter for Qualification
    public String getQualification() {
        return qualification;
    }
    // Setter for Qualification

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    /*
     * String representation for debugging/logging.
     * Includes employee name if available.
     */
    @Override
    public String toString() {
        return "Doctor [ID=" + id + ", Name=" + (employee != null ? employee.getName() : "Unknown") +
                ", Department=" + department + ", Qualification=" + qualification + "]";
    }
}
