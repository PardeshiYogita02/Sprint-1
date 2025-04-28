package com.Hospital_Management_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Hospital_Management_System.util.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
        //get sessionFactory
        SessionFactory factory=HibernateUtil.getSessionFactory(); //Gets a factory object that creates connections to the database.
        
        //create a session(temporary connection to the database.)
        Session session=factory.openSession(); //Opens a session for interacting with the database.

        //Begin a Transaction(This marks the start of a database operation. For example: insert, update, delete.)
        Transaction tx=session.beginTransaction(); //Begins a transaction for performing database operations.

        
        session.close(); //Closes the session after the transaction is completed.
        
        //close Session factory
        factory.close(); //Closes the session factory to release resources.
    }
}
