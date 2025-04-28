package com.Hospital_Management_System.service; // Package declaration for service

import com.Hospital_Management_System.Entity.Employee; // Import Employee entity

public interface EmployeeService { // Interface for EmployeeService
    Employee createEmployee(Employee employee); // Method to create a new employee

    Employee getEmployeeById(int employeeId); // Method to fetch an employee by their ID
}
