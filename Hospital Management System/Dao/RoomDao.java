package com.Hospital_Management_System.Dao; // Declares the package for the interface

import java.util.List; // Imports List interface from Java's util package

import com.Hospital_Management_System.Entity.Room; // Imports the Room entity class

// Interface for Room Data Access Object (DAO)
// Defines methods for CRUD operations on Room entities
public interface RoomDao {

    // Method to create and save a new Room record in the database
    Room createRoom(Room room);

    // Method to retrieve a list of all Room records from the database
    List<Room> getAllRooms();

    // Method to retrieve a specific Room record by its ID from the database
    Room getRoomById(int roomId);

    // Method to update a Room record in the database using its ID
    Room updateRoom(int roomId, Room updatedRoom);

    // Method to delete a Room record from the database by its ID
    String deleteRoom(int roomId);
}
