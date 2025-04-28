package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.RoomDao; // Import RoomDao interface
import com.Hospital_Management_System.DaoImpl.RoomDaoImpl; // Import RoomDaoImpl (implementation of RoomDao)
import com.Hospital_Management_System.Entity.Room; // Import Room entity
import com.Hospital_Management_System.service.RoomService; // Import RoomService interface

public class RoomServiceImpl implements RoomService { // Class implementing RoomService

    RoomDao roomDao = new RoomDaoImpl(); // Creates an object of RoomDaoImpl (which interacts with the DB).
    
    @Override
    public Room createRoom(Room room) { // Method to create a new room
        return roomDao.createRoom(room); // Delegate to RoomDaoImpl for room creation
    }

    @Override
    public Room getRoomById(int roomId) { // Method to fetch a Room by its ID
        return roomDao.getRoomById(roomId); // Delegate to RoomDaoImpl for fetching by ID
    }
}
