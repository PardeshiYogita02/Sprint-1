package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import com.Hospital_Management_System.Dao.RecordsDao; // Import RecordsDao interface
import com.Hospital_Management_System.DaoImpl.RecordsDaoImpl; // Import RecordsDaoImpl (implementation of RecordsDao)
import com.Hospital_Management_System.Entity.Record; // Import Record entity
import com.Hospital_Management_System.service.RecordsService; // Import RecordsService interface

public class RecordsServiceImpl implements RecordsService { // Class implementing RecordsService

    RecordsDao recordDao = new RecordsDaoImpl(); // Creates an object of RecordsDaoImpl (which interacts with the DB).
    
    @Override // Indicates that this method overrides a method from the RecordsService interface
    public void createRecord(Record record) { // Method to create a new record
        System.out.println("Record Created: " + record); // Prints the created record details
    }

    @Override // Method to fetch a record by its ID
    public java.lang.Record getRecordById(int recordId) { 
        return recordDao.getRecordById(recordId); // Delegate to RecordsDaoImpl for fetching by ID
    }

    @Override // Method to create a record (not fully implemented here)
    public void createRecord(java.lang.Record record) {
        // TODO Auto-generated method stub
    }
}
