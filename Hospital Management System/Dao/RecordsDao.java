package com.Hospital_Management_System.Dao; // Declares the package for the RecordsDao interface

import java.util.List; // Imports the List interface for handling multiple records

// Interface defining data access operations for Record entities
public interface RecordsDao {

    // Creates and saves a new Record in the database
    Record createRecord(Record record);

    // Retrieves a list of all Record entries from the database
    List<Record> getAllRecords();

    // Retrieves a specific Record from the database by its ID
    Record getRecordById(int recordId);

    // Updates the details of an existing Record identified by its ID
    Record updateRecord(int recordId, Record updatedRecord);

    // Deletes a Record from the database using its ID
    String deleteRecord(int recordId);
}
