package com.Hospital_Management_System.service; // Package declaration for service

import com.Hospital_Management_System.Entity.Receptionist; // Importing Receptionist entity

public interface ReceptionistService { // Interface for ReceptionistService

    Receptionist createReceptionist(Receptionist receptionist); // Method to add a new receptionist

    Receptionist getReceptionistById(int receptionistId); // Method to fetch a receptionist by their ID
}
