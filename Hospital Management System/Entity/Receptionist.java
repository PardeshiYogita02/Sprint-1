package com.Hospital_Management_System.Entity; // Package declaration

import javax.persistence.*; // Importing all JPA annotations

@Entity // Marks this class as a JPA entity
@Table(name = "receptionist") // Maps this entity to the "receptionist" table
public class Receptionist {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private int id; // Unique ID for the receptionist

    @OneToOne // One-to-one relationship with Employee
    @JoinColumn(name = "employee_id", nullable = false, unique = true) // Foreign key column referencing Employee
    private Employee employee; // Associated employee details

    @Column(name = "name", nullable = false) // Receptionist name (non-nullable)
    private String name;

    @Column(name = "appointment_no") // Optional appointment number
    private Integer appointmentNo;

    @Column(name = "appointment_date") // Optional appointment date
    private String appointmentDate;

    @Column(name = "booker_name") // Name of the person who booked the appointment
    private String bookerName;

    @Column(name = "booker_contact") // Contact number of the booker
    private String bookerContact;

    @Column(name = "booker_address") // Address of the person who booked the appointment
    private String bookerAddress;


    public Receptionist() {} // Default constructor required by JPA

    public Receptionist(Employee employee) {
        this.employee = employee; // Assigns employee
        this.name = employee.getName(); // Derives name from Employee entity
    }

    public int getId() {
        return id; // Returns the receptionist's ID
    }

    public void setId(int recId) {
        this.id = recId; // Sets the receptionist's ID
    }

    public Employee getEmployee() {
        return employee; // Returns the associated Employee
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the associated Employee
        this.name = employee.getName(); // Updates name from the new employee
    }

    public String getName() {
        return name; // Returns the receptionist’s name
    }

    public void setName(String newName) {
        this.name = newName; // Sets the receptionist’s name
    }

    public Integer getAppointmentNo() {
        return appointmentNo; // Returns the appointment number
    }

    public void setAppointmentNo(Integer appointmentNo) {
        this.appointmentNo = appointmentNo; // Sets the appointment number
    }

    public String getAppointmentDate() {
        return appointmentDate; // Returns the appointment date
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate; // Sets the appointment date
    }

    public String getBookerName() {
        return bookerName; // Returns the name of the person who booked
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName; // Sets the booker name
    }

    public String getBookerContact() {
        return bookerContact; // Returns booker’s contact number
    }

    public void setBookerContact(String bookerContact) {
        this.bookerContact = bookerContact; // Sets booker’s contact number
    }

    public String getBookerAddress() {
        return bookerAddress; // Returns booker’s address
    }

    public void setBookerAddress(String bookerAddress) {
        this.bookerAddress = bookerAddress; // Sets booker’s address
    }

    @Override
    public String toString() {
        // Returns a detailed string representation of the receptionist object
        return "Receptionist {" +
               "  Name = '" + name + '\'' +
               ",  Employee ID = " + (employee != null ? employee.getId() : "N/A") +
               ",  Appointment No = " + appointmentNo +
               ",  Appointment Date = '" + appointmentDate + '\'' +
               ",  Booker Name = '" + bookerName + '\'' +
               ",  Booker Contact Number = '" + bookerContact + '\'' +
               ",  Booker Address = '" + bookerAddress + '\'' +
               "}";
    }
}
