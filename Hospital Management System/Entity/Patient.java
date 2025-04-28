package com.Hospital_Management_System.Entity; // Package declaration

import java.time.LocalDate; // Import for date representation
import java.util.List; // Import for list of rooms (if multiple)

import javax.persistence.*; // Importing JPA annotations

/*
 * Represents a Patient entity in the Hospital Management System.
 */
@Entity // Declares this class as a JPA entity
@Table(name = "Patient") // Maps this entity to the "Patient" table in the database
public class Patient {

	@Id // Marks this field as the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates value for primary key
	@Column(name = "P_ID") // Maps to the "P_ID" column in the database
	private int patientid;

	@Column(name = "Name", columnDefinition = "LONGTEXT") // Maps name to a long text column
	private String name;

	@Column(name = "DOB") // Maps to "DOB" column for storing date of birth
	private LocalDate dob;

	@Column(name = "Gender", length = 10) // Stores gender with max length of 10 characters
	private String gender;

	@Column(name = "Mob_No", length = 10) // Stores mobile number, length restricted to 10
	private String mobileNumber;

	@Column(name = "Age", length = 10) // Stores patient’s age
	private int age;

	@OneToMany(mappedBy = "patient") // Defines one-to-many relationship with Room (if applicable)
	private List<Room> rooms; // Holds list of rooms if patient can be linked with multiple rooms (optional)

	@OneToOne // One-to-one relationship with a room
	@JoinColumn(name = "room_id") // Foreign key to the room table; maps this entity to one room
	private Room room;

	public Patient(String string, String string2, String string3, String string4, int i) {
		// Placeholder constructor with dummy parameters
	}

	public Patient(int patientid2, String name2, LocalDate dob3, String dob2, String gender2, int age2) {
		// Placeholder constructor with mixed dummy data
	}

	public Patient() {
		// Default constructor required by JPA
	}

	public int getId() {
		return patientid; // Getter for patient ID
	}

	public Patient(String name, LocalDate dob, String gender, String mobileNumber, int age) {
		// Parameterized constructor for full initialization
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.age = age;
	}

	public Patient(int age2, String name2, String dob2, String gender2, String mobileNumber2) {
		// Another placeholder constructor with dummy parameters
	}

	public int getPatientid() {
		return patientid; // Getter for patient ID
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid; // Setter for patient ID
	}

	public String getName() {
		return name; // Getter for name
	}

	public void setName(String name) {
		this.name = name; // Setter for name
	}

	public LocalDate getDob() {
		return dob; // Getter for date of birth
	}

	public void setDob(LocalDate dob) {
		this.dob = dob; // Setter for date of birth
	}

	public String getGender() {
		return gender; // Getter for gender
	}

	public void setGender(String gender) {
		this.gender = gender; // Setter for gender
	}

	public String getMobileNumber() {
		return mobileNumber; // Getter for mobile number
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber; // Setter for mobile number
	}

	public int getAge() {
		return age; // Getter for age
	}

	public void setAge(int age) {
		this.age = age; // Setter for age
	}

	@Override
	public String toString() {
		// Returns a readable string representation of the patient object
		return "Patient [id=" + patientid + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", mobileNumber="
				+ mobileNumber + ", age=" + age + "]";
	}

	public List<TestReport> getTestReports() {
		// Placeholder method for getting test reports
		return null;
	}

	public Room getRoom() {
		return room; // Getter for associated room
	}

	public void setRoom(Room room) {
		this.room = room; // Setter for associated room
	}
}
