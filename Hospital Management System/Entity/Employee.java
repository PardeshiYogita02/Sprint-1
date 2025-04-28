package com.Hospital_Management_System.Entity; // Package declaration

import java.math.BigDecimal; // Import for handling salary with precision

import javax.persistence.Column; // JPA annotation for column mapping
import javax.persistence.Entity; // Marks this class as a JPA entity
import javax.persistence.GeneratedValue; // Annotation for auto-generating values
import javax.persistence.GenerationType; // Specifies generation strategies for IDs
import javax.persistence.Id; // Marks the primary key of the entity
import javax.persistence.Table; // Maps this entity to a table in the database

@Entity // Declares this class as a JPA entity
@Table(name = "Employee") // Maps this entity to the "Employee" table
public class Employee {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment strategy for primary key
    @Column(name = "E_ID", length = 10) // Maps to "E_ID" column with a length constraint
    private int eid;

    @Column(name = "Name", columnDefinition = "LONGTEXT") // Stores employee name, allowing long text
    private String name;

    @Column(name = "Salary") // Stores the salary of the employee
    private BigDecimal salary;

    @Column(name = "Sex", length = 50) // Stores the gender of the employee
    private String sex;

    @Column(name = "Mob_No", length = 50) // Stores the mobile number
    private String mobileNumber;

    @Column(name = "Address", nullable = false) // Stores address; must not be null
    private String address;

    @Column(name = "State", length = 50) // Stores state name
    private String state;

    @Column(name = "City", length = 50) // Stores city name
    private String city;

    @Column(name = "Pin_No", length = 50) // Stores pin code
    private String pinNo;

    // Default constructor required by JPA
    public Employee() {}

    // Custom constructor to initialize all fields (excluding ID which is auto-generated)
    public Employee(String name, BigDecimal salary, String sex, String mobileNumber, String address,
                    String state, String city, String pinNo) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.mobileNumber = mobileNumber;
        // Prevent null or empty addresses
        this.address = (address != null && !address.isEmpty()) ? address : "Unknown Address";
        this.state = state;
        this.city = city;
        this.pinNo = pinNo;
    }

    // Unused constructor - can be removed or updated as needed
    public Employee(String string, int i, String string2, String string3, String string4,
                    String string5, String string6, String string7) {
        super();
    }

    // Getters and Setters

    public int getId() {
        return eid;
    }

    public void setId(int id) {
        this.eid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    // Placeholder method for setting mobile number (can be removed or used as alias)
    public void setMobNo(String newMobile) {
        // Intentionally left blank or to be implemented if needed
    }

    // Placeholder method for setting role (useful in inheritance like Doctor, Nurse)
    public void setRole(String role) {
        // To be implemented in subclasses or extended usage
    }

    // For displaying object content in readable form
    @Override
    public String toString() {
        return "Employee [id=" + eid + ", name=" + name + ", salary=" + salary + ", sex=" + sex +
               ", mobileNumber=" + mobileNumber + ", address=" + address + ", state=" + state +
               ", city=" + city + ", pinNo=" + pinNo + "]";
    }
}
