package com.Hospital_Management_System.Entity; // Defines the package for organizing related classes

import javax.persistence.*; // Imports JPA annotations used for ORM mapping

@Entity // Marks this class as a JPA entity for database mapping
@Table(name = "Nurse") // Maps this entity to the "Nurse" table in the database
public class Nurse {

    @Id // Declares this field as the primary key
    @Column(name = "E_ID") // Maps to the "E_ID" column; also acts as a foreign key to Employee
    private int id;

    @OneToOne // Declares a one-to-one relationship with the Employee entity
    @JoinColumn(name = "E_ID", referencedColumnName = "E_ID") // Specifies the join column and its reference in Employee
    private Employee employee;

    @ManyToOne // Many nurses can be assigned to one room
    @JoinColumn(name = "R_ID") // Maps this field to the "R_ID" column in the Room table
    private Room room;

    @Column(name = "Shift", length = 50) // Maps the shift info to "Shift" column with a max length of 50 characters
    private String shift;

    public Nurse() { // No-argument constructor required by JPA
    }

    public Nurse(Employee employee, Room room, String shift) { // Parameterized constructor for object initialization
        this.employee = employee; // Assign employee object
        this.room = room; // Assign room object
        this.shift = shift; // Set shift value
        this.id = employee.getId(); // Set nurse ID based on employee's ID to maintain 1-to-1 mapping
    }

    public int getId() { // Getter for ID
        return id;
    }

    public void setId(int id) { // Setter for ID
        this.id = id;
    }

    public Employee getEmployee() { // Getter for employee object
        return employee;
    }

    public void setEmployee(Employee employee) { // Setter for employee object
        this.employee = employee; // Set employee
        this.id = employee.getId(); // Sync nurse ID with employee ID
    }

    public Room getRoom() { // Getter for room
        return room;
    }

    public void setRoom(Room room) { // Setter for room
        this.room = room;
    }

    public String getShift() { // Getter for shift
        return shift;
    }

    public void setShift(String shift) { // Setter for shift
        this.shift = shift;
    }

    @Override
    public String toString() { // Custom toString() method for displaying nurse info
        return "Nurse [ID=" + id + ", Name=" + employee.getName() + ", Room=" + (room != null ? room.getId() : "N/A") +
               ", Shift=" + shift + "]";
    }
}
