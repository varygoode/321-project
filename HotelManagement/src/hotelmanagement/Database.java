/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.io.*;
import java.sql.*;

/**
 *
 * @author polyp_000
 */
public class Database {
    
    static private Database singletonDB = null;
    static private Connection conn = null;
    
    private Database()
    {
        
    }
    
    public static Database getDB()
    {
        if (singletonDB == null) 
        {
            singletonDB = new Database();       
        }    
        return singletonDB;
    }
    
    public void startConnection()
    {
        String connectURL = "jdbc:derby://localhost:1527/hotel";    
        try 
        {
            conn = DriverManager.getConnection(connectURL, "manager","password");
            System.out.println("Connection Established");
        }
        catch(SQLException ex)
        {
            System.out.println("Connection failed");
        }
    }
    
    public void closeConnection()
    {
        String connectURL = "jdbc:derby://localhost:1527/hotel";    
        try 
        {
            System.out.println("Connection Closed");
            conn.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Attempt failed");
        }
    }
}
