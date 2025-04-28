package com.Hospital_Management_System.DaoImpl; // Package for Employee DAO implementation

import java.util.List; // Importing List for handling multiple Employee entities
import org.hibernate.HibernateException; // Exception class for handling Hibernate errors
import org.hibernate.Session; // Hibernate session to interact with the DB
import org.hibernate.Transaction; // Manages DB transaction boundaries
import org.hibernate.query.Query; // Used to execute HQL queries
import com.Hospital_Management_System.Dao.EmployeeDao; // Import Employee DAO interface
import com.Hospital_Management_System.Entity.Employee; // Import Employee entity
import com.Hospital_Management_System.util.HibernateUtil; // Utility for Hibernate session management

public class EmployeeDaoImpl implements EmployeeDao { // Implementation of EmployeeDao interface

    @Override
    public Employee createEmployee(Employee employee) { // Create a new Employee
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
        	session.beginTransaction(); // Begin transaction
			session.save(employee); // Save the employee object
			session.getTransaction().commit(); // Commit transaction
			return employee; // Return saved employee
        } catch (HibernateException e) { // Catch Hibernate exceptions
            e.printStackTrace(); // Print exception stack trace
        }
        return null; // Return null on failure
    }

    @Override
    public List<Employee> getAllEmployees() { // Retrieve all Employees
        try (Session session = HibernateUtil.getSession()) { // Open session
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class); // HQL query to fetch all employees
            return query.list(); // Return list of employees
        } catch (HibernateException e) { // Catch exceptions
            e.printStackTrace(); // Print exception stack trace
        }
        return null; // Return null on failure
    }

    @Override
    public Employee getEmployeeById(int employeeId) { // Retrieve employee by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            return session.get(Employee.class, employeeId); // Fetch employee by primary key
        } catch (HibernateException e) { // Catch exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if not found
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee updatedEmployee) { // Update employee by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Employee employee = session.get(Employee.class, employeeId); // Fetch employee by ID
            if (employee != null) { // Check if employee exists
                employee.setName(updatedEmployee.getName()); // Update name
                employee.setSalary(updatedEmployee.getSalary()); // Update salary
                employee.setSex(updatedEmployee.getSex()); // Update sex
                employee.setMobileNumber(updatedEmployee.getMobileNumber()); // Update mobile number
                employee.setAddress(updatedEmployee.getAddress()); // Update address
                employee.setState(updatedEmployee.getState()); // Update state
                employee.setCity(updatedEmployee.getCity()); // Update city
                employee.setPinNo(updatedEmployee.getPinNo()); // Update pin number
                session.update(employee); // Save updated employee
                tx.commit(); // Commit transaction
                return employee; // Return updated employee
            }
        } catch (HibernateException e) { // Catch exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if update fails
    }

    @Override
    public String deleteEmployee(int employeeId) { // Delete employee by ID
        try (Session session = HibernateUtil.getSession()) { // Open session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Employee employee = session.get(Employee.class, employeeId); // Fetch employee by ID
            if (employee != null) { // Check if employee exists
                session.delete(employee); // Delete employee
                tx.commit(); // Commit transaction
                return "Employee deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Catch exception
            e.printStackTrace(); // Print stack trace
        }
        return "Employee deletion failed"; // Return failure message
    }
}
