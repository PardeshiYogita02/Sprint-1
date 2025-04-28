package com.Hospital_Management_System.Entity; // Declares the package to which this class belongs

import javax.persistence.*; // Imports necessary JPA annotations

@Entity // Marks this class as a JPA entity
@Table(name = "Room") // Maps this class to the "Room" table in the database
public class Room {

    @Id // Specifies that the following field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates value for the primary key (R_ID)
    @Column(name = "R_ID") // Specifies the column name in the database
    private int id; // Unique identifier for each room

    @Column(name = "Type", length = 50) // Maps to the "Type" column in the database, with a max length of 50 characters
    private String type; // Type of the room (e.g., general, ICU)

    @Column(name = "Capacity", length = 50) // Maps to the "Capacity" column, with a max length of 50
    private int capacity; // Number of beds or capacity of the room

    @Column(name = "Availability", nullable = false) // Maps to the "Availability" column, cannot be null
    private boolean availability; // Whether the room is available or not (true/false)

    @ManyToOne // Indicates that many rooms can be linked to one patient (room-to-patient relationship)
    @JoinColumn(name = "patient_id") // Foreign key column in Room table linking to Patient
    private Patient patient; // Reference to the patient occupying the room

    // Default constructor required by JPA
    public Room() {}

    // Parameterized constructor to create a Room with specified details
    public Room(String type, int capacity, boolean availability) {
        this.type = type; // Sets the room type
        this.capacity = capacity; // Sets the room's capacity
        this.availability = availability; // Sets the room's availability
    }

    // Getters and Setters for each field

    public int getId() {
        return id; // Returns the room ID
    }

    public void setId(int id) {
        this.id = id; // Sets the room ID
    }

    public String getType() {
        return type; // Returns the room type
    }

    public void setType(String type) {
        this.type = type; // Sets the room type
    }

    public int getCapacity() {
        return capacity; // Returns the room capacity
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity; // Sets the room capacity
    }

    public boolean isAvailability() {
        return availability; // Returns whether the room is available or not
    }

    public void setAvailability(boolean availability) {
        this.availability = availability; // Sets the availability status of the room
    }

    public Patient getPatient() {
        return patient; // Returns the patient occupying the room
    }

    public void setPatient(Patient patient) {
        this.patient = patient; // Sets the patient occupying the room
    }

    @Override
    public String toString() {
        // Returns a string representation of the Room object
        return "Room [id=" + id + ", type=" + type + ", capacity=" + capacity + ", availability=" + availability +
               ", patient=" + (patient != null ? patient.getId() : "None") + "]"; // Displays patient ID or "None"
    }
}
