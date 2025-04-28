package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.EmployeeDao; // Import EmployeeDao interface
import com.Hospital_Management_System.DaoImpl.EmployeeDaoImpl; // Import EmployeeDaoImpl (implementation of EmployeeDao)
import com.Hospital_Management_System.Entity.Employee; // Import Employee entity
import com.Hospital_Management_System.service.EmployeeService; // Import EmployeeService interface

public class EmployeeServiceImpl implements EmployeeService { // Class implementing EmployeeService interface

    EmployeeDao employeeDao = new EmployeeDaoImpl(); // Creates an object of EmployeeDaoImpl to interact with the database
    
    @Override
    public Employee createEmployee(Employee employee) { // Method to create a new employee
        return employeeDao.createEmployee(employee); // Calls DAO method to create employee in the database
    }

    @Override
    public Employee getEmployeeById(int employeeId) { // Method to fetch an employee by their ID
        return employeeDao.getEmployeeById(employeeId); // Calls DAO method to get employee by ID
    }
}
