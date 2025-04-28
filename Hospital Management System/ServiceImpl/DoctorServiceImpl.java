package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import java.util.List; // Import List class for handling collections

import com.Hospital_Management_System.Dao.DoctorDao; // Import DoctorDao interface
import com.Hospital_Management_System.DaoImpl.DoctorDaoImpl; // Import DoctorDaoImpl (implementation of DoctorDao)
import com.Hospital_Management_System.Entity.Doctor; // Import Doctor entity
import com.Hospital_Management_System.service.DoctorService; // Import DoctorService interface

public class DoctorServiceImpl implements DoctorService { // Class implementing DoctorService interface

    DoctorDao doctorDao = new DoctorDaoImpl(); // Creates an object of DoctorDaoImpl to interact with the database
    
    @Override
    public Doctor createDoctor(Doctor doctor) { // Method to create a new doctor and save it to the database
        return doctorDao.createDoctor(doctor); // Passes the Doctor to DAO to be saved in the database
    }

    @Override
    public Doctor getDoctorById(int doctorId) { // Method to get a doctor by their ID
        return doctorDao.getDoctorById(doctorId); // Calls the DAO method to fetch the doctor by their ID
    }

    @Override
    public void saveDoctor(Doctor doctor) { // Placeholder method for saving a doctor
        // TODO Auto-generated method stub
    }

    @Override
    public List<Doctor> getAllDoctors() { // Placeholder method for getting all doctors
        // TODO Auto-generated method stub
        return null;
    }
}
