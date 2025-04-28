package com.Hospital_Management_System.Dao; // Defines the package for this interface

import java.util.List; // Imports the List interface to work with lists of Employee objects

import com.Hospital_Management_System.Entity.Employee; // Imports the Employee entity class

// Interface for defining data access operations related to Employee entities
public interface EmployeeDao {

    // Adds a new Employee record to the database
    Employee createEmployee(Employee employee);

    // Retrieves a list of all Employee records from the database
    List<Employee> getAllEmployees();

    // Fetches a specific Employee based on their unique ID
    Employee getEmployeeById(int employeeId);

    // Updates the details of an existing Employee using their ID
    Employee updateEmployee(int employeeId, Employee updatedEmployee);

    // Removes an Employee record from the database using their ID
    String deleteEmployee(int employeeId);
}
