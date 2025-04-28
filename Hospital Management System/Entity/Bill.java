package com.Hospital_Management_System.Entity;


import java.math.BigDecimal;
import javax.persistence.*;
/*
 * Represents a Bill entity in the Hospital Management System.
 * Each bill is linked to a patient and contains the total billing amount.
 */
@Entity
@Table(name = "Bill")
public class Bill {
	 // Primary key with auto-incremented ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_ID")
    private int id;
    // Many-to-One relationship with Patient
    // Each bill belongs to one patient, but a patient can have multiple bills
    @ManyToOne
    @JoinColumn(name = "P_ID", referencedColumnName = "P_ID", nullable = false)
    private Patient patient;
 // Total amount for the bill (non-null)
    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    // ✅ Required: No-Argument Constructor
    public Bill() {
    }

    // ✅ Parameterized Constructor
    public Bill(Patient patient, BigDecimal amount) {
        this.patient = patient;
        this.amount = amount;
    }

    // ✅ Getters & Setters
    public int getId() {
        return id;
    }
 // Setter for Bill ID
    public void setId(int id) {
        this.id = id;
    }
 // Getter for associated Patient
    public Patient getPatient() {
        return patient;
    }
 // Setter for associated Patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
 // Getter for billing amount
    public BigDecimal getAmount() {
        return amount;
    }
    // Setter for billing amount
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    // ToString method for displaying Bill details
    @Override
    public String toString() {
        return "Bill [id=" + id + ", patient=" + patient + ", amount=" + amount + "]";
    }
}
