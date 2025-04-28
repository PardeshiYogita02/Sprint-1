package com.Hospital_Management_System.service; // Package declaration for service

import com.Hospital_Management_System.Entity.Nurse; // Importing Nurse entity

public interface NurseService { // NurseService interface

    Nurse createNurse(Nurse nurse); // Method to create and save a new nurse

    Nurse getNurseById(int nurseId); // Method to fetch a nurse by their ID
}
