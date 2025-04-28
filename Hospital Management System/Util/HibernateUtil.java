package com.Hospital_Management_System.util; // Package declaration for utility classes

import org.hibernate.Session; // Import Hibernate Session class
import org.hibernate.SessionFactory; // Import Hibernate SessionFactory class
import org.hibernate.cfg.Configuration; // Import Hibernate Configuration class

import com.Hospital_Management_System.Entity.Bill; // Import Bill entity
import com.Hospital_Management_System.Entity.Doctor; // Import Doctor entity
import com.Hospital_Management_System.Entity.Employee; // Import Employee entity
import com.Hospital_Management_System.Entity.Nurse; // Import Nurse entity
import com.Hospital_Management_System.Entity.Patient; // Import Patient entity
import com.Hospital_Management_System.Entity.Receptionist; // Import Receptionist entity
import com.Hospital_Management_System.Entity.Record; // Import Record entity
import com.Hospital_Management_System.Entity.Room; // Import Room entity
import com.Hospital_Management_System.Entity.TestReport; // Import TestReport entity

public class HibernateUtil { // Utility class for managing Hibernate sessions
    
    private final static SessionFactory sessionFactory = buildSessionFactory(); // Static SessionFactory object
    
    private static SessionFactory buildSessionFactory() { // Method to build the SessionFactory
        try {
            return new Configuration().configure("hibernate.cfg.xml") // Configures Hibernate using the configuration file
                    .addAnnotatedClass(Patient.class) // Adds Patient entity to the configuration
                    .addAnnotatedClass(Employee.class) // Adds Employee entity to the configuration
                    .addAnnotatedClass(Doctor.class) // Adds Doctor entity to the configuration
                    .addAnnotatedClass(Nurse.class) // Adds Nurse entity to the configuration
                    .addAnnotatedClass(Room.class) // Adds Room entity to the configuration
                    .addAnnotatedClass(Receptionist.class) // Adds Receptionist entity to the configuration
                    .addAnnotatedClass(TestReport.class) // Adds TestReport entity to the configuration
                    .addAnnotatedClass(Bill.class) // Adds Bill entity to the configuration
                    .addAnnotatedClass(Record.class) // Adds Record entity to the configuration
                    .buildSessionFactory(); // Builds and returns the SessionFactory
        } catch (Throwable e) { // Catching any errors during the configuration process
            throw new ExceptionInInitializerError(e); // Throws an error if initialization fails
        }
    }
    
    public static SessionFactory getSessionFactory() { // Method to get the SessionFactory
        return sessionFactory; // Returns the SessionFactory instance
    }
    
    public static Session getSession() { // Method to open and return a new session
        return getSessionFactory().openSession(); // Opens and returns a new session from the SessionFactory
    }
}
