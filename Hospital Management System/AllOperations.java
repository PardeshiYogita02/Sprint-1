package com.Hospital_Management_System; // Declares the package for the class.

import java.math.BigDecimal; // Imports BigDecimal for handling precise decimal values like money.
import java.time.LocalDate; // Imports LocalDate to handle date values without time.
import java.util.List; // Imports List to work with collections of objects.
import java.util.Scanner; // Imports Scanner to get input from the user.

import org.hibernate.Session; // Imports Session from Hibernate to handle database sessions.
import org.hibernate.Transaction; // Imports Transaction for managing database transactions.
import org.hibernate.query.Query; // Imports Query to perform database queries in Hibernate.

import com.Hospital_Management_System.Entity.*; // Imports all entities (Patient, Employee, Doctor, etc.) from the Hospital Management System.
import com.Hospital_Management_System.Entity.Record; // Imports the Record entity.
import com.Hospital_Management_System.util.HibernateUtil; // Imports HibernateUtil for session factory creation.

public class AllOperations { // Defines the AllOperations class to handle all hospital system operations.

	static Scanner sc = new Scanner(System.in); // Creates a scanner object to take input from the user.

	// ✅ Patient Operations
	public static void patientOperations() {

		while (true) {  // 🔄 Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession(); // Open a new Hibernate session to interact with the database.
			Transaction tx = session.beginTransaction(); // Start a new transaction to make changes to the database.
			System.out.println("\n===== Patient Operations ====="); // Display menu for patient operations.
			System.out.println("1. Add Patient"); // Option to add a new patient.
			System.out.println("2. View Patient"); // Option to view a patient by ID.
			System.out.println("3. Update Patient"); // Option to update an existing patient.
			System.out.println("4. Delete Patient"); // Option to delete a patient by ID.
			System.out.println("5. List All Patients"); // Option to list all patients.
			System.out.println("6. Back"); // Option to go back to previous menu.
			System.out.print("Enter your choice: "); // Prompt user to enter choice.

			if (!sc.hasNextInt()) {  // 🛑 Validate numeric input
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");
				sc.next();  // Consume invalid input
				continue;   // Restart loop
			}

			int choice = sc.nextInt(); // Read user choice.

			switch (choice) { // Switch statement to handle user choice.
			case 1: // Case 1: Add new patient.
				sc.nextLine(); // Consume any leftover newline from previous input.

				System.out.print("Enter Full Name: "); // Prompt for patient's name.
				String name = sc.nextLine(); // Read full name (allowing spaces).

				System.out.print("Enter Age: "); // Prompt for patient's age.
				while (!sc.hasNextInt()) { 
					System.out.println("Invalid input! Please enter a valid number:");
					sc.next(); // Consume invalid input
				}
				int age = sc.nextInt(); // Read age.
				sc.nextLine(); // Consume newline.

				System.out.print("Enter Gender (Male/Female): "); // Prompt for gender.
				String gender = sc.nextLine(); // Read gender.

				while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) { // Validate gender input.
					System.out.println("Invalid gender! Please enter 'Male' or 'Female' only.");
					System.out.print("Enter Gender (Male/Female): "); // Prompt again for gender.
					gender = sc.nextLine(); // Read gender.
				}

				System.out.print("Enter Mobile Number (10 digits): "); // Prompt for mobile number.
				String mobile = sc.nextLine(); // Read mobile number.
				while (!mobile.matches("\\d{10}")) { // Validate mobile number (10 digits).
					System.out.println("Invalid mobile number! Please enter a 10-digit number:");
					mobile = sc.nextLine(); // Prompt for mobile number again.
				}

				// ✅ Ask for Date of Birth manually (with validation)
				LocalDate dob = null; 
				while (dob == null) { // Loop until a valid date is entered.
					System.out.print("Enter DOB (yyyy-MM-dd): "); // Prompt for DOB.
					String dobInput = sc.nextLine(); // Read input for DOB.
					try {
						dob = LocalDate.parse(dobInput); // Try parsing the input into a LocalDate.
					} catch (Exception e) {
						System.out.println("Invalid format! Please enter DOB in yyyy-MM-dd format.");
					}
				}

				// ✅ Create and save patient with full name and manually entered DOB
				Patient patient = new Patient(name, dob, gender, mobile, age); // Create new patient object.
				session.save(patient); // Save the patient to the database.

				System.out.println("✅ Patient Added Successfully!"); // Success message.

				break;

			case 2: // Case 2: View a patient.
				System.out.print("Enter Patient ID: "); // Prompt for patient ID.
				int id = sc.nextInt(); // Read patient ID.
				Patient p = session.get(Patient.class, id); // Retrieve patient using the ID.
				System.out.println(p); // Display patient details.
				break;
			case 3: // Case 3: Update a patient's information.
				System.out.print("Enter Patient ID to update: "); // Prompt for patient ID to update.
				int updateId = sc.nextInt(); // Read ID.
				Patient patientToUpdate = session.get(Patient.class, updateId); // Get patient to update.

				// Check if patient exists
				if (patientToUpdate != null) { // If patient is found.
					sc.nextLine(); // Consume newline.

					System.out.print("Enter New Name: "); // Prompt for new name.
					String newName = sc.nextLine(); // Read new name.
					patientToUpdate.setName(newName); // Update name.

					System.out.print("Enter New Age: "); // Prompt for new age.
					while (!sc.hasNextInt()) { 
						System.out.println("Invalid input! Please enter a valid number:");
						sc.next(); // Consume invalid input
					}
					int newAge = sc.nextInt(); // Read new age.
					patientToUpdate.setAge(newAge); // Update age.
					sc.nextLine(); // Consume newline.

					System.out.print("Enter New Gender (Male/Female): "); // Prompt for new gender.
					String newGender = sc.nextLine().trim().toLowerCase(); // Read and trim new gender.

					// ✅ Validate Gender
					while (!newGender.equalsIgnoreCase("male") && !newGender.equalsIgnoreCase("female")) { // Validate new gender.
						System.out.println("❌ Invalid input! Gender must be 'Male' or 'Female'.");
						System.out.print("Enter New Gender (Male/Female): "); // Prompt again.
						newGender = sc.nextLine().trim().toLowerCase(); // Read new gender.
					}
					patientToUpdate.setGender(newGender); // Update gender.

					System.out.print("Enter New Mobile Number (10 digits): "); // Prompt for new mobile number.
					String newMobile = sc.nextLine().trim(); // Read new mobile number.

					// ✅ Validate Mobile Number (Must be 10 digits)
					while (!newMobile.matches("\\d{10}")) { // Validate new mobile number.
						System.out.println("❌ Invalid Mobile Number! Must be exactly 10 digits.");
						System.out.print("Enter New Mobile Number (10 digits): "); // Prompt again.
						newMobile = sc.nextLine().trim(); // Read new mobile number.
					}
					patientToUpdate.setMobileNumber(newMobile); // Update mobile number.

					// ✅ Ask for new Date of Birth manually (with validation)
					LocalDate newDob = null;
					while (newDob == null) { // Loop until valid DOB is entered.
						System.out.print("Enter New DOB (yyyy-MM-dd): "); // Prompt for new DOB.
						String dobInput = sc.next(); // Read DOB input.
						try {
							newDob = LocalDate.parse(dobInput); // Try to parse the input as a date.
						} catch (Exception e) {
							System.out.println("Invalid format! Please enter DOB in yyyy-MM-dd format.");
						}
					}
					patientToUpdate.setDob(newDob); // Update DOB.

					// ✅ Save the updated patient details
					session.update(patientToUpdate); // Update patient in the database.
					System.out.println("✅ Patient Updated Successfully!"); // Success message.
				} else {
					System.out.println("❌ Patient with ID " + updateId + " not found!"); // If patient not found.
				}

				break;
			case 4: // Case 4: Delete a patient.
				System.out.print("Enter Patient ID to delete: "); // Prompt for ID to delete.
				int deleteId = sc.nextInt(); // Read patient ID.
				Patient patientToDelete = session.get(Patient.class, deleteId); // Retrieve patient.

				if (patientToDelete != null) { // If patient exists.
					session.delete(patientToDelete); // Delete patient from database.
					System.out.println("✅ Patient Deleted Successfully!"); // Success message.
				} else {
					System.out.println("❌ Patient with ID " + deleteId + " not found!"); // If patient not found.
				}

				break;
			case 5: // Case 5: List all patients.
				session.createQuery("from Patient", Patient.class).list().forEach(System.out::println); // Query and display all patients.
				break;
			case 6: // Case 6: Exit patient operations.
				System.out.println("Exiting Patient Operations..."); // Exit message.
				session.close(); // Close session.
				return; // Exit the method.
			default: // Default case: Invalid input.
				System.out.println("Invalid choice!"); // Invalid choice message.
			}
			tx.commit(); // Commit the transaction.
			session.close(); // Close the session.
		}

	}
	// Doctor Operations
	public static void doctorOperations() {

		while (true) {  // Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();  // Open Hibernate session
			Transaction tx = session.beginTransaction();  // Begin transaction

			// Display Doctor Operations Menu
			System.out.println("\n===== Doctor Operations =====");
			System.out.println("1. Assign Employee as Doctor");  // Option to assign an employee as doctor
			System.out.println("2. View Doctor");  // Option to view doctor by ID
			System.out.println("3. Update Doctor");  // Option to update doctor information
			System.out.println("4. Delete Doctor");  // Option to delete doctor by ID
			System.out.println("5. List All Doctors");  // Option to display all doctors
			System.out.println("6. Back");  // Exit from doctor operations
			System.out.print("Enter your choice: ");  // Ask for user choice

			// Validate input to ensure it's an integer
			if (!sc.hasNextInt()) {
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");  // Show error message
				sc.next();  // Consume invalid input
				continue;   // Restart loop
			}

			int choice = sc.nextInt();  // Read user choice

			switch (choice) {  // Perform action based on choice

			case 1:  // Assign Employee as Doctor
				System.out.print("Enter Employee ID to assign as Doctor: ");  // Prompt for Employee ID
				int empId = sc.nextInt();  // Read employee ID

				// Fetch the employee from the database
				Employee emp = session.get(Employee.class, empId);  // Get employee object from DB

				if (emp == null) {  // If employee not found
					System.out.println("❌ Error: Employee with ID " + empId + " not found! Please enter a valid Employee ID.");
					break;  // Exit case
				}

				// Display available departments
				System.out.println("✅ Available Departments:");
				System.out.println("1. Cardiology (Heart Specialist)\n2. Neurology (Brain & Nervous System)\n3. Orthopedics (Bones & Joints)");
				System.out.println("4. Pediatrics (Child Health)\n5. Dermatology (Skin Specialist)\n6. Gynecology (Women's Health)");
				System.out.println("7. Ophthalmology (Eye Specialist)\n8. ENT (Ear, Nose, Throat)\n9. Oncology (Cancer Treatment)");
				System.out.println("10. Anesthesiology (Surgical Anesthesia)");

				System.out.print("Enter Department: ");  // Ask for department
				String department = sc.next();  // Read department input

				// Display available qualifications
				System.out.println("\n✅ Available Qualifications:");
				System.out.println("1. MBBS (Bachelor of Medicine, Bachelor of Surgery)\n2. MD (Doctor of Medicine - Advanced Specialization)");
				System.out.println("3. MS (Master of Surgery)\n4. DM (Doctorate of Medicine - Super Specialty)");
				System.out.println("5. DNB (Diplomate of National Board - Specialist Training)\n6. PhD (Doctor of Philosophy in Medical Sciences)");
				System.out.println("7. MCh (Master of Chirurgiae - Super Specialty in Surgery)");
				System.out.println("8. BAMS (Bachelor of Ayurvedic Medicine & Surgery)\n9. BHMS (Bachelor of Homeopathic Medicine & Surgery)");
				System.out.println("10. Fellowship Certifications (e.g., FACS, FICP, etc.)");

				System.out.print("Enter Qualification: ");  // Ask for qualification
				String qualification = sc.next();  // Read qualification input

				// Create a new Doctor entity using the existing Employee
				Doctor doctor = new Doctor(emp, department, qualification);  // Create new doctor
				session.save(doctor);  // Save doctor to DB

				// Success message
				System.out.println("✅ Success: Employee ID " + empId + " (" + emp.getName() + ") has been assigned as a Doctor in " + department + " department with qualification: " + qualification + ".");
				break;  // Exit case

			case 2:  // View Doctor by ID
				System.out.print("Enter Doctor ID: ");  // Prompt for doctor ID
				int doctorId = sc.nextInt();  // Read doctor ID
				Doctor d = session.get(Doctor.class, doctorId);  // Fetch doctor from DB
				System.out.println(d);  // Print doctor info
				break;  // Exit case

			case 3:  // Update Doctor info
				System.out.print("Enter Doctor ID to update: ");  // Ask for doctor ID
				int updateDoctorId = sc.nextInt();  // Read doctor ID
				sc.nextLine(); // Consume leftover newline

				Doctor doctorToUpdate = session.get(Doctor.class, updateDoctorId);  // Fetch doctor

				// Check if doctor exists
				if (doctorToUpdate == null) {
					System.out.println("❌ Error: Doctor ID " + updateDoctorId + " not found!");
				} else {
					// Display department options
					System.out.println("✅ Available Departments:");
					System.out.println("1. Cardiology (Heart Specialist)\n2. Neurology (Brain & Nervous System)\n3. Orthopedics (Bones & Joints)");
					System.out.println("4. Pediatrics (Child Health)\n5. Dermatology (Skin Specialist)\n6. Gynecology (Women's Health)");
					System.out.println("7. Ophthalmology (Eye Specialist)\n8. ENT (Ear, Nose, Throat)\n9. Oncology (Cancer Treatment)");
					System.out.println("10. Anesthesiology (Surgical Anesthesia)");

					System.out.print("Enter New Department: ");  // Ask for new department
					String newDepartment = sc.nextLine();  // Read new department

					// Show qualifications list
					System.out.println("\n✅ Available Qualifications:");
					System.out.println("1. MBBS (Bachelor of Medicine, Bachelor of Surgery)\n2. MD (Doctor of Medicine - Advanced Specialization)");
					System.out.println("3. MS (Master of Surgery)\n4. DM (Doctorate of Medicine - Super Specialty)");
					System.out.println("5. DNB (Diplomate of National Board - Specialist Training)\n6. PhD (Doctor of Philosophy in Medical Sciences)");
					System.out.println("7. MCh (Master of Chirurgiae - Super Specialty in Surgery)");
					System.out.println("8. BAMS (Bachelor of Ayurvedic Medicine & Surgery)\n9. BHMS (Bachelor of Homeopathic Medicine & Surgery)");
					System.out.println("10. Fellowship Certifications (e.g., FACS, FICP, etc.)");

					System.out.print("Enter New Qualification: ");  // Ask for new qualification
					String newQualification = sc.nextLine();  // Read new qualification

					// Set new values
					doctorToUpdate.setDepartment(newDepartment);  // Update department
					doctorToUpdate.setQualification(newQualification);  // Update qualification

					// Save updated data
					session.update(doctorToUpdate);  // Update doctor in DB

					System.out.println("✅ Doctor Updated Successfully!");  // Confirmation
				}

				break;  // Exit case

			case 4:  // Delete Doctor
				System.out.print("Enter Doctor ID to delete: ");  // Prompt for ID
				int deleteDoctorId = sc.nextInt();  // Read ID
				Doctor doctorToDelete = session.get(Doctor.class, deleteDoctorId);  // Fetch doctor

				// Check if doctor exists
				if (doctorToDelete == null) {
					System.out.println("❌ Error: Doctor ID " + deleteDoctorId + " not found!");
				} else {
					session.delete(doctorToDelete);  // Delete doctor
					System.out.println("✅ Doctor Deleted Successfully!");  // Confirmation
				}

				break;  // Exit case

			case 5:  // List All Doctors
				session.createQuery("from Doctor", Doctor.class).list().forEach(System.out::println);  // Query and print all doctors
				break;  // Exit case

			case 6:  // Exit Doctor operations
				System.out.println("Exiting Doctor Operations...");  // Exit message
				session.close();  // Close session
				return;  // Return to caller

			default:  // Handle invalid choice
				System.out.println("Invalid choice!");  // Show error
			}

			tx.commit();  // Commit transaction
			session.close();  // Close session
		}
	}
	// ✅ Employee Operations
	public static void employeeOperations() {

		while (true) {  // 🔄 Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();  // Open a Hibernate session
			Transaction tx = session.beginTransaction();  // Begin transaction for DB operations

			// Display Employee Operations Menu
			System.out.println("\n===== Employee Operations =====");
			System.out.println("1. Add Employee");  // Option for adding a new employee
			System.out.println("2. View Employee");  // Option for viewing an employee by ID
			System.out.println("3. Update Employee");  // Option for updating employee details
			System.out.println("4. Delete Employee");  // Option for deleting an employee by ID
			System.out.println("5. List All Employees");  // Option to list all employees with roles
			System.out.println("6. Back");  // Option to return to previous menu
			System.out.print("Enter your choice: ");  // Prompt for user input

			// ✅ Validate input to ensure it's an integer
			if (!sc.hasNextInt()) {  // Check if input is not an integer
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");  // Error message
				sc.next();  // Consume invalid input
				continue;   // Restart loop for valid input
			}

			int choice = sc.nextInt();  // Read user choice

			switch (choice) {  // Handle user choice with switch-case

			case 1:  // Add Employee
				System.out.print("Enter Employee Name: ");
				sc.nextLine(); // Consume leftover newline
				String empName = sc.nextLine();  // Read employee name

				System.out.print("Enter Salary: ");
				while (!sc.hasNextBigDecimal()) {  // Validate salary input
					System.out.println("Invalid input! Please enter a valid salary amount:");
					sc.next();  // Consume invalid input
				}
				BigDecimal salary = sc.nextBigDecimal();  // Read salary
				sc.nextLine(); // Consume newline

				// ✅ Validate Gender (Only Male or Female)
				System.out.print("Enter Gender (Male/Female): ");
				String empGender = sc.nextLine().trim();  // Read gender

				while (!empGender.equalsIgnoreCase("Male") && !empGender.equalsIgnoreCase("Female")) {  // Validate gender
					System.out.println("❌ Invalid Gender! Please enter 'Male' or 'Female'.");
					System.out.print("Enter Gender (Male/Female): ");
					empGender = sc.nextLine().trim();
				}

				// ✅ Validate Mobile Number (Must be exactly 10 digits)
				System.out.print("Enter Mobile Number (10 digits only): ");
				String empMobile = sc.nextLine().trim();  // Read mobile number

				while (!empMobile.matches("\\d{10}")) {  // Validate mobile number format
					System.out.println("❌ Invalid Mobile Number! Please enter a 10-digit number.");
					System.out.print("Enter Mobile Number (10 digits only): ");
					empMobile = sc.nextLine().trim();
				}

				// ✅ Now empGender & empMobile are valid

				System.out.print("Enter Address: ");
				String empAddress = sc.nextLine();  // Read employee address

				// ✅ New Fields: State, City, Pincode
				System.out.print("Enter State: ");
				String empState = sc.nextLine();  // Read state

				System.out.print("Enter City: ");
				String empCity = sc.nextLine();  // Read city

				System.out.print("Enter Pincode: ");
				String empPincode = sc.next();  // Read pincode
				while (!empPincode.matches("\\d{6}")) {  // Validate 6-digit pincode
					System.out.println("Invalid Pincode! Enter a valid 6-digit number:");
					empPincode = sc.next();
				}

				// ✅ Create and Save Employee
				Employee emp = new Employee(empName, salary, empGender, empMobile, empAddress, empState, empCity, empPincode);  // Create Employee object
				session.save(emp);  // Save employee to database
				System.out.println("✅ Employee Added Successfully!");  // Confirmation message

				break;

			case 2:  // View Employee
				System.out.print("Enter Employee ID: ");
				int empId = sc.nextInt();  // Read employee ID
				Employee e = session.get(Employee.class, empId);  // Fetch employee from DB
				System.out.println(e);  // Display employee details
				break;

			case 3:  // Update Employee
				System.out.print("Enter Employee ID to update: ");
				int updateEmpId = sc.nextInt();  // Read employee ID
				sc.nextLine(); // Consume newline

				Employee empToUpdate = session.get(Employee.class, updateEmpId);  // Fetch employee to update
				if (empToUpdate == null) {  // Check if employee exists
					System.out.println("❌ Employee not found!");
				} else {
					System.out.println("Updating Employee Details...");

					System.out.print("Enter New Name: ");
					String newName = sc.nextLine();  // Read new name
					if (!newName.isEmpty()) empToUpdate.setName(newName);  // Update name if provided

					System.out.print("Enter New Salary: ");
					String newSalary = sc.nextLine();  // Read new salary
					if (!newSalary.isEmpty()) empToUpdate.setSalary(new BigDecimal(newSalary));  // Update salary if provided

					// ✅ Validate and Update Gender (Only Male or Female)
					System.out.print("Enter New Gender (Male/Female): ");
					String newGender = sc.nextLine().trim();  // Read new gender

					if (!newGender.isEmpty()) {
						while (!newGender.equalsIgnoreCase("Male") && !newGender.equalsIgnoreCase("Female")) {  // Validate gender
							System.out.println("❌ Invalid Gender! Please enter 'Male' or 'Female'.");
							System.out.print("Enter New Gender (Male/Female): ");
							newGender = sc.nextLine().trim();
						}
						empToUpdate.setSex(newGender);  // Update gender if valid
					}

					// ✅ Validate and Update Mobile Number (Must be exactly 10 digits)
					System.out.print("Enter New Mobile Number (10 digits): ");
					String newMobile = sc.nextLine().trim();  // Read new mobile number

					if (!newMobile.isEmpty()) {
						while (!newMobile.matches("\\d{10}")) {  // Validate mobile number
							System.out.println("❌ Invalid Mobile Number! Please enter a 10-digit number.");
							System.out.print("Enter New Mobile Number (10 digits): ");
							newMobile = sc.nextLine().trim();
						}
						empToUpdate.setMobNo(newMobile);  // Update mobile number if valid
					}

					System.out.print("Enter New Address: ");
					String newAddress = sc.nextLine();  // Read new address
					if (!newAddress.isEmpty()) empToUpdate.setAddress(newAddress);  // Update address if provided

					System.out.print("Enter New State: ");
					String newState = sc.nextLine();  // Read new state
					if (!newState.isEmpty()) empToUpdate.setState(newState);  // Update state if provided

					System.out.print("Enter New City: ");
					String newCity = sc.nextLine();  // Read new city
					if (!newCity.isEmpty()) empToUpdate.setCity(newCity);  // Update city if provided

					System.out.print("Enter New Pincode: ");
					String newPincode = sc.nextLine();  // Read new pincode
					if (!newPincode.isEmpty() && newPincode.matches("\\d{6}")) {  // Validate new pincode
						empToUpdate.setPinNo(newPincode);  // Update pincode if valid
					} else if (!newPincode.isEmpty()) {
						System.out.println("Invalid Pincode! Keeping existing.");  // Invalid pincode message
					}

					session.update(empToUpdate);  // Save updates to DB
					System.out.println("✅ Employee Updated Successfully!");  // Confirmation message
				}
				break;

			case 4:  // Delete Employee
				System.out.print("Enter Employee ID to delete: ");
				int empIdToDelete = sc.nextInt();  // Read employee ID
				sc.nextLine(); // Consume newline

				Employee empToDelete = session.get(Employee.class, empIdToDelete);  // Fetch employee to delete

				if (empToDelete != null) {  // Check if employee exists
					session.delete(empToDelete);  // Delete employee
					System.out.println("✅ Employee Deleted Successfully!");  // Confirmation message
				} else {
					System.out.println("❌ Employee ID " + empIdToDelete + " not found! Please enter a valid ID.");  // Error message if not found
				}
				break;

			case 5:  // List All Employees
				Session session1 = HibernateUtil.getSessionFactory().openSession(); // Open new session for listing employees

				try {
					List<Employee> employees = session1.createQuery("from Employee", Employee.class).list();  // Fetch all employees

					if (employees.isEmpty()) {  // Check if employee list is empty
						System.out.println("❌ No employees found in the system.");  // No employees message
					} else {
						System.out.println("🔹 List of Employees with Roles 🔹");

						for (Employee emp1 : employees) {
							String role = "General Employee";  // Default role

							// Check if the employee is a Doctor
							boolean isDoctor = !session1.createQuery("from Doctor d where d.employee.id = :empId", Doctor.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Check if the employee is a Nurse
							boolean isNurse = !session1.createQuery("from Nurse n where n.employee.id = :empId", Nurse.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Check if the employee is a Receptionist
							boolean isReceptionist = !session1.createQuery("from Receptionist r where r.employee.id = :empId", Receptionist.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Determine role based on query results
							if (isDoctor) {
								role = "Doctor";
							} else if (isNurse) {
								role = "Nurse";
							} else if (isReceptionist) {
								role = "Receptionist";
							}

							// Print employee details with role
							System.out.println("🆔 Employee ID: " + emp1.getId() +
									" | Name: " + emp1.getName() +
									" | Role: " + role);
						}
					}
				} catch (Exception e1) {  // Handle any exceptions
					e1.printStackTrace();
				} finally {
					session1.close(); // Close the session to release resources
				}
				break;

			case 6:  // Back/Exit
				System.out.println("Exiting Employee Operations...");  // Exit message
				session.close(); // Properly close the Hibernate session
				return;  // Exit the method

			default:  // Handle invalid menu options
				System.out.println("Invalid choice!");  // Invalid input message
			}

			tx.commit();  // Commit transaction
			session.close();  // Close session
		}
	}
	// ✅ Nurse Operations
	public static void nurseOperations() {
		while (true) { // Infinite loop to keep showing nurse operations menu
			Session session = HibernateUtil.getSessionFactory().openSession(); // Open Hibernate session
			Transaction tx = session.beginTransaction(); // Begin transaction

			try {
				System.out.println("\n===== Nurse Operations ====="); // Display menu
				System.out.println("1. Assign Employee as Nurse");
				System.out.println("2. Assign Nurse to Room");
				System.out.println("3. View Nurse Details");
				System.out.println("4. Update Nurse Information");
				System.out.println("5. Delete Nurse");
				System.out.println("6. List All Nurses");
				System.out.println("7. Nurse Knows Patient Test Reports");
				System.out.println("8. Back");
				System.out.print("Enter your choice: ");

				if (!sc.hasNextInt()) { // Check if input is integer
					System.out.println("❌ Invalid input! Please enter a number between 1-7.");
					sc.next(); // Consume invalid input
					continue; // Loop again
				}

				int choice = sc.nextInt(); // Read user choice

				switch (choice) {
				case 1: // Assign employee as nurse
					System.out.print("Enter Employee ID to assign as Nurse: ");
					int empId = sc.nextInt(); // Read employee ID
					Employee emp = session.get(Employee.class, empId); // Fetch employee object
					Query<Nurse> nurseQuery = session.createQuery("from Nurse where employee.id = :empId", Nurse.class); // Query to check if already nurse
					nurseQuery.setParameter("empId", empId);
					Nurse existingNurse = nurseQuery.uniqueResult(); // Fetch result

					if (existingNurse != null) { // Check if already nurse
						System.out.println("❌ Error: Employee ID " + empId + " is already assigned as a Nurse!");
						break;
					}

					if (emp == null) { // Check if employee exists
						System.out.println("❌ Error: Employee with ID " + empId + " not found!");
						break;
					}

					Nurse newNurse = new Nurse(emp, null, null); // Create new nurse
					session.save(newNurse); // Save to DB
					System.out.println("✅ Employee ID " + empId + " (" + emp.getName() + ") assigned as a Nurse.");
					break;

				case 2: // Assign nurse to room with shift
					System.out.print("Enter Nurse ID: ");
					int nurseId = sc.nextInt(); // Read nurse ID
					Nurse nurse = session.get(Nurse.class, nurseId); // Get nurse

					if (nurse == null) { // Check existence
						System.out.println("❌ Error: Nurse with ID " + nurseId + " not found!");
						break;
					}

					System.out.print("Enter Room Number: ");
					int roomNumber = sc.nextInt(); // Read room number
					Room room = session.get(Room.class, roomNumber); // Get room

					if (room == null) { // Check room existence
						System.out.println("❌ Error: Room with Number " + roomNumber + " not found!");
						break;
					}

					sc.nextLine(); // Consume newline left-over
					System.out.print("Enter Shift (Morning/Night): ");
					String shift = sc.nextLine().trim(); // Read shift

					if (!shift.equalsIgnoreCase("Morning") && !shift.equalsIgnoreCase("Night")) { // Validate shift
						System.out.println("❌ Invalid shift! Please enter 'Morning' or 'Night'.");
						break;
					}

					nurse.setRoom(room); // Set room
					nurse.setShift(shift); // Set shift
					session.update(nurse); // Update nurse

					System.out.println("✅ Nurse ID " + nurseId + " assigned to Room " + roomNumber + " with " + shift + " shift.");
					break;

				case 3: // View nurse details
					System.out.print("Enter Nurse ID: ");
					int viewNurseId = sc.nextInt(); // Read ID
					Nurse viewNurse = session.get(Nurse.class, viewNurseId); // Fetch nurse
					System.out.println(viewNurse != null ? viewNurse : "❌ Nurse Not Found!"); // Display
					break;

				case 4: // Update nurse
					System.out.print("Enter Nurse ID to update: ");
					int updateNurseId = sc.nextInt(); // Read ID
					Nurse updateNurse = session.get(Nurse.class, updateNurseId); // Fetch nurse

					if (updateNurse == null) { // Check existence
						System.out.println("❌ Nurse not found!");
						break;
					}

					System.out.print("Enter new Room Number: ");
					sc.nextLine(); // Consume newline
					String roomInput = sc.nextLine(); // Read input

					if (!roomInput.isEmpty()) { // If not empty
						int newRoomNumber = Integer.parseInt(roomInput); // Convert to int
						Room newRoom = session.get(Room.class, newRoomNumber); // Get room

						if (newRoom == null) { // Check room
							System.out.println("❌ Room not found!");
							break;
						}
						updateNurse.setRoom(newRoom); // Set new room
					}

					System.out.print("Enter new Shift: ");
					String newShift = sc.nextLine(); // Read shift

					if (!newShift.isEmpty()) { // If provided
						updateNurse.setShift(newShift); // Set shift
					}

					session.update(updateNurse); // Update nurse
					System.out.println("✅ Nurse information updated.");
					break;

				case 5: // Delete nurse
					System.out.print("Enter Nurse ID to delete: ");
					int deleteNurseId = sc.nextInt(); // Read ID
					Nurse deleteNurse = session.get(Nurse.class, deleteNurseId); // Fetch nurse

					if (deleteNurse == null) { // Check existence
						System.out.println("❌ Nurse not found!");
						break;
					}

					session.delete(deleteNurse); // Delete
					System.out.println("✅ Nurse deleted successfully.");
					break;

				case 6: // List all nurses
					List<Nurse> nurses = session.createQuery("from Nurse", Nurse.class).list(); // Fetch all
					nurses.forEach(System.out::println); // Print
					break;
				case 7: // Nurse checks patient test reports
					System.out.print("Enter Nurse ID: ");
					int nurseTestId = sc.nextInt(); // Read nurse ID
					Nurse nurseTest = session.get(Nurse.class, nurseTestId); // Get nurse

					if (nurseTest == null) { // Check
						System.out.println("❌ Error: Nurse with ID " + nurseTestId + " not found!");
						break;
					}

					System.out.print("Enter Patient ID: ");
					int patientId = sc.nextInt(); // Read patient ID
					Patient patient = session.get(Patient.class, patientId); // Get patient

					if (patient == null) { // Check
						System.out.println("❌ Error: Patient with ID " + patientId + " not found!");
						break;
					}

					List<TestReport> testReports = session.createQuery("from TestReport where patient.id = :patientId", TestReport.class)
							.setParameter("patientId", patientId)
							.list(); // Fetch reports

					if (testReports.isEmpty()) { // If none found
						System.out.println("❌ No test reports found for Patient ID " + patientId);
					} else {
						System.out.println("🔹 Test Reports for Patient ID " + patientId + ":");
						for (TestReport report : testReports) { // Print each
							System.out.println("📄 Report ID: " + report.getId() +
									" | Test Type: " + report.getTestType() +
									" | Result: " + report.getResult());
						}
					}
					break;

				case 8: // Exit nurse menu
					System.out.println("Exiting Nurse Operations...");
					session.close(); // Close session
					return;

				default: // Invalid choice
					System.out.println("❌ Invalid choice!");
				}

				tx.commit(); // Commit transaction
			} catch (Exception e) {
				tx.rollback(); // Rollback on error
				System.out.println("Error: " + e.getMessage()); // Print error
				e.printStackTrace(); // Stack trace
			} finally {
				session.close(); // Ensure session closed
			}
		}
	}
	// ✅ Receptionist Operations
	public static void receptionistOperations() {

		while (true) { // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession(); // ✅ Open a new Hibernate session
			Transaction tx = session.beginTransaction(); // ✅ Start transaction

			try {
				System.out.println("\n===== Receptionist Operations ====="); // 📋 Display menu
				System.out.println("1. Assign Employee as Receptionist");
				System.out.println("2. Appointment Details");
				System.out.println("3. View Receptionist Details");
				System.out.println("4. List All Receptionists");
				System.out.println("5. Delete Receptionists");
				System.out.println("6. Back");
				System.out.print("Enter your choice: ");

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");
					sc.next(); // ❌ Consume invalid input
					continue; // 🔁 Restart loop
				}

				int choice = sc.nextInt(); // ✅ Read user choice

				switch (choice) {
				case 1: // ➕ Assign Employee as Receptionist
					System.out.print("Enter Employee ID to assign as Receptionist: ");
					int empId = sc.nextInt(); // 🔍 Input employee ID
					Employee emp = session.get(Employee.class, empId); // 📥 Fetch employee

					Query<Receptionist> query = session.createQuery("from Receptionist where employee.id = :empId", Receptionist.class); // 🔁 Check if already receptionist
					query.setParameter("empId", empId);
					Receptionist existingReceptionist = query.uniqueResult();

					if (existingReceptionist != null) {
						System.out.println("❌ Error: Employee ID " + empId + " is already assigned as a Receptionist!");
						return; // 🔚 Return if already exists
					}
					if (emp == null) {
						System.out.println("❌ Error: Employee with ID " + empId + " not found!");
						break; // ❌ Invalid employee ID
					}

					Receptionist newReceptionist = new Receptionist(emp); // ✅ Create new receptionist
					session.save(newReceptionist); // 💾 Save to DB
					System.out.println("✅ Employee ID " + empId + " (" + emp.getName() + ") assigned as a Receptionist.");
					break;

				case 2: // 📅 Appointment Management
				    System.out.println("1️⃣ Book Appointment");
				    System.out.println("2️⃣ Cancel Appointment");
				    System.out.print("Choose an option: ");
				    int appointmentChoice = sc.nextInt();
				    sc.nextLine(); // consume newline

				    System.out.print("Enter Receptionist ID: ");
				    int receptionistId = sc.nextInt();
				    sc.nextLine(); // consume newline
				    Receptionist receptionist = session.get(Receptionist.class, receptionistId);

				    if (receptionist == null) {
				        System.out.println("❌ Error: Receptionist with ID " + receptionistId + " not found!");
				        break;
				    }

				    if (appointmentChoice == 1) {
				        // 📅 Booking Appointment
				        System.out.print("Enter Appointment Number (001-100): ");
				        int appointmentNo = sc.nextInt();
				        sc.nextLine(); // consume newline

				        System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
				        String appointmentDate = sc.nextLine();

				        String bookerName;
				        while (true) {
				            System.out.print("Enter Booker Full Name: ");
				            bookerName = sc.nextLine().trim();
				            if (bookerName.split(" ").length >= 2) {
				                break;
				            } else {
				                System.out.println("❌ Please enter at least first and last name.");
				            }
				        }

				        String bookerContact;
				        while (true) {
				            System.out.print("Enter 10-digit Booker Contact Number: ");
				            bookerContact = sc.nextLine().trim();
				            if (bookerContact.matches("\\d{10}")) {
				                break;
				            } else {
				                System.out.println("❌ Invalid contact number! Must be exactly 10 digits.");
				            }
				        }

				        System.out.print("Enter Booker Address: ");
				        String bookerAddress = sc.nextLine();

				        // ✍️ Setting appointment details
				        receptionist.setAppointmentNo(appointmentNo);
				        receptionist.setAppointmentDate(appointmentDate);
				        receptionist.setBookerName(bookerName);
				        receptionist.setBookerContact(bookerContact);
				        receptionist.setBookerAddress(bookerAddress);

				        session.update(receptionist);
				        System.out.println("✅ Appointment No " + appointmentNo + " scheduled successfully for " + appointmentDate + ".");
				    } else if (appointmentChoice == 2) {
				        // ❌ Cancelling Appointment
				        receptionist.setAppointmentNo(0);
				        receptionist.setAppointmentDate(null);
				        receptionist.setBookerName(null);
				        receptionist.setBookerContact(null);
				        receptionist.setBookerAddress(null);

				        session.update(receptionist);
				        System.out.println("✅ Appointment cancelled successfully for Receptionist ID " + receptionistId + ".");
				    } else {
				        System.out.println("❌ Invalid choice. Please select 1 or 2.");
				    }
				    break;

				case 3: // 🔍 View Receptionist Details
					System.out.print("Enter Receptionist ID: ");
					int recId = sc.nextInt();
					Receptionist rec = session.get(Receptionist.class, recId); // 📥 Fetch receptionist
					System.out.println(rec != null ? rec : "❌ Receptionist Not Found!"); // 📄 Display
					break;

				case 4: // 📋 List All Receptionists
					session.createQuery("from Receptionist", Receptionist.class).list().forEach(System.out::println); // 📄 Print all
					break;

				case 5: // 🗑️ Delete Receptionist
				    System.out.print("Enter Receptionist ID to delete: "); // Prompt for ID
				    int deleteReceptionistId = sc.nextInt(); // Read ID
				    Receptionist receptionistToDelete = session.get(Receptionist.class, deleteReceptionistId); // Fetch receptionist

				    // Check if receptionist exists
				    if (receptionistToDelete == null) {
				        System.out.println("❌ Error: Receptionist ID " + deleteReceptionistId + " not found!");
				    } else {
				        session.delete(receptionistToDelete); // Delete receptionist
				        System.out.println("✅ Receptionist Deleted Successfully!"); // Confirmation
				    }

				    break; // Exit case


				case 6: // 🔙 Exit menu
					System.out.println("Exiting Receptionist Operations...");
					session.close(); // ❎ Close session
					return;

				default: // ❌ Invalid option
					System.out.println("❌ Invalid choice!");
				}

				tx.commit(); // ✅ Commit all changes if no errors occur
			} catch (Exception e) {
				tx.rollback(); // ❌ Rollback if something goes wrong
				System.out.println("Error: " + e.getMessage());
			} finally {
				session.close(); // ✅ Ensure session is closed properly
			}
		}
	}
	// Room Operation

	private static final int MAX_ROOMS = 100; // Max 100 rooms
	private static final int FIXED_CAPACITY = 2; // Fixed capacity

	public static void roomOperations() { // Method to handle room-related operations
		Scanner sc = new Scanner(System.in); // Scanner to read user input

		while (true) { // Loop until user chooses to exit
			Session session = HibernateUtil.getSessionFactory().openSession(); // Open Hibernate session

			System.out.println("1. Create Room"); // Menu option to create room
			System.out.println("2. Allocate Room to Patient"); // Menu option to allocate room
			System.out.println("3. View Room Details"); // Menu option to view room details
			System.out.println("4. Update Room Information"); // Menu option to update room
			System.out.println("5. List All Rooms"); // Menu option to list all rooms
			System.out.println("6. Back"); // Option to exit back
			System.out.print("Enter your choice: "); // Prompt for choice
			int choice = sc.nextInt(); // Read user choice

			switch (choice) { // Switch to handle different options
			case 1: // Case to create room
				createRoom(session, sc); // Call createRoom method
				break; // Break after operation
			case 2: // Case to allocate room
				allocateRoom(session, sc); // Call allocateRoom method
				break; // Break after operation
			case 3: // Case to view room
				viewRoom(session, sc); // Call viewRoom method
				break; // Break after operation
			case 4: // Case to update room
				updateRoom(session, sc); // Call updateRoom method
				break; // Break after operation
			case 5: // Case to list all rooms
				listAllRooms(session); // Call listAllRooms method
				break; // Break after operation
			case 6: // Case to exit
				System.out.println("Exiting Room Operations..."); // Exit message
				session.close(); // Close session
				return; // Return to exit loop
			default: // Default case for invalid input
				System.out.println("❌ Invalid choice! Please try again."); // Error message
			}
		}
	}

	// 1️⃣ Create Room
	private static void createRoom(Session session, Scanner sc) { // Method to create a room
		System.out.print("Enter Room ID (1-100): "); // Prompt for Room ID
		int roomId = sc.nextInt(); // Read Room ID

		if (roomId < 1 || roomId > MAX_ROOMS) { // Validate Room ID range
			System.out.println("❌ Invalid Room ID! Choose between 1-100."); // Error for invalid ID
			return; // Exit method
		}

		if (session.get(Room.class, roomId) != null) { // Check if room already exists
			System.out.println("❌ Room ID already exists!"); // Error if exists
			return; // Exit method
		}

		System.out.print("Enter Room Type (e.g., General, ICU, Private): "); // Prompt for type
		String type = sc.next(); // Read type

		System.out.print("Is Room Available? (true/false): "); // Prompt for availability
		boolean availability = sc.nextBoolean(); // Read availability

		Room room = new Room(type, FIXED_CAPACITY, availability); // Create new Room object
		room.setId(roomId); // Manually setting ID

		Transaction tx = session.beginTransaction(); // Begin transaction
		session.save(room); // Save room to DB
		tx.commit(); // Commit transaction

		System.out.println("✅ Room Created Successfully: " + room); // Success message
	}

	// 2️⃣ Allocate Room to Patient
	private static void allocateRoom(Session session, Scanner sc) { // Method to allocate room
		System.out.print("Enter Room ID to Allocate (1-100): "); // Prompt for Room ID
		int roomId = sc.nextInt(); // Read Room ID

		Room room = session.get(Room.class, roomId); // Fetch room
		if (room == null) { // Check if room exists
			System.out.println("❌ Room does not exist!"); // Error if not
			return; // Exit method
		}

		System.out.println("\nRoom Details: " + room); // Display room details
		if (!room.isAvailability()) { // Check room availability
			System.out.println("❌ Room is already occupied!"); // Error if occupied
			return; // Exit method
		}

		System.out.print("Enter Patient ID: "); // Prompt for Patient ID
		int patientId = sc.nextInt(); // Read Patient ID

		Patient patient = session.get(Patient.class, patientId); // Fetch patient
		if (patient == null) { // Check if patient exists
			System.out.println("❌ Patient not found!"); // Error if not found
			return; // Exit method
		}

		room.setAvailability(false); // Set room as occupied
		room.setPatient(patient); // Link patient to room
		patient.setRoom(room); // Link room to patient

		Transaction tx = session.beginTransaction(); // Begin transaction
		session.update(room); // Update room in DB
		session.update(patient); // Update patient in DB
		tx.commit(); // Commit transaction

		System.out.println("✅ Room " + roomId + " allocated to Patient ID " + patientId); // Success message

		Room assignedRoom = patient.getRoom(); // Fetch assigned room for confirmation

		if (assignedRoom != null) { // If assigned
			System.out.println("🏨 Room Assigned: Room ID " + assignedRoom.getId() + " | Type: " + assignedRoom.getType()); // Display assigned room
		} else { // If not assigned
			System.out.println("🏨 Room Assigned: None"); // Message for no assignment
		}
	}

	// 3️⃣ View Room Details
	private static void viewRoom(Session session, Scanner sc) { // Method to view room
		System.out.print("Enter Room ID: "); // Prompt for Room ID
		int roomId = sc.nextInt(); // Read Room ID

		Room room = session.get(Room.class, roomId); // Fetch room
		if (room == null) { // Check if room exists
			System.out.println("❌ Room not found!"); // Error if not found
		} else { // If room found
			System.out.println("\n🔍 Room Details: " + room); // Display room details
		}
	}
	// 4️⃣ Update Room Information
	private static void updateRoom(Session session, Scanner sc) {
	    System.out.print("Enter Room ID to update: ");
	    int roomId = sc.nextInt();

	    Room room = session.get(Room.class, roomId);
	    if (room == null) {
	        System.out.println("❌ Room not found!");
	        return;
	    }

	    System.out.print("Enter New Availability (true/false): ");
	    boolean availability = sc.nextBoolean();

	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction(); // Start transaction early

	        room.setAvailability(availability); // Update room availability

	        if (availability) { // Room is now available
	            if (room.getPatient() != null) {
	                Patient patient = room.getPatient(); // Fetch linked patient

	                // 🛠 Update both sides of relationship:
	                patient.setRoom(null); // Patient no longer has a room
	                room.setPatient(null); // Room no longer assigned to any patient

	                session.update(patient); // Update patient entity first
	            }
	        }

	        session.update(room); // Then update room entity
	        tx.commit(); // Commit transaction
	        System.out.println("✅ Room updated successfully!");

	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        System.out.println("❌ Error while updating room: " + e.getMessage());
	    }
	}
	
	// 5. List All Rooms
	private static void listAllRooms(Session session) { // Method to list all rooms
		System.out.println("\n📋 List of All Rooms:"); // Heading
		session.createQuery("FROM Room", Room.class).list().forEach(System.out::println); // Query and print all rooms
	}

	// ✅ Test Report Operations

	public static void testReportOperations() { // Method to handle test report operations

		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession(); // Open Hibernate session
			Transaction tx1 = session.beginTransaction();  // ✅ Start transaction

			try {
				System.out.println("\n===== Test Report Operations ====="); // Display menu header
				System.out.println("1. Generate Test Report"); // Option to generate report
				System.out.println("2. View Test Report"); // Option to view report
				System.out.println("3. Update Test Report"); // Option to update report
				System.out.println("4. Delete Test Report"); // Option to delete report
				System.out.println("5. List All Test Reports"); // Option to list all reports
				System.out.println("6. Back"); // Option to go back
				System.out.print("Enter your choice: "); // Prompt for input

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) { // Check if input is an integer
					System.out.println("❌ Invalid input! Please enter a number between 1-6."); // Error for invalid input
					sc.next();  // Consume invalid input
					continue;   // Restart loop
				}

				int choice = sc.nextInt(); // Read user choice

				switch (choice) { // Switch statement to handle different choices
				case 1: // Case to generate test report
					System.out.print("Enter Patient ID: "); // Prompt for Patient ID
					int patientId = sc.nextInt(); // Read Patient ID
					Patient patient = session.get(Patient.class, patientId); // Fetch patient

					if (patient == null) { // Check if patient exists
						System.out.println("❌ Error: Patient with ID " + patientId + " not found!"); // Error if not found
						break; // Exit case
					}

					sc.nextLine(); // Consume newline
					System.out.print("Enter Test Type (CBC, Urinalysis, X-Ray, FBS, ECG, RT-PCR, LFT, KFT): "); // Prompt for test type

					String testType = sc.nextLine(); // Read test type
					System.out.print("Enter Test Result (Positive, Negative, Normal, Abnormal, High, Low, Inconclusive): "); // Prompt for result
					String result = sc.nextLine(); // Read result

					TestReport newReport = new TestReport(patient, testType, result); // Create new TestReport object
					session.save(newReport); // Save report to DB
					System.out.println("✅ Test Report Generated Successfully: " + newReport); // Success message
					break; // Exit case

				case 2: // Case to view test report
					System.out.print("Enter Test Report ID: "); // Prompt for report ID
					int reportId = sc.nextInt(); // Read report ID
					TestReport report = session.get(TestReport.class, reportId); // Fetch report
					System.out.println(report != null ? report : "❌ Test Report Not Found!"); // Display report or error message
					break; // Exit case

				case 3: // Case to update test report
					System.out.print("Enter Test Report ID to Update: "); // Prompt for report ID
					int updateId = sc.nextInt(); // Read report ID
					TestReport reportToUpdate = session.get(TestReport.class, updateId); // Fetch report to update

					if (reportToUpdate == null) { // Check if report exists
						System.out.println("❌ Test Report Not Found!"); // Error if not found
						break; // Exit case
					}

					sc.nextLine(); // Consume newline
					System.out.print("Enter New Test Result: "); // Prompt for new result
					String newResult = sc.nextLine(); // Read new result
					reportToUpdate.setResult(newResult); // Update result
					session.update(reportToUpdate); // Update report in DB
					System.out.println("✅ Test Report Updated Successfully!"); // Success message
					break; // Exit case

				case 4: // Case to delete test report
					System.out.print("Enter Test Report ID to Delete: "); // Prompt for report ID
					int deleteId = sc.nextInt(); // Read report ID
					TestReport reportToDelete = session.get(TestReport.class, deleteId); // Fetch report to delete

					if (reportToDelete == null) { // Check if report exists
						System.out.println("❌ Test Report Not Found!"); // Error if not found
						break; // Exit case
					}

					session.delete(reportToDelete); // Delete report from DB
					System.out.println("✅ Test Report Deleted Successfully!"); // Success message
					break; // Exit case

				case 5: // Case to list all test reports
					List<TestReport> reports = session.createQuery("from TestReport", TestReport.class).list(); // Fetch all reports
					if (reports.isEmpty()) { // Check if reports list is empty
						System.out.println("❌ No Test Reports Found!"); // Error if empty
					} else { // If reports exist
						reports.forEach(System.out::println); // Print all reports
					}
					break; // Exit case
				case 6: // Case to exit
					System.out.println("Exiting Test Report Operations..."); // Exit message
					session.close(); // Close session
					return; // Return to exit method

				default: // Default case for invalid input
					System.out.println("❌ Invalid Choice!"); // Error for invalid choice
				}

				tx1.commit(); // Commit transaction
			} catch (Exception e) { // Catch any exceptions
				tx1.rollback(); // Rollback transaction on error
				System.out.println("Error: " + e.getMessage()); // Print error message
			} finally { // Finally block to ensure session is closed
				session.close(); // Close session
			}
		}
	}
	// ✅ Billing Operations

	public static void billingOperations() {  // Method to handle billing operations

		while (true) {  // Infinite loop to keep the menu running until "Back" (case 6) is selected
			Session session = HibernateUtil.getSessionFactory().openSession();  // Open a Hibernate session
			Transaction tx = session.beginTransaction();  // Begin a new transaction

			try {
				System.out.println("\n===== Billing Operations =====");  // Display billing operation options
				System.out.println("1. Generate Bill");  // Option to generate a new bill
				System.out.println("2. View Bill");  // Option to view a bill
				System.out.println("3. Update Bill");  // Option to update an existing bill
				System.out.println("4. Delete Bill");  // Option to delete a bill
				System.out.println("5. List All Bills");  // Option to list all bills
				System.out.println("6. Back");  // Option to go back to the previous menu
				System.out.print("Enter your choice: ");  // Prompt for user input

				// Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {  // Check if the input is not an integer
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");  // Error message for invalid input
					sc.next();  // Consume the invalid input
					continue;   // Restart the loop to ask for a valid input
				}
				int choice = sc.nextInt();  // Read the user input as an integer
				sc.nextLine(); // Fix Scanner input issue by clearing the buffer

				switch (choice) { // Switch case for billing operations
				case 1: // Case 1 for generating bill
					System.out.print("Enter Patient ID: "); // Prompt user to enter patient ID
					int patientId = sc.nextInt(); // Read patient ID
					sc.nextLine(); // Consume the newline character

					// Fetch Patient object
					Patient patient = session.get(Patient.class, patientId); // Retrieve patient object by ID
					if (patient == null) { // If patient not found
						System.out.println("❌ Error: Patient with ID " + patientId + " not found!"); // Display error message
						break; // Exit case
					}

					// Check if a bill already exists for this patient
					Bill existingBill = session.createQuery("FROM Bill b WHERE b.patient.id = :pid", Bill.class) // Query for existing bill
							.setParameter("pid", patientId) // Set patient ID as query parameter
							.uniqueResult(); // Get single result

					if (existingBill != null) { // If a bill already exists
						System.out.println("\n⚠️ A bill has already been generated for this patient."); // Alert user
						System.out.println("💳 Existing Bill Amount: ₹" + existingBill.getAmount()); // Show existing bill amount
						System.out.println("📄 Bill ID: " + existingBill.getId()); // Display existing bill ID

						System.out.print("🔄 Do you want to update the existing bill? (yes/no): "); // Ask user for update option
						String choice1 = sc.nextLine().trim().toLowerCase(); // Read user input

						if (choice1.equals("yes")) { // If user chooses to update
							// Redirect to update
							System.out.print("Enter New Bill Amount: ₹"); // Prompt for new bill amount
							BigDecimal updatedAmount = sc.nextBigDecimal(); // Read new amount
							sc.nextLine(); // Consume the newline character

							existingBill.setAmount(updatedAmount); // Update bill amount
							session.update(existingBill); // Update bill in session
							session.flush(); // Commit changes

							System.out.println("✅ Bill Updated Successfully. New Amount: ₹" + updatedAmount); // Show success message
						} else {
							System.out.println("ℹ️ No changes made to the existing bill."); // No changes made
						}
						break; // Exit case
					}

					// Continue normal billing flow if no bill exists
					System.out.println("\n============== 📋 PATIENT DETAILS =============="); // Display patient details header
					System.out.println("👤 Name: " + patient.getName()); // Display patient name

					Room assignedRoomid = patient.getRoom(); // Get assigned room
					if (assignedRoomid != null) { // If room is assigned
						System.out.println("🏨 Room Assigned: Room ID " + assignedRoomid.getId() + " | Type: " + assignedRoomid.getType()); // Show room details
					} else {
						System.out.println("🏨 Room Assigned: None"); // If no room assigned
					}

					List<TestReport> testReports = session.createQuery( // Fetch test reports for the patient
							"FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class // Query to get test reports
							).setParameter("pid", patientId).list(); // Set patient ID as query parameter

					if (testReports != null && !testReports.isEmpty()) { // If test reports exist
						System.out.println("🧪 Test Reports:"); // Display test reports header
						for (TestReport report : testReports) { // Loop through each test report
							System.out.println("   • " + report.getTestType() + " — " + report.getResult()); // Display test type and result
						}
					} else {
						System.out.println("🧪 Test Reports: None"); // If no test reports
					}

					System.out.println("\n============== 🧾 BILL CALCULATION =============="); // Bill calculation header

					BigDecimal totalBill = BigDecimal.ZERO; // Initialize total bill amount

					if (assignedRoomid != null) { // If room is assigned
						System.out.print("🔸 Enter Room Cost (per day)\n 1.General:₹1000/-\n 2.Private:₹2000/-\n 3.ICU:₹5000/-: ₹"); // Display room cost options
						BigDecimal roomCost = sc.nextBigDecimal(); // Read room cost

						System.out.print("🔸 Enter Number of Days Stayed: "); // Prompt for number of days stayed
						int days = sc.nextInt(); // Read number of days
						sc.nextLine(); // Consume the newline character

						BigDecimal roomTotal = roomCost.multiply(BigDecimal.valueOf(days)); // Calculate room cost for the stay
						totalBill = totalBill.add(roomTotal); // Add room total to total bill
					}

					System.out.print("🔸 Enter Test Type (CBC, Urinalysis, X-Ray, FBS, ECG, RT-PCR, LFT, KFT): "); // Prompt for test type
					String testType = sc.nextLine(); // Read test type

					System.out.print("🔸 Enter Test Result (Positive, Negative, Normal, Abnormal, High, Low, Inconclusive): "); // Prompt for test result
					String testResult = sc.nextLine(); // Read test result

					System.out.println("💉 Test Cost Chart:"); // Display test cost chart
					System.out.println("   • CBC        : ₹500"); // Display cost for CBC
					System.out.println("   • Urinalysis : ₹400"); // Display cost for Urinalysis
					System.out.println("   • X-Ray      : ₹800"); // Display cost for X-Ray
					System.out.println("   • FBS        : ₹600"); // Display cost for FBS
					System.out.println("   • ECG        : ₹700"); // Display cost for ECG
					System.out.println("   • RT-PCR     : ₹1500"); // Display cost for RT-PCR
					System.out.println("   • LFT        : ₹1200"); // Display cost for LFT
					System.out.println("   • KFT        : ₹1300"); // Display cost for KFT

					System.out.print("🔸 Enter Test Cost: ₹"); // Prompt for test cost
					BigDecimal testCost = sc.nextBigDecimal(); // Read test cost
					sc.nextLine(); // Consume the newline character

					totalBill = totalBill.add(testCost); // Add test cost to total bill

					System.out.println("\n---------------------------------------------"); // Separator line
					System.out.printf("💰 Total (System Calculated): ₹%,.2f%n", totalBill); // Display calculated total
					System.out.println("---------------------------------------------"); // Separator line

					System.out.print("📝 Enter Final Bill Amount to Save: ₹"); // Prompt for final bill amount
					BigDecimal finalAmount = sc.nextBigDecimal(); // Read final bill amount
					sc.nextLine(); // Consume the newline character

					Bill finalBill = new Bill(patient, finalAmount); // Create new Bill object
					session.save(finalBill); // Save bill to session
					session.flush(); // Commit transaction

					System.out.println("\n✅ Bill saved successfully to the system."); // Success message
					System.out.printf("📄 Final Bill Saved: ₹%,.2f%n", finalAmount); // Display final bill
					System.out.println("=============================================\n"); // End of operation
					break; // Exit case

				case 2: // Case 2 for viewing a bill
					System.out.print("Enter Bill ID: "); // Prompt user to enter bill ID
					int billId = sc.nextInt(); // Read bill ID
					sc.nextLine(); // Consume the newline character

					Bill bill = session.get(Bill.class, billId); // Fetch bill object by ID
					if (bill == null) { // If bill not found
						System.out.println("❌ Bill Not Found!"); // Display error message
						break; // Exit case
					}

					Patient patient1 = bill.getPatient(); // Get patient associated with the bill

					System.out.println("\n============== 🧾 BILL DETAILS =============="); // Bill details header
					System.out.println("📄 Bill ID       : " + bill.getId()); // Display bill ID
					System.out.println("👤 Patient Name  : " + patient1.getName()); // Display patient name
					System.out.println("💰 Total Amount  : ₹" + bill.getAmount()); // Display total amount of the bill

					// Show Room Details
					Room room = patient1.getRoom(); // Get room assigned to patient
					if (room != null) { // If room is assigned
						System.out.println("🏨 Room Assigned : " + room.getType() + " (Room ID: " + room.getId() + ")"); // Display room details
					} else {
						System.out.println("🏨 Room Assigned : None"); // If no room assigned
					}

					// Show Test Reports
					List<TestReport> testReports1 = session.createQuery( // Query to fetch test reports for the patient
							"FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class) // Query to fetch test reports
							.setParameter("pid", patient1.getId()) // Set patient ID as query parameter
							.list(); // Get list of test reports

					if (testReports1 != null && !testReports1.isEmpty()) { // If test reports exist
						System.out.println("🧪 Test Reports:"); // Display test reports header
						for (TestReport report : testReports1) { // Loop through each test report
							System.out.println("   • " + report.getTestType() + " — " + report.getResult()); // Display test type and result
						}
					} else {
						System.out.println("🧪 Test Reports: None"); // If no test reports
					}
					break; // Exit case

				case 3: // Case 3 for updating a bill
					System.out.print("Enter Bill ID to Update: "); // Prompt user to enter bill ID
					int updateId = sc.nextInt(); // Read bill ID to update
					sc.nextLine();  // Clear buffer

					Bill billToUpdate = session.get(Bill.class, updateId); // Fetch bill object by ID
					if (billToUpdate == null) { // If bill not found
						System.out.println("❌ Bill Not Found!"); // Display error message
						break; // Exit case
					}

					Patient billPatient = billToUpdate.getPatient(); // Get patient associated with the bill
					System.out.println("\n============== 📋 PATIENT DETAILS =============="); // Patient details header
					System.out.println("👤 Name: " + billPatient.getName()); // Display patient name

					Room assignedRoom = billPatient.getRoom(); // Get room assigned to patient

					BigDecimal totalBill1 = BigDecimal.ZERO; // Initialize total bill amount

					// Room Billing
					if (assignedRoom != null) { // If room is assigned
						System.out.println("\n🏨 Room Info: " + assignedRoom.getType() + " (Room ID: " + assignedRoom.getId() + ")"); // Display room details
						System.out.println("💰 Room Type Charges:"); // Room charge options
						System.out.println("   • General      : ₹1000/day"); // General room charge
						System.out.println("   • Private      : ₹2000/day"); // Private room charge
						System.out.println("   • ICU          : ₹5000/day"); // ICU room charge

						System.out.print("🔸 Enter Room Cost (per day): ₹"); // Prompt for room cost
						BigDecimal roomCost = sc.nextBigDecimal(); // Read room cost

						System.out.print("🔸 Enter Number of Days Stayed: "); // Prompt for number of days
						int days = sc.nextInt(); // Read number of days
						sc.nextLine(); // Clear buffer

						BigDecimal roomTotal = roomCost.multiply(BigDecimal.valueOf(days)); // Calculate room total cost
						totalBill1 = totalBill1.add(roomTotal); // Add room total to the total bill
					}

					// Show Test Reports
					System.out.println("\n============== 🧪 TEST REPORTS =============="); // Test reports header
					List<TestReport> testReports11 = session.createQuery( // Query to fetch test reports for the patient
							"from TestReport where patient.id = :patientId", TestReport.class) // Query for test reports
							.setParameter("patientId", billPatient.getId()) // Set patient ID as query parameter
							.list(); // Get list of test reports

					if (testReports11.isEmpty()) { // If no test reports found
						System.out.println("❌ No Test Reports Found!"); // Display error message
					} else { // If test reports exist
						for (TestReport report : testReports11) { // Loop through each test report
							System.out.println("📄 Report ID: " + report.getId()); // Display report ID
							System.out.println("🔬 Test Type: " + report.getTestType()); // Display test type
							System.out.println("📊 Result: " + report.getResult()); // Display test result
							System.out.println("--------------------------------------"); // Separator line
						}
					}

					// Test Billing
					System.out.println("\n💉 Test Type Charges:"); // Test charges header
					System.out.println("   • CBC        : ₹500"); // CBC charge
					System.out.println("   • Urinalysis : ₹400"); // Urinalysis charge
					System.out.println("   • X-Ray      : ₹800"); // X-Ray charge
					System.out.println("   • FBS        : ₹600"); // FBS charge
					System.out.println("   • ECG        : ₹700"); // ECG charge
					System.out.println("   • RT-PCR     : ₹1500"); // RT-PCR charge
					System.out.println("   • LFT        : ₹1200"); // LFT charge
					System.out.println("   • KFT        : ₹1300"); // KFT charge

					System.out.print("🔸 Enter Test Cost: ₹"); // Prompt for test cost
					BigDecimal testCost1 = sc.nextBigDecimal(); // Read test cost
					sc.nextLine(); // Clear buffer

					totalBill1 = totalBill1.add(testCost1); // Add test cost to total bill

					// Update and persist the bill
					billToUpdate.setAmount(totalBill1); // Set the new amount for the bill
					session.update(billToUpdate); // Update the bill in the session
					session.flush();  // Ensure update is pushed

					System.out.printf("✅ Bill Updated Successfully: ₹%,.2f%n", totalBill1); // Display success message
					break; // Exit case
				case 4: // Case for deleting a bill
					System.out.print("Enter Bill ID to Delete: "); // Prompt user for Bill ID
					int deleteId = sc.nextInt(); // Read Bill ID
					sc.nextLine(); // Clear input buffer

					Bill billToDelete = session.get(Bill.class, deleteId); // Fetch the Bill object by ID
					if (billToDelete == null) { // If the Bill does not exist
						System.out.println("❌ Bill Not Found!"); // Show error message
						break; // Exit the case
					}

					session.delete(billToDelete); // Delete the Bill from session
					session.flush(); // Ensure changes are committed in Hibernate
					System.out.println("✅ Bill Deleted Successfully!"); // Confirm deletion
					break; // Exit the case
				case 5: // Case to display the list of all bills
					List<Bill> bills = session.createQuery("from Bill", Bill.class).list(); // Retrieve all bills from the database

					if (bills.isEmpty()) { // If no bills are found
						System.out.println("❌ No Bills Available!"); // Display a message indicating no bills
					} else { // If there are bills available
						System.out.println("\n🔹📋 List of All Bills 🔹"); // Display header for bill list

						for (Bill b : bills) { // Loop through each bill
							Patient patient11 = b.getPatient(); // Fetch the associated patient for the bill

							System.out.println("\n============== 🧾 BILL ID: " + b.getId() + " =============="); // Display Bill ID
							System.out.println("👤 Patient Name  : " + patient11.getName()); // Display patient name
							System.out.println("💰 Total Amount  : ₹" + b.getAmount()); // Display bill amount

							Room room1 = patient11.getRoom(); // Fetch the room assigned to the patient
							if (room1 != null) { // If the patient has an assigned room
								System.out.println("🏨 Room Assigned : " + room1.getType() + " (Room ID: " + room1.getId() + ")"); // Display room type and ID
							} else { // If no room is assigned
								System.out.println("🏨 Room Assigned : None"); // Indicate no room assigned
							}

							List<TestReport> testReports111 = session.createQuery( // Fetch all test reports for the patient
									"FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class)
									.setParameter("pid", patient11.getId()) // Set patient ID as a parameter
									.list(); // Execute the query and get the result

							if (testReports111 != null && !testReports111.isEmpty()) { // If there are test reports
								System.out.println("🧪 Test Reports:"); // Display header for test reports
								for (TestReport report : testReports111) { // Loop through each test report
									System.out.println("   • " + report.getTestType() + " — " + report.getResult()); // Display test type and result
								}
							} else { // If no test reports exist
								System.out.println("🧪 Test Reports: None"); // Indicate no test reports
							}
						}
					}
					break; // Exit the case
				case 6: // Case to exit billing operations
					System.out.println("Exiting Billing Operations..."); // Display message indicating exit
					return; // Exit the method, effectively ending the program

				default: // Default case for invalid choices
					System.out.println("❌ Invalid choice!"); // Display an error message for invalid input

				} // End of switch statement

				tx.commit(); // Commit the transaction to finalize changes in the database
			} catch (Exception e) { // Catch any exceptions
				if (tx != null && tx.getStatus().canRollback()) { // If transaction is valid and can be rolled back
					tx.rollback(); // Rollback the transaction to avoid partial changes
				}
				e.printStackTrace(); // Print the stack trace for debugging
				System.out.println("⚠️ Error: " + e.getMessage()); // Display the error message
			} finally { // Finally block to ensure cleanup
				session.close(); // Close the session to release resources
			}
		}
	}
	// ✅ Record Operations
	public static void recordOperations() {

		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();  // ✅ Open session once
			Transaction tx = session.beginTransaction();  // ✅ Start transaction

			try {
				System.out.println("\n===== Record Operations =====");  // Displaying the menu header
				System.out.println("1. Add Record");  // Option to add a record
				System.out.println("2. View Record");  // Option to view a record
				System.out.println("3. Update Record");  // Option to update a record
				System.out.println("4. Delete Record");  // Option to delete a record
				System.out.println("5. List All Records");  // Option to list all records
				System.out.println("6. Back");  // Option to go back
				System.out.print("Enter your choice: ");  // Prompt user for input

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {  // Check if input is not an integer
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");  // Invalid input message
					sc.next();  // Consume invalid input
					continue;   // Restart loop to prompt user again
				}

				int choice = sc.nextInt();  // Read user's choice

				switch (choice) {  // Process user's choice
				case 1:  // Case for adding a new record
					System.out.print("Enter Appointment No: ");  // Prompt user for appointment number
					int appointmentNo = sc.nextInt();  // Read appointment number

					// 🔍 Check if appointment number is already recorded
					Record existingRecord = session.createQuery(
							"FROM Record WHERE appointmentNo = :appointmentNo", Record.class)  // Query to check if appointment already exists
							.setParameter("appointmentNo", appointmentNo)  // Set the appointment number parameter
							.uniqueResult();  // Get unique result

					if (existingRecord != null) {  // If the appointment exists, show error message
						System.out.println("❌ Error: Appointment No " + appointmentNo + " is already recorded!");  // Error message
						break;  // Exit case
					}

					System.out.print("Enter Receptionist ID: ");  // Prompt for receptionist ID
					int recId = sc.nextInt();  // Read receptionist ID

					Receptionist receptionist = session.get(Receptionist.class, recId);  // Fetch receptionist by ID
					if (receptionist == null) {  // If receptionist not found, show error message
						System.out.println("❌ Receptionist Not Found!");  // Error message
						break;  // Exit case
					}

					Record newRecord = new Record(appointmentNo, receptionist);  // Create new record with appointment and receptionist
					session.save(newRecord);  // Save new record to database
					System.out.println("✅ Record Added Successfully: " + newRecord);  // Success message
					break;  // Exit case

				case 2:  // Case for viewing a record
					System.out.print("Enter Record No: ");  // Prompt for record number
					int recordNo = sc.nextInt();  // Read record number
					Record record = session.get(Record.class, recordNo);  // Fetch record by number

					if (record != null) {  // If record exists, display details
						System.out.println("📄 Record Details:");  // Header for record details
						System.out.println("Record No       : " + record.getRecordNo());  // Display record number
						System.out.println("Appointment No  : " + record.getAppointmentNo());  // Display appointment number

						Receptionist rcp = record.getReceptionist();  // Get the receptionist associated with the record
						if (rcp != null) {  // If receptionist exists, display receptionist details
							System.out.println("👤 Handled By Receptionist: " + rcp.getName());  // Display receptionist's name
							System.out.println("📅 Appointment Info:");  // Appointment details header
							System.out.println("Booker Name     : " + rcp.getBookerName());  // Display booker's name
							System.out.println("Contact Number  : " + rcp.getBookerContact());  // Display booker's contact number
							System.out.println("Appointment Date: " + rcp.getAppointmentDate());  // Display appointment date
							System.out.println("Address         : " + rcp.getBookerAddress());  // Display booker's address
						}
					} else {  // If record is not found
						System.out.println("❌ Record Not Found!");  // Error message
					}
					break;  // Exit case

				case 3:  // Case for updating a record
					System.out.print("Enter Record No to Update: ");  // Prompt for record number to update
					int updateRecordNo = sc.nextInt();  // Read record number to update
					Record recordToUpdate = session.get(Record.class, updateRecordNo);  // Fetch record to update

					if (recordToUpdate == null) {  // If record not found, show error message
						System.out.println("❌ Record Not Found!");  // Error message
						break;  // Exit case
					}

					// Update appointment number
					System.out.print("Enter New Appointment No: ");  // Prompt for new appointment number
					int newAppointmentNo = sc.nextInt();  // Read new appointment number
					recordToUpdate.setAppointmentNo(newAppointmentNo);  // Update appointment number

					// Update receptionist
					System.out.print("Enter New Receptionist ID to Assign: ");  // Prompt for new receptionist ID
					int newReceptionistId = sc.nextInt();  // Read new receptionist ID
					Receptionist newReceptionist = session.get(Receptionist.class, newReceptionistId);  // Fetch new receptionist by ID

					if (newReceptionist == null) {  // If new receptionist is not found
						System.out.println("❌ Receptionist with ID " + newReceptionistId + " not found!");  // Error message
						break;  // Exit case
					}

					recordToUpdate.setReceptionist(newReceptionist);  // Set new receptionist for record

					session.update(recordToUpdate);  // Update record in database
					System.out.println("✅ Record Updated Successfully!");  // Success message
					System.out.println("New Appointment No: " + newAppointmentNo);  // Display new appointment number
					System.out.println("Assigned to Receptionist: " + newReceptionist.getName());  // Display new receptionist's name
					break;  // Exit case

				case 4:  // Case for deleting a record
					System.out.print("Enter Record No to Delete: ");  // Prompt for record number to delete
					int deleteRecordNo = sc.nextInt();  // Read record number to delete
					Record recordToDelete = session.get(Record.class, deleteRecordNo);  // Fetch record to delete

					if (recordToDelete == null) {  // If record not found
						System.out.println("❌ Record Not Found!");  // Error message
						break;  // Exit case
					}

					session.delete(recordToDelete);  // Delete record from database
					System.out.println("✅ Record Deleted Successfully!");  // Success message
					break;  // Exit case

				case 5:  // Case for listing all records
				    System.out.println("\n📋 Please choose an option:");  
				    System.out.println("1️⃣  List of Appointment Records");  
				    System.out.println("2️⃣  List of Patient Records");  
				    System.out.print("Enter your choice: ");  
				    int listChoice = sc.nextInt();  // Take user input

				    if (listChoice == 1) {  // If user selects Appointment Records
				        List<Record> records = session.createQuery("from Record", Record.class).list();  // Fetch all records
				        if (records.isEmpty()) {  // If no records are found
				            System.out.println("❌ No Records Available!");  // Error message
				        } else {  // If records exist, display them
				            System.out.println("\n📋 All Records:");  // Header for all records
				            for (Record r : records) {  // Loop through all records and display details
				                System.out.println("\nRecord No       : " + r.getRecordNo());  // Display record number
				                System.out.println("Appointment No  : " + r.getAppointmentNo());  // Display appointment number

				                Receptionist rcp = r.getReceptionist();  // Get receptionist for each record
				                if (rcp != null) {  // If receptionist exists, display details
				                    System.out.println("👤 Handled By Receptionist: " + rcp.getName());  // Display receptionist's name
				                    System.out.println("📅 Appointment Info:");  // Appointment info header
				                    System.out.println("Booker Name     : " + rcp.getBookerName());  // Display booker's name
				                    System.out.println("Contact Number  : " + rcp.getBookerContact());  // Display booker's contact
				                    System.out.println("Appointment Date: " + rcp.getAppointmentDate());  // Display appointment date
				                    System.out.println("Address         : " + rcp.getBookerAddress());  // Display booker's address
				                }
				            }
				        }
				    } else if (listChoice == 2) {  // If user selects Patient Bills
				        List<Bill> bills = session.createQuery("from Bill", Bill.class).list();  // Retrieve all bills from the database
				        if (bills.isEmpty()) {  // If no bills are found
				            System.out.println("❌ No Bills Available!");  // Display a message indicating no bills
				        } else {  // If there are bills available
				            System.out.println("\n🔹📋 List of All Bills 🔹");  // Display header for bill list
				            for (Bill b : bills) {  // Loop through each bill
				                Patient patient11 = b.getPatient();  // Fetch the associated patient for the bill

				                System.out.println("\n============== 🧾 BILL ID: " + b.getId() + " ==============");  // Display Bill ID
				                System.out.println("👤 Patient Name  : " + patient11.getName());  // Display patient name
				                System.out.println("💰 Total Amount  : ₹" + b.getAmount());  // Display bill amount

				                Room room1 = patient11.getRoom();  // Fetch the room assigned to the patient
				                if (room1 != null) {  // If the patient has an assigned room
				                    System.out.println("🏨 Room Assigned : " + room1.getType() + " (Room ID: " + room1.getId() + ")");  // Display room type and ID
				                } else {  // If no room is assigned
				                    System.out.println("🏨 Room Assigned : None");  // Indicate no room assigned
				                }

				                List<TestReport> testReports111 = session.createQuery(  // Fetch all test reports for the patient
				                        "FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class)
				                        .setParameter("pid", patient11.getId())  // Set patient ID as a parameter
				                        .list();  // Execute the query and get the result

				                if (testReports111 != null && !testReports111.isEmpty()) {  // If there are test reports
				                    System.out.println("🧪 Test Reports:");  // Display header for test reports
				                    for (TestReport report : testReports111) {  // Loop through each test report
				                        System.out.println("   • " + report.getTestType() + " — " + report.getResult());  // Display test type and result
				                    }
				                } else {  // If no test reports exist
				                    System.out.println("🧪 Test Reports: None");  // Indicate no test reports
				                }
				            }
				        }
				    } else {  // If invalid choice entered
				        System.out.println("❗ Invalid Choice! Please enter 1 or 2.");  // Error message
				    }
				    break;  // Exit case

				case 6:  // Case for exiting the record operations
					System.out.println("Exiting Record Operations...");  // Exit message
					session.close();  // Close session
					return;  // Exit method

				default:  // Case for invalid choice
					System.out.println("❌ Invalid Choice!");  // Error message
				}

				tx.commit();  // Commit transaction
			} catch (Exception e) {  // Handle exceptions
				tx.rollback();  // Rollback transaction in case of error
				System.out.println("Error: " + e.getMessage());  // Display error message
			} finally {
				session.close();  // Close session
			}
		}
	}
}