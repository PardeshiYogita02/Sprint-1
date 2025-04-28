package com.Hospital_Management_System.Entity; // Declares the package this class belongs to

import javax.persistence.*; // Imports necessary JPA annotations

@Entity // Marks this class as a JPA entity
@Table(name = "record") // Maps this class to the "record" table in the database
public class Record {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates value for primary key (recordNo)
    private int recordNo; // Unique identifier for each record

    @Column(name = "appointment_no", nullable = false) // Column for appointment number, cannot be null
    private int appointmentNo; // Stores the appointment number related to the record

    @ManyToOne // Many records can be associated with one receptionist
    @JoinColumn(name = "receptionist_id", nullable = false) // Foreign key linking to receptionist table, non-null
    private Receptionist receptionist; // Reference to the associated receptionist

    public Record() {} // Default constructor required by JPA

    // Parameterized constructor to create Record instances with required data
    public Record(int appointmentNo, Receptionist receptionist) {
        this.appointmentNo = appointmentNo; // Sets appointment number
        this.receptionist = receptionist; // Sets associated receptionist
    }

    public int getRecordNo() {
        return recordNo; // Returns the record number
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo; // Sets the record number
    }

    public int getAppointmentNo() {
        return appointmentNo; // Returns the appointment number
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo; // Sets the appointment number
    }

    public Receptionist getReceptionist() {
        return receptionist; // Returns the associated receptionist
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist; // Sets the associated receptionist
    }

    @Override
    public String toString() {
        // Returns a string representation of the Record object
        return "Record {" +
                "recordNo=" + recordNo +
                ", appointmentNo=" + appointmentNo +
                ", receptionist=" + receptionist.getName() + // Displays receptionist's name
                '}';
    }
}
