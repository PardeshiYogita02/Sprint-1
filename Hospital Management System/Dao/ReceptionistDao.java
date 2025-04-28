package com.Hospital_Management_System.Dao; // Defines the package location for this interface

import java.util.List; // Imports the List interface for handling collections of receptionists

import com.Hospital_Management_System.Entity.Receptionist; // Imports the Receptionist entity class

// Interface for defining CRUD operations related to Receptionist entities
public interface ReceptionistDao {

    // Creates a new Receptionist entry in the database
    Receptionist createReceptionist(Receptionist receptionist);

    // Retrieves all Receptionist entries from the database
    List<Receptionist> getAllReceptionists();

    // Retrieves a specific Receptionist using their unique ID
    Receptionist getReceptionistById(int receptionistId);

    // Updates the data of a specific Receptionist by ID
    Receptionist updateReceptionist(int receptionistId, Receptionist updatedReceptionist);

    // Deletes a Receptionist from the database based on ID and returns a status message
    String deleteReceptionist(int receptionistId);
}
