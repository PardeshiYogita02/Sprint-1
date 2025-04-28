package com.Hospital_Management_System.service; // Package declaration for service

public interface RecordsService { // RecordsService interface

    void createRecord(Record record); // Method to create a new Record

    Record getRecordById(int recordId); // Method to retrieve a Record by its ID

    void createRecord(com.Hospital_Management_System.Entity.Record record); // Another method to create a new Record
}
