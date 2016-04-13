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
    
    public void initRooms(ArrayList roomList, RoomFactory roomfactory) throws SQLException
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
                roomList.add(roomfactory.createRoom(roomType, roomNum, desc, rate));
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public void initUsers(ArrayList userList, UserFactory userfactory) throws SQLException
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
                userList.add(userfactory.createUser(hotelmanagement.Customer.class,username,password,firstName,lastName,userID));
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
     
    
    public void initReservations(ArrayList<Reservation> reserveList, ArrayList<Room> roomList, ArrayList<User> userList, ReservationFactory resfactory) throws SQLException 
    {
        Statement stmt = null;
        String query = "select RESERVATIONNUMBER, STARTDATE, ENDDATE, ISPAID, CURRENTPRICE, CHECKEDIN, ROOMNUMBER, TOTALPRICE " +
                       "from " + "RESERVATIONS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int reserveNum = rs.getInt("RESERVATIONNUMBER");
                java.util.Date startDate;
                startDate = rs.getDate("STARTDATE");
                java.util.Date endDate;
                endDate = rs.getDate("ENDDATE");
                boolean isPaid = rs.getBoolean("ISPAID");
                double curPrice = rs.getDouble("CURRENTPRICE");
                boolean checkedIn = rs.getBoolean("CHECKEDIN");
                double totalPrice = rs.getDouble("TOTALPRICE");
                System.out.println(reserveNum + 
                                   "\t" + startDate +
                                   "\t" + endDate + 
                                   "\t" + isPaid +
                                   "\t" + curPrice + "\t" + checkedIn + "\t" + totalPrice);
                reserveList.add(resfactory.createReservation(startDate, endDate, roomList.get(0), checkedIn, userList.get(0), reserveNum));                
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    
    
    public void storeRooms(ArrayList<Room> roomList) throws SQLException
    {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            // Use TRUNCATE
            String delSQL = "TRUNCATE ROOMS";
            // Execute deletion
            stmt.executeUpdate(delSQL);
            // Use DELETE
            delSQL = "DELETE FROM ROOMS";
            // Execute deletion
            stmt.executeUpdate(delSQL);
            
            for(int i = 0; i < roomList.size(); i++)
            {
                String sql = "INSERT INTO ROOMS(ROOMTYPE, ROOMNUMBER, RATE, DESCRIPTION) " +
                   "VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, roomList.get(i).getType().toString());
                ps.setInt(2, roomList.get(i).getNumber());
                ps.setDouble(3, roomList.get(i).getRate());
                ps.setString(4, roomList.get(i).getDescription());
                
                stmt.executeUpdate(sql);
            }
            
            //
            // INSERT LOOP FOR PULLING VALUES OUT OF ARRAYLISTS
            //
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public void storeUsers(ArrayList<User> userList) throws SQLException
    {
        Statement stmt = null;
        // Use TRUNCATE
        String delSQL = "TRUNCATE USERS";
        // Execute deletion
        stmt.executeUpdate(delSQL);
        // Use DELETE
        delSQL = "DELETE FROM USERS";
        // Execute deletion
        stmt.executeUpdate(delSQL);
        
        String query = "INSERT INTO USERS( USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME )";
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
            for(int i = 0; i < userList.size(); i++)
            {
                String sql = "INSERT INTO USERS(USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME) " +
                   "VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userList.get(i).getID());
                ps.setString(2, userList.get(i).getUsername());
                ps.setString(3, userList.get(i).getPassword());
                ps.setString(4, userList.get(i).getFirstName());
                ps.setString(5, userList.get(i).getLastName());
                
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
     
    
    public void storeReservations(ArrayList<Reservation> reserveList, ArrayList<Room> roomList, ArrayList<User> userList, ReservationFactory resfactory) throws SQLException 
    {
        Statement stmt = null;
        // Use TRUNCATE
        String delSQL = "TRUNCATE RESERVATIONS";
        // Execute deletion
        stmt.executeUpdate(delSQL);
        // Use DELETE
        delSQL = "DELETE FROM RESERVATIONS";
        // Execute deletion
        stmt.executeUpdate(delSQL);
        
        try {
            stmt = conn.createStatement();

            for(int i = 0; i < reserveList.size(); i++)
            {
                String sql = "INSERT INTO RESERVATIONS(RESERVATIONNUMBER, STARTDATE, ENDDATE, ISPAID, CURRENTPRICE, CHECKEDIN, ROOMNUMBER, TOTALPRICE) " +
                   "VALUES (?,?,?,?,?)";
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
                
                stmt.executeUpdate(sql);
            }
            //
            // INSERT LOOP FOR PULLING VALUES OUT OF ARRAYLISTS
            //
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            System.out.println("Failed to execute statement");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    
    
    
    private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)";
    private static final String SQL_DESERIALIZE_OBJECT = "SELECT serialized_object FROM serialized_java_objects WHERE serialized_id = ?";

    public static long serializeJavaObjectToDB(Connection connection,
                    Object objectToSerialize) throws SQLException {

            PreparedStatement pstmt = connection
                            .prepareStatement(SQL_SERIALIZE_OBJECT);

            // just setting the class name
            pstmt.setString(1, objectToSerialize.getClass().getName());
            pstmt.setObject(2, objectToSerialize);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            int serialized_id = -1;
            if (rs.next()) {
                    serialized_id = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            System.out.println("Java object serialized to database. Object: "
                            + objectToSerialize);
            return serialized_id;
    }

    /**
     * To de-serialize a java object from database
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deSerializeJavaObjectFromDB(Connection connection,
                    long serialized_id) throws SQLException, IOException,
                    ClassNotFoundException {
            PreparedStatement pstmt = connection
                            .prepareStatement(SQL_DESERIALIZE_OBJECT);
            pstmt.setLong(1, serialized_id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            // Object object = rs.getObject(1);

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));

            Object deSerializedObject = objectIn.readObject();

            rs.close();
            pstmt.close();

            System.out.println("Java object de-serialized from database. Object: "
                            + deSerializedObject + " Classname: "
                            + deSerializedObject.getClass().getName());
            return deSerializedObject;
    }

    /**
     * Serialization and de-serialization of java object from mysql
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static void test(String args[]) throws ClassNotFoundException,
                    SQLException, IOException {
            Connection connection = null;

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/javaserialization";
            String username = "root";
            String password = "admin";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

            // a sample java object to serialize
            Vector obj = new Vector();
            obj.add("java");
            obj.add("papers");

            // serializing java object to mysql database
            long serialized_id = serializeJavaObjectToDB(connection, obj);

            // de-serializing java object from mysql database
            Vector objFromDatabase = (Vector) deSerializeJavaObjectFromDB(
                            connection, serialized_id);

            connection.close();
    }
    
}
