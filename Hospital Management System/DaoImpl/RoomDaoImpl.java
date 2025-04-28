package com.Hospital_Management_System.DaoImpl; // Package declaration for DAO implementation

import java.util.List; // Importing List interface

import org.hibernate.HibernateException; // For handling Hibernate-related exceptions
import org.hibernate.Session; // For establishing session with database
import org.hibernate.Transaction; // For transaction handling
import org.hibernate.query.Query; // For HQL queries

import com.Hospital_Management_System.Dao.RoomDao; // Importing RoomDao interface
import com.Hospital_Management_System.Entity.Room; // Importing Room entity
import com.Hospital_Management_System.util.HibernateUtil; // Importing Hibernate utility class

public class RoomDaoImpl implements RoomDao { // Implementation of RoomDao interface

    // This method is responsible for saving a new room to the database.
    @Override
    public Room createRoom(Room room) { // Method to create and save a new room
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            session.beginTransaction(); // Begin transaction
            session.save(room); // Save room object to the database
            session.getTransaction().commit(); // Commit the transaction
            return room; // Return the saved room
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if operation fails
    }

    // Fetches all the rooms from the database.
    @Override
    public List<Room> getAllRooms() { // Method to fetch all rooms
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Query<Room> query = session.createQuery("FROM Room", Room.class); // HQL query to fetch all rooms
            return query.list(); // Return list of rooms
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if operation fails
    }

    // Fetches a single room from the database based on its ID.
    @Override
    public Room getRoomById(int roomId) { // Method to fetch room by ID
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            return session.get(Room.class, roomId); // Return room by primary key
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if room not found
    }

    // Updates the details of an existing room in the database.
    @Override
    public Room updateRoom(int roomId, Room updatedRoom) { // Method to update room details
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Room room = session.get(Room.class, roomId); // Get existing room by ID
            if (room != null) { // Check if room exists
                room.setType(updatedRoom.getType()); // Update room type
                room.setCapacity(updatedRoom.getCapacity()); // Update room capacity
                room.setAvailability(updatedRoom.isAvailability()); // Update room availability
                session.update(room); // Update the room in database
                tx.commit(); // Commit transaction
                return room; // Return updated room
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return null; // Return null if update fails
    }

    // Deletes a room from the database based on its ID.
    @Override
    public String deleteRoom(int roomId) { // Method to delete a room
        try (Session session = HibernateUtil.getSession()) { // Open Hibernate session
            Transaction tx = session.beginTransaction(); // Begin transaction
            Room room = session.get(Room.class, roomId); // Get room by ID
            if (room != null) { // Check if room exists
                session.delete(room); // Delete the room
                tx.commit(); // Commit transaction
                return "Room deleted successfully"; // Return success message
            }
        } catch (HibernateException e) { // Handle Hibernate exception
            e.printStackTrace(); // Print stack trace
        }
        return "Room deletion failed"; // Return failure message
    }
}
