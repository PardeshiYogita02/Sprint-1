package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.NurseDao; // Import NurseDao interface
import com.Hospital_Management_System.DaoImpl.NurseDaoImpl; // Import NurseDaoImpl (implementation of NurseDao)
import com.Hospital_Management_System.Entity.Nurse; // Import Nurse entity
import com.Hospital_Management_System.service.NurseService; // Import NurseService interface

public class NurseServiceImpl implements NurseService { // Class implementing NurseService interface

    NurseDao nurseDao = new NurseDaoImpl(); // Creates an object of NurseDaoImpl to interact with the database
    
    @Override
    public Nurse createNurse(Nurse nurse) { // Method to create a new nurse
        return nurseDao.createNurse(nurse); // Calls DAO method to create nurse in the database
    }

    @Override
    public Nurse getNurseById(int nurseId) { // Method to fetch a nurse by their ID
        return nurseDao.getNurseById(nurseId); // Calls DAO method to get nurse by ID
    }
}
