/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author timothy
 */
public class Database {
    
    static private Database singletonDB = null;
    static private Connection conn = null;
    
    private Database()
    {
        
    }
    ////////////////////////////////////
    // Creates singleton database
    ////////////////////////////////////
    public static Database getDB()
    {
        if (singletonDB == null) 
        {
            singletonDB = new Database();       
        }    
        return singletonDB;
    }
    
    ////////////////////////////////////
    // Initializes connection with JDBC
    ////////////////////////////////////    
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
    
    ////////////////////////////////////
    // Terminates connection with JDBC
    ////////////////////////////////////
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
    
    ////////////////////////////////////
    // Pulls Room data from DB and initializes Room objects
    ////////////////////////////////////    
    public void initRooms(ArrayList roomList, RoomFactory roomfactory) throws SQLException
    {
        Statement stmt = null;
        String query = "select * from ROOMS";
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
                roomList.add(roomfactory.createRoom(roomType, roomNum, desc, rate));
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    ////////////////////////////////////
    // Pulls User data from DB and initializes User objects
    ////////////////////////////////////    
    public void initUsers(ArrayList userList, UserFactory userfactory) throws SQLException
    {
        Statement stmt = null;
        Class userType;
        String query = "select * from USERS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int userID = rs.getInt("USERID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                if ((userID%2) == 0)
                {
                    userType = hotelmanagement.Employee.class;
                }
                else
                {
                    userType = hotelmanagement.Customer.class;
                }
                
                System.out.println(userID + "\t" + username +
                                   "\t" + password + "\t" + firstName +
                                   "\t" + lastName);
                
                userList.add(userfactory.createUser(userType,username,password,firstName,lastName,userID));
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to initialize users");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
     
    ////////////////////////////////////
    // Pulls Reservation data from DB and initializes Reservation objects
    ////////////////////////////////////    
    public void initReservations(ArrayList<Reservation> reserveList, ArrayList<Room> roomList, ArrayList<User> userList, ReservationFactory resfactory, Ledger ledger) throws SQLException 
    {
        Statement stmt = null;
        String query = "select * from RESERVATIONS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int reserveNum = rs.getInt("RESERVATIONNUMBER");
                java.util.Date startDate = new java.util.Date(rs.getDate("STARTDATE").getTime());
                java.util.Date endDate = new java.util.Date(rs.getDate("ENDDATE").getTime());
                boolean isPaid = rs.getBoolean("ISPAID");
                double curPrice = rs.getDouble("CURRENTPRICE");
                boolean checkedIn = rs.getBoolean("CHECKEDIN");
                int roomNum = rs.getInt("ROOMNUMBER");
                double totalPrice = rs.getDouble("TOTALPRICE");
                int userID = rs.getInt("USERID");
                
                System.out.println(reserveNum + 
                                   "\t" + startDate +
                                   "\t" + endDate + 
                                   "\t" + isPaid +
                                   "\t" + curPrice + "\t" + checkedIn + "\t" + totalPrice);

                ArrayList<Room> roomResults = ledger.search(roomList, new ArrayList<String>(Arrays.asList(Integer.toString(roomNum))));
                ArrayList<User> userResults = ledger.search(userList, new ArrayList<String>(Arrays.asList(Integer.toString(userID))));
                
                reserveList.add(resfactory.createReservation(startDate, endDate, roomResults.get(0), checkedIn, userResults.get(0), reserveNum));
                roomResults.clear();
                userResults.clear();
            }
        } catch (SQLException e ) {
            System.out.println("Failed to initialize reservations");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    ////////////////////////////////////
    // Stores Room data in DB
    ////////////////////////////////////
    public void storeRooms(ArrayList<Room> roomList) throws SQLException
    {
        Statement stmt = null;
        stmt = conn.createStatement();
        try {
            // Use TRUNCATE
            // Execute deletion
            stmt.executeUpdate("TRUNCATE TABLE ROOMS");
            
            for(int i = 0; i < roomList.size(); i++)
            {
                String sql = "INSERT INTO ROOMS(ROOMTYPE, ROOMNUMBER, RATE, DESCRIPTION) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, roomList.get(i).getType().toString());
                ps.setInt(2, roomList.get(i).getNumber());
                ps.setDouble(3, roomList.get(i).getRate());
                ps.setString(4, roomList.get(i).getDescription());
                
                ps.executeUpdate();
            }

        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to store rooms");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    ////////////////////////////////////
    // Stores Users data in DB
    ////////////////////////////////////    
    public void storeUsers(ArrayList<User> userList) throws SQLException
    {
        Statement stmt = null;
        stmt = conn.createStatement();
        // Use TRUNCATE
        //String delSQL = "TRUNCATE USERS";
        // Execute deletion
        stmt.executeUpdate("TRUNCATE TABLE USERS");
        
        try {
            for(int i = 0; i < userList.size(); i++)
            {
                String sql = "insert into USERS(USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userList.get(i).getID());
                ps.setString(2, userList.get(i).getUsername());
                ps.setString(3, userList.get(i).getPassword());
                ps.setString(4, userList.get(i).getFirstName());
                ps.setString(5, userList.get(i).getLastName());
                
                ps.executeUpdate();
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to store users");
        } finally {
            if (stmt != null) 
            { 
                stmt.close(); 
            }
        }
    }
     
    ////////////////////////////////////
    // Stores Reservation data in DB
    ////////////////////////////////////    
    public void storeReservations(ArrayList<Reservation> reserveList, ArrayList<Room> roomList, ArrayList<User> userList) throws SQLException 
    {
        Statement stmt = null;
        stmt = conn.createStatement();
        // Use TRUNCATE
        // Execute deletion
        stmt.executeUpdate("TRUNCATE TABLE RESERVATIONS");
        
        try {

            for(int i = 0; i < reserveList.size(); i++)
            {
                String sql = "insert into RESERVATIONS(RESERVATIONNUMBER, STARTDATE, ENDDATE, ISPAID, CURRENTPRICE, CHECKEDIN, ROOMNUMBER, TOTALPRICE, USERID) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, reserveList.get(i).getReserveID());
                java.sql.Date sqlStartDate = new java.sql.Date(reserveList.get(i).getStartDate().getTime());
                ps.setDate(2, sqlStartDate);
                java.sql.Date sqlEndDate = new java.sql.Date(reserveList.get(i).getEndDate().getTime());
                ps.setDate(3, sqlEndDate);
                ps.setBoolean(4, reserveList.get(i).getIsPaid());
                ps.setDouble(5, reserveList.get(i).getCurrentPrice());
                ps.setBoolean(6, reserveList.get(i).IsCheckedIn());
                ps.setInt(7, reserveList.get(i).getRoom().getNumber());
                ps.setDouble(8, reserveList.get(i).getTotalPrice());
                ps.setInt(9, reserveList.get(i).getReserver().getID());
                
                ps.executeUpdate();
            }

        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to store reservations");
        } finally {
            if (stmt != null) 
            { 
                stmt.close(); 
            }
        }
    }

    ////////////////////////////////////
    // Pulls Archive data from DB and initializes Archive objects
    ////////////////////////////////////    
    public void initArchives(ArrayList<Reservation> archiveList, ArrayList<Room> roomList, ArrayList<User> userList, ReservationFactory resfactory, Ledger ledger) throws SQLException 
    {
        Statement stmt = null;
        String query = "select * from ARCHIVES";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int reserveNum = rs.getInt("RESERVATION_NUMBER");
                java.util.Date startDate = new java.util.Date(rs.getDate("START_DATE").getTime());
                java.util.Date endDate = new java.util.Date(rs.getDate("END_DATE").getTime());
                boolean checkedIn = rs.getBoolean("CHECKEDIN");
                int roomNum = rs.getInt("ROOM_NUMBER");
                double totalPrice = rs.getDouble("TOTAL_PRICE");
                int userID = rs.getInt("USERID");

                ArrayList<Room> roomResults = ledger.search(roomList, new ArrayList<String>(Arrays.asList(Integer.toString(roomNum))));
                ArrayList<User> userResults = ledger.search(userList, new ArrayList<String>(Arrays.asList(Integer.toString(userID))));
                
                archiveList.add(resfactory.createReservation(startDate, endDate, roomResults.get(0), checkedIn, userResults.get(0), reserveNum));
                roomResults.clear();
                userResults.clear();
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to initialize archives");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    ////////////////////////////////////
    // Stores Archive data in DB
    ////////////////////////////////////    
    public void storeArchives(ArrayList<Reservation> archiveList, ArrayList<Room> roomList, ArrayList<User> userList) throws SQLException 
    {
        Statement stmt = null;
        stmt = conn.createStatement();
        // Use TRUNCATE
        // Execute deletion
        stmt.executeUpdate("TRUNCATE TABLE ARCHIVES");
        
        try {

            for(int i = 0; i < archiveList.size(); i++)
            {
                String sql = "insert into ARCHIVES(RESERVATION_NUMBER, START_DATE, END_DATE, ROOM_NUMBER, TOTAL_PRICE, USERID, CHECKEDIN) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, archiveList.get(i).getReserveID());
                java.sql.Date sqlStartDate = new java.sql.Date(archiveList.get(i).getStartDate().getTime());
                ps.setDate(2, sqlStartDate);
                java.sql.Date sqlEndDate = new java.sql.Date(archiveList.get(i).getEndDate().getTime());
                ps.setDate(3, sqlEndDate);
                ps.setInt(4, archiveList.get(i).getRoom().getNumber());
                ps.setDouble(5, archiveList.get(i).getTotalPrice());
                ps.setInt(6, archiveList.get(i).getReserver().getID());
                ps.setBoolean(7, archiveList.get(i).IsCheckedIn());
                
                ps.executeUpdate();
            }

        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to store archives");
        } finally {
            if (stmt != null) 
            { 
                stmt.close(); 
            }
        }
    }    
}
