package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.ReceptionistDao; // Import ReceptionistDao interface
import com.Hospital_Management_System.DaoImpl.ReceptionistDaoImpl; // Import ReceptionistDaoImpl (implementation of ReceptionistDao)
import com.Hospital_Management_System.Entity.Receptionist; // Import Receptionist entity
import com.Hospital_Management_System.service.ReceptionistService; // Import ReceptionistService interface

public class ReceptionistServiceImpl implements ReceptionistService { // Class implementing ReceptionistService

    ReceptionistDao receptionistDao = new ReceptionistDaoImpl(); // Creates an object of ReceptionistDaoImpl to interact with the database
    
    @Override
    public Receptionist createReceptionist(Receptionist receptionist) { // Method to create a new receptionist and save it to the database
        return receptionistDao.createReceptionist(receptionist); // Passes the Receptionist to DAO to be saved in the database
    }
    
    @Override
    public Receptionist getReceptionistById(int receptionistId) { // Method to get a receptionist by their ID
        return receptionistDao.getReceptionistById(receptionistId); // Calls the DAO method to fetch the receptionist by their ID
    }
}
