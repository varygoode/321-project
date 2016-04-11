/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.*;
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
    
    public void initRooms(ArrayList roomList) throws SQLException
    {
        Statement stmt = null;
        String query = "select ROOMTYPE, ROOMNUMBER, RATE, DESCRIPTION " +
                       "from " + "ROOMS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                RoomTypeEnum roomType = RoomTypeEnum.valueOf( rs.getString("ROOMTYPE"));
                int roomNum = rs.getInt("ROOMNUMBER");
                double rate = rs.getDouble("RATE");
                String desc = rs.getString("DESCRIPTION");
                System.out.println(roomType + "\t" + rate +
                                   "\t" + desc);
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public void initUsers(ArrayList userList) throws SQLException
    {
        Statement stmt = null;
        String query = "select USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME " +
                       "from " + "USERS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int userID = rs.getInt("USERID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                System.out.println(userID + "\t" + username +
                                   "\t" + password + "\t" + firstName +
                                   "\t" + lastName);
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
     
    
    public void initReservations(ArrayList reserveList) throws SQLException 
    {
        Statement stmt = null;
        String query = "select RESERVATIONNUMBER, STARTDATE, ENDDATE, ISPAID, CURRENTPRICE, CHECKEDIN, ROOMNUMBER, TOTALPRICE " +
                       "from " + "RESERVATIONS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int reserveNum = rs.getInt("RESERVATIONNUMBER");
                //Date startDate = rs.getDate("STARTDATE");
                //Date endDate = rs.getDate("ENDDATE");
                boolean isPaid = rs.getBoolean("ISPAID");
                double curPrice = rs.getDouble("CURRENTPRICE");
                boolean checkedIn = rs.getBoolean("CHECKEDIN");
                double totalPrice = rs.getDouble("TOTALPRICE");
                System.out.println(reserveNum + 
                                   //"\t" + startDate +
                                   //"\t" + endDate + 
                                   "\t" + isPaid +
                                   "\t" + curPrice + "\t" + checkedIn + "\t" + totalPrice);
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
