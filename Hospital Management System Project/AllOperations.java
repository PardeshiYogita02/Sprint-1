package com.Hospital_Management_System;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.query.QueryProducer;
import org.hibernate.query.Query;

import com.Hospital_Management_System.Entity.*;
import com.Hospital_Management_System.Entity.Record;
import com.Hospital_Management_System.util.HibernateUtil;
//import com.mysql.cj.xdevapi.SessionImpl;

public class AllOperations {

	static Scanner sc = new Scanner(System.in);

	// ✅ Patient Operations
	public static void patientOperations() {
		
		while (true) {  // 🔄 Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("\n===== Patient Operations =====");
			System.out.println("1. Add Patient");
			System.out.println("2. View Patient");
			System.out.println("3. Update Patient");
			System.out.println("4. Delete Patient");
			System.out.println("5. List All Patients");
			System.out.println("6. Back");
			System.out.print("Enter your choice: ");

			if (!sc.hasNextInt()) {  // 🛑 Validate numeric input
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");
				sc.next();  // Consume invalid input
				continue;   // Restart loop
			}

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
			    sc.nextLine(); // Consume any leftover newline from previous input

			    System.out.print("Enter Full Name: ");
			    String name = sc.nextLine(); // Allows full name with spaces

			    System.out.print("Enter Age: ");
			    while (!sc.hasNextInt()) { 
			        System.out.println("Invalid input! Please enter a valid number:");
			        sc.next();
			    }
			    int age = sc.nextInt();
			    sc.nextLine(); // Consume newline

			    System.out.print("Enter Gender (Male/Female): ");
			    String gender = sc.nextLine();

			    while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
			        System.out.println("Invalid gender! Please enter 'Male' or 'Female' only.");
			        System.out.print("Enter Gender (Male/Female): ");
			        gender = sc.nextLine();
			    }

			    System.out.print("Enter Mobile Number (10 digits): ");
			    String mobile = sc.nextLine();
			    while (!mobile.matches("\\d{10}")) {
			        System.out.println("Invalid mobile number! Please enter a 10-digit number:");
			        mobile = sc.nextLine();
			    }

			    // ✅ Ask for Date of Birth manually (with validation)
			    LocalDate dob = null;
			    while (dob == null) {
			        System.out.print("Enter DOB (yyyy-MM-dd): ");
			        String dobInput = sc.nextLine();
			        try {
			            dob = LocalDate.parse(dobInput);
			        } catch (Exception e) {
			            System.out.println("Invalid format! Please enter DOB in yyyy-MM-dd format.");
			        }
			    }

			    // ✅ Create and save patient with full name and manually entered DOB
			    Patient patient = new Patient(name, dob, gender, mobile, age);
			    session.save(patient);

			    System.out.println("✅ Patient Added Successfully!");

			    break;

			case 2:
				System.out.print("Enter Patient ID: ");
				int id = sc.nextInt();
				Patient p = session.get(Patient.class, id);
				System.out.println(p);
				break;
			case 3:
				System.out.print("Enter Patient ID to update: ");
				int updateId = sc.nextInt();
				Patient patientToUpdate = session.get(Patient.class, updateId);

				// Check if patient exists
				if (patientToUpdate != null) {
					sc.nextLine(); // Consume newline

					System.out.print("Enter New Name: ");
					String newName = sc.nextLine();
					patientToUpdate.setName(newName);

					System.out.print("Enter New Age: ");
					while (!sc.hasNextInt()) { 
						System.out.println("Invalid input! Please enter a valid number:");
						sc.next();
					}
					int newAge = sc.nextInt();
					patientToUpdate.setAge(newAge);
					sc.nextLine(); // Consume newline

					System.out.print("Enter New Gender (Male/Female): ");
					String newGender = sc.nextLine().trim().toLowerCase();

					// ✅ Validate Gender
					while (!newGender.equalsIgnoreCase("male") && !newGender.equalsIgnoreCase("female")) {
						System.out.println("❌ Invalid input! Gender must be 'Male' or 'Female'.");
						System.out.print("Enter New Gender (Male/Female): ");
						newGender = sc.nextLine().trim().toLowerCase();
					}
					patientToUpdate.setGender(newGender);

					System.out.print("Enter New Mobile Number (10 digits): ");
					String newMobile = sc.nextLine().trim();

					// ✅ Validate Mobile Number (Must be 10 digits)
					while (!newMobile.matches("\\d{10}")) {
						System.out.println("❌ Invalid Mobile Number! Must be exactly 10 digits.");
						System.out.print("Enter New Mobile Number (10 digits): ");
						newMobile = sc.nextLine().trim();
					}
					patientToUpdate.setMobileNumber(newMobile);

					// ✅ Ask for new Date of Birth manually (with validation)
					LocalDate newDob = null;
					while (newDob == null) {
						System.out.print("Enter New DOB (yyyy-MM-dd): ");
						String dobInput = sc.next();
						try {
							newDob = LocalDate.parse(dobInput); // Convert user input into LocalDate
						} catch (Exception e) {
							System.out.println("Invalid format! Please enter DOB in yyyy-MM-dd format.");
						}
					}
					patientToUpdate.setDob(newDob);

					// ✅ Save the updated patient details
					session.update(patientToUpdate);
					System.out.println("✅ Patient Updated Successfully!");
				} else {
					System.out.println("❌ Patient with ID " + updateId + " not found!");
				}

				break;
			case 4:
				System.out.print("Enter Patient ID to delete: ");
				int deleteId = sc.nextInt();
				Patient patientToDelete = session.get(Patient.class, deleteId);

				if (patientToDelete != null) {
					session.delete(patientToDelete);
					System.out.println("✅ Patient Deleted Successfully!");
				} else {
					System.out.println("❌ Patient with ID " + deleteId + " not found!");
				}

				break;
			case 5:
				session.createQuery("from Patient", Patient.class).list().forEach(System.out::println);
				break;
			case 6:
				System.out.println("Exiting Patient Operations...");
				session.close();
				return;
			default:
				System.out.println("Invalid choice!");
			}
			tx.commit();
			session.close();
		}

	}
	//✅ Doctor Operations
	public static void doctorOperations() {
		
		while (true) {  // 🔄 Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("\n===== Doctor Operations =====");
			System.out.println("1. Assign Employee as Doctor");
			System.out.println("2. View Doctor");
			System.out.println("3. Update Doctor");
			System.out.println("4. Delete Doctor");
			System.out.println("5. List All Doctors");
			System.out.println("6. Back");
			System.out.print("Enter your choice: ");

			// ✅ Validate input to ensure it's an integer
			if (!sc.hasNextInt()) {
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");
				sc.next();  // Consume invalid input
				continue;   // Restart loop
			}
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Employee ID to assign as Doctor: ");
				int empId = sc.nextInt();

				// Fetch the employee from the database
				Employee emp = session.get(Employee.class, empId);

				if (emp == null) {
					System.out.println("❌ Error: Employee with ID " + empId + " not found! Please enter a valid Employee ID.");
					break;
				}
				System.out.println("✅ Available Departments:");
				System.out.println("1. Cardiology (Heart Specialist)\n2. Neurology (Brain & Nervous System)\n3. Orthopedics (Bones & Joints)");
				System.out.println("4. Pediatrics (Child Health)\n5. Dermatology (Skin Specialist)\n6. Gynecology (Women's Health)");
				System.out.println("7. Ophthalmology (Eye Specialist)\n8. ENT (Ear, Nose, Throat)\n9. Oncology (Cancer Treatment)");
				System.out.println("10. Anesthesiology (Surgical Anesthesia)");

				System.out.print("Enter Department: ");
				String department = sc.next();

				System.out.println("\n✅ Available Qualifications:");
				System.out.println("1. MBBS (Bachelor of Medicine, Bachelor of Surgery)\n2. MD (Doctor of Medicine - Advanced Specialization)");
				System.out.println("3. MS (Master of Surgery)\n4. DM (Doctorate of Medicine - Super Specialty)");
				System.out.println("5. DNB (Diplomate of National Board - Specialist Training)\n6. PhD (Doctor of Philosophy in Medical Sciences)");
				System.out.println("7. MCh (Master of Chirurgiae - Super Specialty in Surgery)");
				System.out.println("8. BAMS (Bachelor of Ayurvedic Medicine & Surgery)\n9. BHMS (Bachelor of Homeopathic Medicine & Surgery)");
				System.out.println("10. Fellowship Certifications (e.g., FACS, FICP, etc.)");
				System.out.print("Enter Qualification: ");
				String qualification = sc.next();

				// Create a new Doctor entity using the existing Employee
				Doctor doctor = new Doctor(emp, department, qualification);
				session.save(doctor);

				System.out.println("✅ Success: Employee ID " + empId + " (" + emp.getName() + ") has been assigned as a Doctor in " + department + " department with qualification: " + qualification + ".");
				break;
			case 2:
				System.out.print("Enter Doctor ID: ");
				int doctorId = sc.nextInt();
				Doctor d = session.get(Doctor.class, doctorId);
				System.out.println(d);
				break;
			case 3:
			    System.out.print("Enter Doctor ID to update: ");
			    int updateDoctorId = sc.nextInt();
			    sc.nextLine(); // Consume leftover newline

			    Doctor doctorToUpdate = session.get(Doctor.class, updateDoctorId);

			    // ✅ Check if doctor exists
			    if (doctorToUpdate == null) {
			        System.out.println("❌ Error: Doctor ID " + updateDoctorId + " not found!");
			    } else {
			    	System.out.println("✅ Available Departments:");
					System.out.println("1. Cardiology (Heart Specialist)\n2. Neurology (Brain & Nervous System)\n3. Orthopedics (Bones & Joints)");
					System.out.println("4. Pediatrics (Child Health)\n5. Dermatology (Skin Specialist)\n6. Gynecology (Women's Health)");
					System.out.println("7. Ophthalmology (Eye Specialist)\n8. ENT (Ear, Nose, Throat)\n9. Oncology (Cancer Treatment)");
					System.out.println("10. Anesthesiology (Surgical Anesthesia)");

			        System.out.print("Enter New Department: ");
			        String newDepartment = sc.nextLine();

			    	System.out.println("\n✅ Available Qualifications:");
					System.out.println("1. MBBS (Bachelor of Medicine, Bachelor of Surgery)\n2. MD (Doctor of Medicine - Advanced Specialization)");
					System.out.println("3. MS (Master of Surgery)\n4. DM (Doctorate of Medicine - Super Specialty)");
					System.out.println("5. DNB (Diplomate of National Board - Specialist Training)\n6. PhD (Doctor of Philosophy in Medical Sciences)");
					System.out.println("7. MCh (Master of Chirurgiae - Super Specialty in Surgery)");
					System.out.println("8. BAMS (Bachelor of Ayurvedic Medicine & Surgery)\n9. BHMS (Bachelor of Homeopathic Medicine & Surgery)");
					System.out.println("10. Fellowship Certifications (e.g., FACS, FICP, etc.)");
			        System.out.print("Enter New Qualification: ");
			        String newQualification = sc.nextLine();

			        // ✅ Set new values
			        doctorToUpdate.setDepartment(newDepartment);
			        doctorToUpdate.setQualification(newQualification);

			        // ✅ Update in DB
			        session.update(doctorToUpdate);

			        System.out.println("✅ Doctor Updated Successfully!");
			    }

			    break;

			case 4:
			    System.out.print("Enter Doctor ID to delete: ");
			    int deleteDoctorId = sc.nextInt();
			    Doctor doctorToDelete = session.get(Doctor.class, deleteDoctorId);

			    // ✅ Check if doctor exists
			    if (doctorToDelete == null) {
			        System.out.println("❌ Error: Doctor ID " + deleteDoctorId + " not found!");
			    } else {
			        session.delete(doctorToDelete);
			        System.out.println("✅ Doctor Deleted Successfully!");
			    }
			    
			    break;

			case 5:
				session.createQuery("from Doctor", Doctor.class).list().forEach(System.out::println);
				break;
			case 6:
				System.out.println("Exiting Doctor Operations...");
				session.close();
				return;
			default:
				System.out.println("Invalid choice!");
			}
			tx.commit();
			session.close();
		}
	}
	// ✅ Employee Operations
	public static void employeeOperations() {
		
		while (true) {  // 🔄 Keep displaying the menu until "Back" is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("\n===== Employee Operations =====");
			System.out.println("1. Add Employee");
			System.out.println("2. View Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. List All Employees");
			System.out.println("6. Back");
			System.out.print("Enter your choice: ");

			// ✅ Validate input to ensure it's an integer
			if (!sc.hasNextInt()) {
				System.out.println("❌ Invalid input! Please enter a number between 1-6.");
				sc.next();  // Consume invalid input
				continue;   // Restart loop
			}
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Employee Name: ");
				sc.nextLine(); // Consume newline
				String empName = sc.nextLine();  

				System.out.print("Enter Salary: ");
				while (!sc.hasNextBigDecimal()) {  
					System.out.println("Invalid input! Please enter a valid salary amount:");
					sc.next(); 
				}
				BigDecimal salary = sc.nextBigDecimal();
				sc.nextLine(); // Consume newline

				// ✅ Validate Gender (Only Male or Female)
				System.out.print("Enter Gender (Male/Female): ");
				String empGender = sc.nextLine().trim();

				while (!empGender.equalsIgnoreCase("Male") && !empGender.equalsIgnoreCase("Female")) {
					System.out.println("❌ Invalid Gender! Please enter 'Male' or 'Female'.");
					System.out.print("Enter Gender (Male/Female): ");
					empGender = sc.nextLine().trim();
				}

				// ✅ Validate Mobile Number (Must be exactly 10 digits)
				System.out.print("Enter Mobile Number (10 digits only): ");
				String empMobile = sc.nextLine().trim();

				while (!empMobile.matches("\\d{10}")) {
					System.out.println("❌ Invalid Mobile Number! Please enter a 10-digit number.");
					System.out.print("Enter Mobile Number (10 digits only): ");
					empMobile = sc.nextLine().trim();
				}

				// ✅ Now empGender & empMobile are valid

				System.out.print("Enter Address: ");
				String empAddress = sc.nextLine();

				// ✅ New Fields: State, City, Pincode
				System.out.print("Enter State: ");
				String empState = sc.nextLine();

				System.out.print("Enter City: ");
				String empCity = sc.nextLine();

				System.out.print("Enter Pincode: ");
				String empPincode = sc.next();
				while (!empPincode.matches("\\d{6}")) { // Validate 6-digit pincode
					System.out.println("Invalid Pincode! Enter a valid 6-digit number:");
					empPincode = sc.next();
				}

				// ✅ Create and Save Employee
				Employee emp = new Employee(empName, salary, empGender, empMobile, empAddress, empState, empCity, empPincode);
				session.save(emp);
				System.out.println("✅ Employee Added Successfully!");

				break;
			case 2:
				System.out.print("Enter Employee ID: ");
				int empId = sc.nextInt();
				Employee e = session.get(Employee.class, empId);
				System.out.println(e);
				break;
				
			case 3:
				System.out.print("Enter Employee ID to update: ");
				int updateEmpId = sc.nextInt();
				sc.nextLine(); // Consume newline

				Employee empToUpdate = session.get(Employee.class, updateEmpId);
				if (empToUpdate == null) {
					System.out.println("❌ Employee not found!");
				} else {
					System.out.println("Updating Employee Details...");

					System.out.print("Enter New Name : ");
					String newName = sc.nextLine();
					if (!newName.isEmpty()) empToUpdate.setName(newName);

					System.out.print("Enter New Salary :");
					String newSalary = sc.nextLine();
					if (!newSalary.isEmpty()) empToUpdate.setSalary(new BigDecimal(newSalary));

					// ✅ Validate and Update Gender (Only Male or Female)
					System.out.print("Enter New Gender (Male/Female): ");
					String newGender = sc.nextLine().trim();

					if (!newGender.isEmpty()) {
						while (!newGender.equalsIgnoreCase("Male") && !newGender.equalsIgnoreCase("Female")) {
							System.out.println("❌ Invalid Gender! Please enter 'Male' or 'Female'.");
							System.out.print("Enter New Gender (Male/Female): ");
							newGender = sc.nextLine().trim();
						}
						empToUpdate.setSex(newGender); // ✅ Update gender if valid
					}

					// ✅ Validate and Update Mobile Number (Must be exactly 10 digits)
					System.out.print("Enter New Mobile Number (10 digits): ");
					String newMobile = sc.nextLine().trim();

					if (!newMobile.isEmpty()) {
						while (!newMobile.matches("\\d{10}")) {
							System.out.println("❌ Invalid Mobile Number! Please enter a 10-digit number.");
							System.out.print("Enter New Mobile Number (10 digits): ");
							newMobile = sc.nextLine().trim();
						}
						empToUpdate.setMobNo(newMobile); // ✅ Update mobile number if valid
					}


					System.out.print("Enter New Address:  ");
					String newAddress = sc.nextLine();
					if (!newAddress.isEmpty()) empToUpdate.setAddress(newAddress);

					System.out.print("Enter New State: ");
					String newState = sc.nextLine();
					if (!newState.isEmpty()) empToUpdate.setState(newState);

					System.out.print("Enter New City : ");
					String newCity = sc.nextLine();
					if (!newCity.isEmpty()) empToUpdate.setCity(newCity);

					System.out.print("Enter New Pincode :");
					String newPincode = sc.nextLine();
					if (!newPincode.isEmpty() && newPincode.matches("\\d{6}")) {
						empToUpdate.setPinNo(newPincode);
					} else if (!newPincode.isEmpty()) {
						System.out.println("Invalid Pincode! Keeping existing.");
					}

					session.update(empToUpdate);
					System.out.println("✅ Employee Updated Successfully!");
				}

				break;
			case 4:
				System.out.print("Enter Employee ID to delete: ");
				int empIdToDelete = sc.nextInt();
				sc.nextLine(); // Consume newline

				// ✅ Fetch Employee by ID
				Employee empToDelete = session.get(Employee.class, empIdToDelete);

				if (empToDelete != null) {
					// ✅ Employee exists → Proceed with deletion
					session.delete(empToDelete);
					System.out.println("✅ Employee Deleted Successfully!");
				} else {
					// ❌ Employee not found → Show error message
					System.out.println("❌ Employee ID " + empIdToDelete + " not found! Please enter a valid ID.");
				}

			case 5:
				
				Session session1 = HibernateUtil.getSessionFactory().openSession(); // Ensure session is opened

				try {
					List<Employee> employees = session1.createQuery("from Employee", Employee.class).list();

					if (employees.isEmpty()) {
						System.out.println("❌ No employees found in the system.");
					} else {
						System.out.println("🔹 List of Employees with Roles 🔹");

						for (Employee emp1 : employees) {
							String role = "General Employee"; // Default role

							// Check if the employee is a Doctor
							boolean isDoctor = !session1.createQuery("from Doctor d where d.employee.id = :empId", Doctor.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Check if the employee is a Nurse
							boolean isNurse = !session1.createQuery("from Nurse n where n.employee.id = :empId", Nurse.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Check if the employee is a Receptionist
							boolean isReceptionist = !session1.createQuery("from Receptionist r where r.employee.id = :empId", Receptionist.class)
									.setParameter("empId", emp1.getId()).list().isEmpty();

							// Assign the correct role
							if (isDoctor) {
								role = "Doctor";
							} else if (isNurse) {
								role = "Nurse";
							} else if (isReceptionist) {
								role = "Receptionist";
							}

							// Print Employee Details
							System.out.println("🆔 Employee ID: " + emp1.getId() +
									" | Name: " + emp1.getName() +
									" | Role: " + role);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					session1.close(); // Ensure session is closed properly
				}
				break;
			case 6:
				System.out.println("Exiting Employee Operations...");
				session.close(); // Properly close the Hibernate session
				return;

			default:
				System.out.println("Invalid choice!");
			}
			tx.commit();
			session.close();
		}

	}

	// ✅ Nurse Operations
	public static void nurseOperations() {
        while (true) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            try {
                System.out.println("\n===== Nurse Operations =====");
                System.out.println("1. Assign Employee as Nurse");
                System.out.println("2. Assign Nurse to Room");
                System.out.println("3. View Nurse Details");
                System.out.println("4. Update Nurse Information");
                System.out.println("5. Delete Nurse");
                System.out.println("6. List All Nurses");
                System.out.println("7. Nurse Knows Patient Test Reports");
                System.out.println("8. Back");
                System.out.print("Enter your choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("❌ Invalid input! Please enter a number between 1-7.");
                    sc.next();
                    continue;
                }

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Employee ID to assign as Nurse: ");
                        int empId = sc.nextInt();
                        Employee emp = session.get(Employee.class, empId);
                        Query<Nurse> nurseQuery = session.createQuery("from Nurse where employee.id = :empId", Nurse.class);
                        nurseQuery.setParameter("empId", empId);
                        Nurse existingNurse = nurseQuery.uniqueResult();

                        if (existingNurse != null) {
                            System.out.println("❌ Error: Employee ID " + empId + " is already assigned as a Nurse!");
                            break;
                        }

                        if (emp == null) {
                            System.out.println("❌ Error: Employee with ID " + empId + " not found!");
                            break;
                        }

                        Nurse newNurse = new Nurse(emp, null, null);
                        session.save(newNurse);
                        System.out.println("✅ Employee ID " + empId + " (" + emp.getName() + ") assigned as a Nurse.");
                        break;

                    case 2:
                        System.out.print("Enter Nurse ID: ");
                        int nurseId = sc.nextInt();
                        Nurse nurse = session.get(Nurse.class, nurseId);

                        if (nurse == null) {
                            System.out.println("❌ Error: Nurse with ID " + nurseId + " not found!");
                            break;
                        }

                        System.out.print("Enter Room Number: ");
                        int roomNumber = sc.nextInt();
                        Room room = session.get(Room.class, roomNumber);

                        if (room == null) {
                            System.out.println("❌ Error: Room with Number " + roomNumber + " not found!");
                            break;
                        }

                        sc.nextLine(); // Consume newline left-over
                        System.out.print("Enter Shift (Morning/Night): ");
                        String shift = sc.nextLine().trim();

                        if (!shift.equalsIgnoreCase("Morning") && !shift.equalsIgnoreCase("Night")) {
                            System.out.println("❌ Invalid shift! Please enter 'Morning' or 'Night'.");
                            break;
                        }

                        nurse.setRoom(room);
                        nurse.setShift(shift);
                        session.update(nurse);
                        
                        System.out.println("✅ Nurse ID " + nurseId + " assigned to Room " + roomNumber + " with " + shift + " shift.");
                        break;


                    case 3:
                        System.out.print("Enter Nurse ID: ");
                        int viewNurseId = sc.nextInt();
                        Nurse viewNurse = session.get(Nurse.class, viewNurseId);
                        System.out.println(viewNurse != null ? viewNurse : "❌ Nurse Not Found!");
                        break;

                    case 4:
                        System.out.print("Enter Nurse ID to update: ");
                        int updateNurseId = sc.nextInt();
                        Nurse updateNurse = session.get(Nurse.class, updateNurseId);

                        if (updateNurse == null) {
                            System.out.println("❌ Nurse not found!");
                            break;
                        }

                        System.out.print("Enter new Room Number: ");
                        sc.nextLine(); // Consume newline left-over
                        String roomInput = sc.nextLine();

                        if (!roomInput.isEmpty()) {
                            int newRoomNumber = Integer.parseInt(roomInput);
                            Room newRoom = session.get(Room.class, newRoomNumber);

                            if (newRoom == null) {
                                System.out.println("❌ Room not found!");
                                break;
                            }
                            updateNurse.setRoom(newRoom);
                        }

                        System.out.print("Enter new Shift: ");
                        String newShift = sc.nextLine();

                        if (!newShift.isEmpty()) {
                            updateNurse.setShift(newShift);
                        }

                        session.update(updateNurse);
                        System.out.println("✅ Nurse information updated.");
                        break;

                    case 5:
                        System.out.print("Enter Nurse ID to delete: ");
                        int deleteNurseId = sc.nextInt();
                        Nurse deleteNurse = session.get(Nurse.class, deleteNurseId);

                        if (deleteNurse == null) {
                            System.out.println("❌ Nurse not found!");
                            break;
                        }

                        session.delete(deleteNurse);
                        System.out.println("✅ Nurse deleted successfully.");
                        break;

                    case 6:
                    	List<Nurse> nurses = session.createQuery("from Nurse", Nurse.class).list();
                        nurses.forEach(System.out::println);
                        break;
                    case 7:
                        System.out.print("Enter Nurse ID: ");
                        int nurseTestId = sc.nextInt();
                        Nurse nurseTest = session.get(Nurse.class, nurseTestId);

                        if (nurseTest == null) {
                            System.out.println("❌ Error: Nurse with ID " + nurseTestId + " not found!");
                            break;
                        }

                        System.out.print("Enter Patient ID: ");
                        int patientId = sc.nextInt();
                        Patient patient = session.get(Patient.class, patientId);

                        if (patient == null) {
                            System.out.println("❌ Error: Patient with ID " + patientId + " not found!");
                            break;
                        }

                        List<TestReport> testReports = session.createQuery("from TestReport where patient.id = :patientId", TestReport.class)
                                .setParameter("patientId", patientId)
                                .list();

                        if (testReports.isEmpty()) {
                            System.out.println("❌ No test reports found for Patient ID " + patientId);
                        } else {
                            System.out.println("🔹 Test Reports for Patient ID " + patientId + ":");
                            for (TestReport report : testReports) {
                                System.out.println("📄 Report ID: " + report.getId() +
                                        " | Test Type: " + report.getTestType() +
                                        " | Result: " + report.getResult());
                            }
                        }
                        break;


                    case 8:
                        System.out.println("Exiting Nurse Operations...");
                        session.close();
                        return;

                    default:
                        System.out.println("❌ Invalid choice!");
                }

                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }
		// ✅ Receptionist Operations

	public static void receptionistOperations() {


		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = session.beginTransaction();  // ✅ Start transaction
			
			try {
				System.out.println("\n===== Receptionist Operations =====");
				System.out.println("1. Assign Employee as Receptionist");
				System.out.println("2. Book Appointment");
				System.out.println("3. View Receptionist Details");
				System.out.println("4. List All Receptionists");
				System.out.println("5. Delete Receptionists");
				System.out.println("6. Back");
				System.out.print("Enter your choice: ");

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");
					sc.next();  // Consume invalid input
					continue;   // Restart loop
				}

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.print("Enter Employee ID to assign as Receptionist: ");
					int empId = sc.nextInt();
					Employee emp = session.get(Employee.class, empId);
					Query<Receptionist> query = session.createQuery("from Receptionist where employee.id = :empId", Receptionist.class);
					query.setParameter("empId", empId);
					Receptionist existingReceptionist = query.uniqueResult();

					if (existingReceptionist != null) {
						System.out.println("❌ Error: Employee ID " + empId + " is already assigned as a Receptionist!");
						return;
					}
					if (emp == null) {
						System.out.println("❌ Error: Employee with ID " + empId + " not found!");
						break;
					}

					Receptionist newReceptionist = new Receptionist(emp);
					session.save(newReceptionist);
					System.out.println("✅ Employee ID " + empId + " (" + emp.getName() + ") assigned as a Receptionist.");
					break;

				case 2:
				    System.out.print("Enter Receptionist ID to Book Appointment: ");
				    int receptionistId = sc.nextInt();
				    Receptionist receptionist = session.get(Receptionist.class, receptionistId);

				    if (receptionist == null) {
				        System.out.println("❌ Error: Receptionist with ID " + receptionistId + " not found!");
				        break;
				    }

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

				    // Read and validate 10-digit mobile number
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

				    receptionist.setAppointmentNo(appointmentNo);
				    receptionist.setAppointmentDate(appointmentDate);
				    receptionist.setBookerName(bookerName);
				    receptionist.setBookerContact(bookerContact);
				    receptionist.setBookerAddress(bookerAddress);
				    

				    session.update(receptionist);

				    System.out.println("✅ Appointment No " + appointmentNo + " scheduled for " + appointmentDate + " under Receptionist ID " + receptionistId + ".");
				    break;
				case 3:
					System.out.print("Enter Receptionist ID: ");
					int recId = sc.nextInt();
					Receptionist rec = session.get(Receptionist.class, recId);
					System.out.println(rec != null ? rec : "❌ Receptionist Not Found!");
					break;

				case 4:
					session.createQuery("from Receptionist", Receptionist.class).list().forEach(System.out::println);
					break;
				case 5:
				    System.out.print("Enter Receptionist ID to delete: ");
				    int deleteReceptionistId = sc.nextInt();
				    Receptionist ReceptionistToDelete = session.get(Receptionist.class, deleteReceptionistId);

				    // ✅ Check if doctor exists
				    if (ReceptionistToDelete == null) {
				        System.out.println("❌ Error: Receptionist ID " + deleteReceptionistId + " not found!");
				    } else {
				        session.delete(ReceptionistToDelete);
				        System.out.println("✅ Receptionist Deleted Successfully!");
				    }
				    
				    break;

				case 6:
					System.out.println("Exiting Receptionist Operations...");
					session.close();
					return;
				default:
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

	public static void roomOperations() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			Session session = HibernateUtil.getSessionFactory().openSession();

			System.out.println("1. Create Room");
			System.out.println("2. Allocate Room to Patient");
			System.out.println("3. View Room Details");
			System.out.println("4. Update Room Information");
			System.out.println("5. Delete Room");
			System.out.println("6. List All Rooms");
			System.out.println("7. Back");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				createRoom(session, sc);
				break;
			case 2:
				allocateRoom(session, sc);
				break;
			case 3:
				viewRoom(session, sc);
				break;
			case 4:
				updateRoom(session, sc);
				break;
			case 5:
				deleteRoom(session, sc);
				break;
			case 6:
				listAllRooms(session);
				break;
			case 7:
				System.out.println("Exiting Room Operations...");
				session.close();
				return;
			default:
				System.out.println("❌ Invalid choice! Please try again.");
			}
		}
	}

	// 1️⃣ Create Room
	private static void createRoom(Session session, Scanner sc) {
		System.out.print("Enter Room ID (1-100): ");
		int roomId = sc.nextInt();

		if (roomId < 1 || roomId > MAX_ROOMS) {
			System.out.println("❌ Invalid Room ID! Choose between 1-100.");
			return;
		}

		if (session.get(Room.class, roomId) != null) {
			System.out.println("❌ Room ID already exists!");
			return;
		}

		System.out.print("Enter Room Type (e.g., General, ICU, Private): ");
		String type = sc.next();

		System.out.print("Is Room Available? (true/false): ");
		boolean availability = sc.nextBoolean();

		Room room = new Room(type, FIXED_CAPACITY, availability);
		room.setId(roomId); // Manually setting ID

		Transaction tx = session.beginTransaction();
		session.save(room);
		tx.commit();

		System.out.println("✅ Room Created Successfully: " + room);
	}

	// 2️⃣ Allocate Room to Patient
	private static void allocateRoom(Session session, Scanner sc) {
	    System.out.print("Enter Room ID to Allocate (1-100): ");
	    int roomId = sc.nextInt();

	    Room room = session.get(Room.class, roomId);
	    if (room == null) {
	        System.out.println("❌ Room does not exist!");
	        return;
	    }

	    System.out.println("\nRoom Details: " + room);
	    if (!room.isAvailability()) {
	        System.out.println("❌ Room is already occupied!");
	        return;
	    }

	    System.out.print("Enter Patient ID: ");
	    int patientId = sc.nextInt();

	    Patient patient = session.get(Patient.class, patientId);
	    if (patient == null) {
	        System.out.println("❌ Patient not found!");
	        return;
	    }

	    // Assign patient to room
	    room.setAvailability(false);
	    room.setPatient(patient); // Link patient to room
	    patient.setRoom(room);    // Ensure patient references the room

	    Transaction tx = session.beginTransaction();
	    session.update(room);
	    session.update(patient);
	    tx.commit();

	    System.out.println("✅ Room " + roomId + " allocated to Patient ID " + patientId);

	    // 🔍 Fetch and show assigned room details
	    Room assignedRoom = patient.getRoom();  // Should now be set

	    if (assignedRoom != null) {
	        System.out.println("🏨 Room Assigned: Room ID " + assignedRoom.getId() + " | Type: " + assignedRoom.getType());
	    } else {
	        System.out.println("🏨 Room Assigned: None");
	    }
	}

	// 3️⃣ View Room Details
	private static void viewRoom(Session session, Scanner sc) {
		System.out.print("Enter Room ID: ");
		int roomId = sc.nextInt();

		Room room = session.get(Room.class, roomId);
		if (room == null) {
			System.out.println("❌ Room not found!");
		} else {
			System.out.println("\n🔍 Room Details: " + room);
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

		room.setAvailability(availability);
		Transaction tx = session.beginTransaction();
		session.update(room);
		tx.commit();

		System.out.println("✅ Room updated successfully!");
	}

	// 5️⃣ Delete Room
	private static void deleteRoom(Session session, Scanner sc) {
		System.out.print("Enter Room ID to delete: ");
		int roomId = sc.nextInt();

		Room room = session.get(Room.class, roomId);
		if (room == null) {
			System.out.println("❌ Room not found!");
			return;
		}

		Transaction tx = session.beginTransaction();
		session.delete(room);
		tx.commit();

		System.out.println("✅ Room deleted successfully!");
	}

	// 6️⃣ List All Rooms
	private static void listAllRooms(Session session) {
		System.out.println("\n📋 List of All Rooms:");
		session.createQuery("FROM Room", Room.class).list().forEach(System.out::println);
	}


	// ✅ Test Report Operations

	public static void testReportOperations() {
		


		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session.beginTransaction();  // ✅ Start transaction

			try {
				System.out.println("\n===== Test Report Operations =====");
				System.out.println("1. Generate Test Report");
				System.out.println("2. View Test Report");
				System.out.println("3. Update Test Report");
				System.out.println("4. Delete Test Report");
				System.out.println("5. List All Test Reports");
				System.out.println("6. Back");
				System.out.print("Enter your choice: ");

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");
					sc.next();  // Consume invalid input
					continue;   // Restart loop
				}

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.print("Enter Patient ID: ");
					int patientId = sc.nextInt();
					Patient patient = session.get(Patient.class, patientId);

					if (patient == null) {
						System.out.println("❌ Error: Patient with ID " + patientId + " not found!");
						break;
					}

					sc.nextLine();
					System.out.print("Enter Test Type (CBC, Urinalysis, X-Ray, FBS, ECG, RT-PCR, LFT, KFT): ");

					String testType = sc.nextLine();
					System.out.print("Enter Test Result (Positive, Negative, Normal, Abnormal, High, Low, Inconclusive): ");
					String result = sc.nextLine();

					TestReport newReport = new TestReport(patient, testType, result);
					session.save(newReport);
					System.out.println("✅ Test Report Generated Successfully: " + newReport);
					break;

				case 2:
					System.out.print("Enter Test Report ID: ");
					int reportId = sc.nextInt();
					TestReport report = session.get(TestReport.class, reportId);
					System.out.println(report != null ? report : "❌ Test Report Not Found!");
					break;

				case 3:
					System.out.print("Enter Test Report ID to Update: ");
					int updateId = sc.nextInt();
					TestReport reportToUpdate = session.get(TestReport.class, updateId);

					if (reportToUpdate == null) {
						System.out.println("❌ Test Report Not Found!");
						break;
					}

					sc.nextLine();
					System.out.print("Enter New Test Result: ");
					String newResult = sc.nextLine();
					reportToUpdate.setResult(newResult);
					session.update(reportToUpdate);
					System.out.println("✅ Test Report Updated Successfully!");
					break;

				case 4:
					System.out.print("Enter Test Report ID to Delete: ");
					int deleteId = sc.nextInt();
					TestReport reportToDelete = session.get(TestReport.class, deleteId);

					if (reportToDelete == null) {
						System.out.println("❌ Test Report Not Found!");
						break;
					}

					session.delete(reportToDelete);
					System.out.println("✅ Test Report Deleted Successfully!");
					break;

				case 5:
					List<TestReport> reports = session.createQuery("from TestReport", TestReport.class).list();
					if (reports.isEmpty()) {
						System.out.println("❌ No Test Reports Found!");
					} else {
						reports.forEach(System.out::println);
					}
					break;
				case 6:
					System.out.println("Exiting Test Report Operations...");
					session.close();
					return;

				default:
					System.out.println("❌ Invalid Choice!");
				}

				tx1.commit();
			} catch (Exception e) {
				tx1.rollback();
				System.out.println("Error: " + e.getMessage());
			} finally {
				session.close();
			}
		}
	}
	// ✅ Billing Operations

	public static void billingOperations() {
		
		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();  // ✅ Open session once
			Transaction tx = session.beginTransaction();  // ✅ Start transaction

			try {
				System.out.println("\n===== Billing Operations =====");
				System.out.println("1. Generate Bill");
				System.out.println("2. View Bill");
				System.out.println("3. Update Bill");
				System.out.println("4. Delete Bill");
				System.out.println("5. List All Bills");
				System.out.println("6. Back");
				System.out.print("Enter your choice: ");

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");
					sc.next();  // Consume invalid input
					continue;   // Restart loop
				}
				int choice = sc.nextInt();
				sc.nextLine(); // ✅ Fix Scanner Input Issue

				switch (choice) {
				case 1:
				    System.out.print("Enter Patient ID: ");
				    int patientId = sc.nextInt();
				    sc.nextLine();

				    // Fetch Patient object
				    Patient patient = session.get(Patient.class, patientId);
				    if (patient == null) {
				        System.out.println("❌ Error: Patient with ID " + patientId + " not found!");
				        break;
				    }

				    // 💡 Check if a bill already exists for this patient
				    Bill existingBill = session.createQuery("FROM Bill b WHERE b.patient.id = :pid", Bill.class)
				            .setParameter("pid", patientId)
				            .uniqueResult();

				    if (existingBill != null) {
				        System.out.println("\n⚠️ A bill has already been generated for this patient.");
				        System.out.println("💳 Existing Bill Amount: ₹" + existingBill.getAmount());
				        System.out.println("📄 Bill ID: " + existingBill.getId());

				        System.out.print("🔄 Do you want to update the existing bill? (yes/no): ");
				        String choice1 = sc.nextLine().trim().toLowerCase();

				        if (choice1.equals("yes")) {
				            // ➤ Redirect to update
				            System.out.print("Enter New Bill Amount: ₹");
				            BigDecimal updatedAmount = sc.nextBigDecimal();
				            sc.nextLine();

				            existingBill.setAmount(updatedAmount);
				            session.update(existingBill);
				            session.flush();

				            System.out.println("✅ Bill Updated Successfully. New Amount: ₹" + updatedAmount);
				        } else {
				            System.out.println("ℹ️ No changes made to the existing bill.");
				        }
				        break;
				    }

				    // 🧾 Continue normal billing flow if no bill exists
				    System.out.println("\n============== 📋 PATIENT DETAILS ==============");
				    System.out.println("👤 Name: " + patient.getName());

				    Room assignedRoomid = patient.getRoom();
				    if (assignedRoomid != null) {
				        System.out.println("🏨 Room Assigned: Room ID " + assignedRoomid.getId() + " | Type: " + assignedRoomid.getType());
				    } else {
				        System.out.println("🏨 Room Assigned: None");
				    }

				    List<TestReport> testReports = session.createQuery(
				            "FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class
				    ).setParameter("pid", patientId).list();

				    if (testReports != null && !testReports.isEmpty()) {
				        System.out.println("🧪 Test Reports:");
				        for (TestReport report : testReports) {
				            System.out.println("   • " + report.getTestType() + " — " + report.getResult());
				        }
				    } else {
				        System.out.println("🧪 Test Reports: None");
				    }

				    System.out.println("\n============== 🧾 BILL CALCULATION ==============");

				    BigDecimal totalBill = BigDecimal.ZERO;

				    if (assignedRoomid != null) {
				        System.out.print("🔸 Enter Room Cost (per day)\n 1.General:₹1000/-\n 2.Private:₹2000/-\n 3.ICU:₹5000/-: ₹");
				        BigDecimal roomCost = sc.nextBigDecimal();

				        System.out.print("🔸 Enter Number of Days Stayed: ");
				        int days = sc.nextInt();
				        sc.nextLine();

				        BigDecimal roomTotal = roomCost.multiply(BigDecimal.valueOf(days));
				        totalBill = totalBill.add(roomTotal);
				    }

				    System.out.print("🔸 Enter Test Type (CBC, Urinalysis, X-Ray, FBS, ECG, RT-PCR, LFT, KFT): ");
				    String testType = sc.nextLine();

				    System.out.print("🔸 Enter Test Result (Positive, Negative, Normal, Abnormal, High, Low, Inconclusive): ");
				    String testResult = sc.nextLine();

				    System.out.println("💉 Test Cost Chart:");
				    System.out.println("   • CBC        : ₹500");
				    System.out.println("   • Urinalysis : ₹400");
				    System.out.println("   • X-Ray      : ₹800");
				    System.out.println("   • FBS        : ₹600");
				    System.out.println("   • ECG        : ₹700");
				    System.out.println("   • RT-PCR     : ₹1500");
				    System.out.println("   • LFT        : ₹1200");
				    System.out.println("   • KFT        : ₹1300");

				    System.out.print("🔸 Enter Test Cost: ₹");
				    BigDecimal testCost = sc.nextBigDecimal();
				    sc.nextLine();

				    totalBill = totalBill.add(testCost);

				    System.out.println("\n---------------------------------------------");
				    System.out.printf("💰 Total (System Calculated): ₹%,.2f%n", totalBill);
				    System.out.println("---------------------------------------------");

				    System.out.print("📝 Enter Final Bill Amount to Save: ₹");
				    BigDecimal finalAmount = sc.nextBigDecimal();
				    sc.nextLine();

				    Bill finalBill = new Bill(patient, finalAmount);
				    session.save(finalBill);
				    session.flush();

				    System.out.println("\n✅ Bill saved successfully to the system.");
				    System.out.printf("📄 Final Bill Saved: ₹%,.2f%n", finalAmount);
				    System.out.println("=============================================\n");
				    break;
				case 2:
				    System.out.print("Enter Bill ID: ");
				    int billId = sc.nextInt();
				    sc.nextLine();

				    Bill bill = session.get(Bill.class, billId);
				    if (bill == null) {
				        System.out.println("❌ Bill Not Found!");
				        break;
				    }

				    Patient patient1 = bill.getPatient();

				    System.out.println("\n============== 🧾 BILL DETAILS ==============");
				    System.out.println("📄 Bill ID       : " + bill.getId());
				    System.out.println("👤 Patient Name  : " + patient1.getName());
				    System.out.println("💰 Total Amount  : ₹" + bill.getAmount());

				    // ➤ Show Room Details
				    Room room = patient1.getRoom();
				    if (room != null) {
				        System.out.println("🏨 Room Assigned : " + room.getType() + " (Room ID: " + room.getId() + ")");
				    } else {
				        System.out.println("🏨 Room Assigned : None");
				    }

				    // ➤ Show Test Reports
				    List<TestReport> testReports1 = session.createQuery(
				            "FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class)
				            .setParameter("pid", patient1.getId())
				            .list();

				    if (testReports1 != null && !testReports1.isEmpty()) {
				        System.out.println("🧪 Test Reports:");
				        for (TestReport report : testReports1) {
				            System.out.println("   • " + report.getTestType() + " — " + report.getResult());
				        }
				    } else {
				        System.out.println("🧪 Test Reports: None");
				    }
				    break;

				case 3:
				    System.out.print("Enter Bill ID to Update: ");
				    int updateId = sc.nextInt();
				    sc.nextLine();  // Clear buffer

				    Bill billToUpdate = session.get(Bill.class, updateId);
				    if (billToUpdate == null) {
				        System.out.println("❌ Bill Not Found!");
				        break;
				    }

				    Patient billPatient = billToUpdate.getPatient();
				    System.out.println("\n============== 📋 PATIENT DETAILS ==============");
				    System.out.println("👤 Name: " + billPatient.getName());

				    Room assignedRoom = billPatient.getRoom();

				    BigDecimal totalBill1 = BigDecimal.ZERO;

				    // ➤ Room Billing
				    if (assignedRoom != null) {
				        System.out.println("\n🏨 Room Info: " + assignedRoom.getType() + " (Room ID: " + assignedRoom.getId() + ")");
				        System.out.println("💰 Room Type Charges:");
				        System.out.println("   • General      : ₹1000/day");
				        System.out.println("   • Private      : ₹2000/day");
				        System.out.println("   • ICU          : ₹5000/day");

				        System.out.print("🔸 Enter Room Cost (per day): ₹");
				        BigDecimal roomCost = sc.nextBigDecimal();

				        System.out.print("🔸 Enter Number of Days Stayed: ");
				        int days = sc.nextInt();
				        sc.nextLine();

				        BigDecimal roomTotal = roomCost.multiply(BigDecimal.valueOf(days));
				        totalBill1 = totalBill1.add(roomTotal);
				    }

				    // ➤ Show Test Reports
				    System.out.println("\n============== 🧪 TEST REPORTS ==============");
				    List<TestReport> testReports11 = session.createQuery(
				        "from TestReport where patient.id = :patientId", TestReport.class)
				        .setParameter("patientId", billPatient.getId())
				        .list();

				    if (testReports11.isEmpty()) {
				        System.out.println("❌ No Test Reports Found!");
				    } else {
				        for (TestReport report : testReports11) {
				            System.out.println("📄 Report ID: " + report.getId());
				            System.out.println("🔬 Test Type: " + report.getTestType());
				            System.out.println("📊 Result: " + report.getResult());
				            System.out.println("--------------------------------------");
				        }
				    }

				    // ➤ Test Billing
				    System.out.println("\n💉 Test Type Charges:");
				    System.out.println("   • CBC        : ₹500");
				    System.out.println("   • Urinalysis : ₹400");
				    System.out.println("   • X-Ray      : ₹800");
				    System.out.println("   • FBS        : ₹600");
				    System.out.println("   • ECG        : ₹700");
				    System.out.println("   • RT-PCR     : ₹1500");
				    System.out.println("   • LFT        : ₹1200");
				    System.out.println("   • KFT        : ₹1300");

				    System.out.print("🔸 Enter Test Cost: ₹");
				    BigDecimal testCost1 = sc.nextBigDecimal();
				    sc.nextLine();

				    totalBill1 = totalBill1.add(testCost1);

				    // ✅ Update and persist the bill
				    billToUpdate.setAmount(totalBill1);
				    session.update(billToUpdate);
				    session.flush();  // Ensure update is pushed

				    System.out.printf("✅ Bill Updated Successfully: ₹%,.2f%n", totalBill1);
				    break;

				case 4:
					System.out.print("Enter Bill ID to Delete: ");
					int deleteId = sc.nextInt();
					sc.nextLine();

					Bill billToDelete = session.get(Bill.class, deleteId);
					if (billToDelete == null) {
						System.out.println("❌ Bill Not Found!");
						break;
					}

					session.delete(billToDelete);
					session.flush(); // ✅ Ensure Hibernate processes the delete
					System.out.println("✅ Bill Deleted Successfully!");
					break;
				case 5:
				    List<Bill> bills = session.createQuery("from Bill", Bill.class).list();

				    if (bills.isEmpty()) {
				        System.out.println("❌ No Bills Available!");
				    } else {
				        System.out.println("\n🔹📋 List of All Bills 🔹");

				        for (Bill b : bills) {
				            Patient patient11 = b.getPatient();

				            System.out.println("\n============== 🧾 BILL ID: " + b.getId() + " ==============");
				            System.out.println("👤 Patient Name  : " + patient11.getName());
				            System.out.println("💰 Total Amount  : ₹" + b.getAmount());

				            // ➤ Show Room Details
				            Room room1 = patient11.getRoom();
				            if (room1 != null) {
				                System.out.println("🏨 Room Assigned : " + room1.getType() + " (Room ID: " + room1.getId() + ")");
				            } else {
				                System.out.println("🏨 Room Assigned : None");
				            }

				            // ➤ Show Test Reports
				            List<TestReport> testReports111 = session.createQuery(
				                    "FROM TestReport tr WHERE tr.patient.id = :pid", TestReport.class)
				                    .setParameter("pid", patient11.getId())
				                    .list();

				            if (testReports111 != null && !testReports111.isEmpty()) {
				                System.out.println("🧪 Test Reports:");
				                for (TestReport report : testReports111) {
				                    System.out.println("   • " + report.getTestType() + " — " + report.getResult());
				                }
				            } else {
				                System.out.println("🧪 Test Reports: None");
				            }
				        }
				    }
				    break;

				case 6:
					System.out.println("Exiting Billing Operations...");
					return;

				default:
					System.out.println("❌ Invalid choice!");
				}

				tx.commit();
			} catch (Exception e) {
				if (tx != null && tx.getStatus().canRollback()) {
					tx.rollback();
				}
				e.printStackTrace(); // ✅ Show full error details
				System.out.println("⚠️ Error: " + e.getMessage());
			} finally {
				session.close();
			}
		}
	}

	// ✅ Record Operations

	public static void recordOperations() {
		
		while (true) {  // 🔄 Keep displaying the menu until "Back" (case 6) is chosen
			Session session = HibernateUtil.getSessionFactory().openSession();  // ✅ Open session once
			Transaction tx = session.beginTransaction();  // ✅ Start transaction

			try {
				System.out.println("\n===== Record Operations =====");
				System.out.println("1. Add Record");
				System.out.println("2. View Record");
				System.out.println("3. Update Record");
				System.out.println("4. Delete Record");
				System.out.println("5. List All Records");
				System.out.println("6. Back");
				System.out.print("Enter your choice: ");

				// ✅ Validate input to ensure it's an integer
				if (!sc.hasNextInt()) {
					System.out.println("❌ Invalid input! Please enter a number between 1-6.");
					sc.next();  // Consume invalid input
					continue;   // Restart loop
				}

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
				    System.out.print("Enter Appointment No: ");
				    int appointmentNo = sc.nextInt();

				    // 🔍 Check if appointment number is already recorded
				    Record existingRecord = session.createQuery(
				        "FROM Record WHERE appointmentNo = :appointmentNo", Record.class)
				        .setParameter("appointmentNo", appointmentNo)
				        .uniqueResult();

				    if (existingRecord != null) {
				        System.out.println("❌ Error: Appointment No " + appointmentNo + " is already recorded!");
				        break;
				    }

				    System.out.print("Enter Receptionist ID: ");
				    int recId = sc.nextInt();

				    Receptionist receptionist = session.get(Receptionist.class, recId);
				    if (receptionist == null) {
				        System.out.println("❌ Receptionist Not Found!");
				        break;
				    }

				    Record newRecord = new Record(appointmentNo, receptionist);
				    session.save(newRecord);
				    System.out.println("✅ Record Added Successfully: " + newRecord);
				    break;

				case 2:
					System.out.print("Enter Record No: ");
					int recordNo = sc.nextInt();
					Record record = session.get(Record.class, recordNo);

					if (record != null) {
						System.out.println("📄 Record Details:");
						System.out.println("Record No       : " + record.getRecordNo());
						System.out.println("Appointment No  : " + record.getAppointmentNo());

						Receptionist rcp = record.getReceptionist();
						if (rcp != null) {
							System.out.println("👤 Handled By Receptionist: " + rcp.getName());
							System.out.println("📅 Appointment Info:");
							System.out.println("Booker Name     : " + rcp.getBookerName());
							System.out.println("Contact Number  : " + rcp.getBookerContact());
							System.out.println("Appointment Date: " + rcp.getAppointmentDate());
							System.out.println("Address         : " + rcp.getBookerAddress());
						}
					} else {
						System.out.println("❌ Record Not Found!");
					}
					break;

				case 3:
					System.out.print("Enter Record No to Update: ");
					int updateRecordNo = sc.nextInt();
					Record recordToUpdate = session.get(Record.class, updateRecordNo);

					if (recordToUpdate == null) {
						System.out.println("❌ Record Not Found!");
						break;
					}

					// Update appointment number
					System.out.print("Enter New Appointment No: ");
					int newAppointmentNo = sc.nextInt();
					recordToUpdate.setAppointmentNo(newAppointmentNo);

					// Update receptionist
					System.out.print("Enter New Receptionist ID to Assign: ");
					int newReceptionistId = sc.nextInt();
					Receptionist newReceptionist = session.get(Receptionist.class, newReceptionistId);

					if (newReceptionist == null) {
						System.out.println("❌ Receptionist with ID " + newReceptionistId + " not found!");
						break;
					}

					recordToUpdate.setReceptionist(newReceptionist);

					session.update(recordToUpdate);
					System.out.println("✅ Record Updated Successfully!");
					System.out.println("New Appointment No: " + newAppointmentNo);
					System.out.println("Assigned to Receptionist: " + newReceptionist.getName());
					break;


				case 4:
					System.out.print("Enter Record No to Delete: ");
					int deleteRecordNo = sc.nextInt();
					Record recordToDelete = session.get(Record.class, deleteRecordNo);

					if (recordToDelete == null) {
						System.out.println("❌ Record Not Found!");
						break;
					}

					session.delete(recordToDelete);
					System.out.println("✅ Record Deleted Successfully!");
					break;

				case 5:
					List<Record> records = session.createQuery("from Record", Record.class).list();
					if (records.isEmpty()) {
						System.out.println("❌ No Records Available!");
					} else {
						System.out.println("\n📋 All Records:");
						for (Record r : records) {
							System.out.println("\nRecord No       : " + r.getRecordNo());
							System.out.println("Appointment No  : " + r.getAppointmentNo());

							Receptionist rcp = r.getReceptionist();
							if (rcp != null) {
								System.out.println("👤 Handled By Receptionist: " + rcp.getName());
								System.out.println("📅 Appointment Info:");
								System.out.println("Booker Name     : " + rcp.getBookerName());
								System.out.println("Contact Number  : " + rcp.getBookerContact());
								System.out.println("Appointment Date: " + rcp.getAppointmentDate());
								System.out.println("Address         : " + rcp.getBookerAddress());
							}
						}
					}
					break;

				case 6:
					System.out.println("Exiting Record Operations...");
					session.close();
					return;

				default:
					System.out.println("❌ Invalid Choice!");
				}

				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				System.out.println("Error: " + e.getMessage());
			} finally {
				session.close();
			}
		}
	}
}