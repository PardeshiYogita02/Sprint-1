package com.Hospital_Management_System.service; // Package declaration for service

import com.Hospital_Management_System.Entity.Room; // Import Room entity

public interface RoomService { // RoomService interface

    Room createRoom(Room room); // Method to create a new Room

    Room getRoomById(int roomId); // Method to retrieve a Room by its ID
}
