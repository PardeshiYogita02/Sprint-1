package com.Hospital_Management_System.ServiceImpl; // Package declaration for service implementation

import java.util.List; // Import List for handling collections

import com.Hospital_Management_System.Dao.BillDao; // Import BillDao interface
import com.Hospital_Management_System.DaoImpl.BillDaoImpl; // Import BillDaoImpl (implementation of BillDao)
import com.Hospital_Management_System.Entity.Bill; // Import Bill entity
import com.Hospital_Management_System.service.BillService; // Import BillService interface

public class BillServiceImpl implements BillService { // Class implementing BillService

    BillDao billDao = new BillDaoImpl(); // Creates an object of BillDaoImpl to interact with the database
    
    @Override
    public Bill createBill(Bill bill) { // Method to create a new bill and save it to the database
        return billDao.createBill(bill); // Passes the Bill to DAO to be saved in the database
    }

    @Override
    public Bill getBillById(int billId) { // Method to get a bill by its ID
        return billDao.getBillById(billId); // Calls the DAO method to fetch the bill by its ID
    }

    @Override
    public void addBill(int patientId, double amount) { // Method to add a bill (not fully implemented)
        // TODO Auto-generated method stub
    }

    @Override
    public List<Bill> getAllBills() { // Method to get all bills (not fully implemented)
        // TODO Auto-generated method stub
        return null;
    }
}
