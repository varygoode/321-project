/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;
import java.util.Date;

/**
 *
 * @author timothy
 */
/**
 * 
 * @params
 * startDate & endDate make up the date range for the reservation
 * room is a reference to the Room that the reservation is for
 * totalPrice is the calculated value from the # of days and the room rate
 * isPaid is the the flag for payment
 * reserver is the User making the reservation
 * reserveID is a unique 7-digit number that makes the reservations easily searchable
 */

public class Reservation {
    
    private Date startDate;
    private Date endDate;
    private Room room;
    private double totalPrice;
    private boolean isPaid;
    private User reserver;
    private int reserveID;
    private double currentPrice;
    private boolean checkedIn;
    private int roomNumber;

    
    public Reservation()
    {
        startDate = null;
        endDate = null;
        room = null;
        totalPrice = 0.00;
        isPaid = false;
        reserver = null;
        reserveID = 999999;
        currentPrice = 0.00;
        checkedIn = false;
    }
    
    public Reservation(Date sDate, Date eDate, Room room, boolean payment, User user, int reserve)
    {
        startDate = sDate;
        endDate = eDate;
        this.room = room;
        totalPrice = Math.abs(sDate.getDay()-eDate.getDay())*room.getRate();
        isPaid = payment;
        reserver = user;
        reserveID = reserve;
        currentPrice = totalPrice;
        checkedIn = false;
    }
 
    //===============================
    //  Mutators
    //===============================
    public void setStartDate(Date start)
    {
        startDate = start;
    }
    
    public void setEndDate(Date end)
    {
        endDate = end;
    }
    
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    
    public void setRoom(Room room)
    {
        this.room = room;
    }
    
    public void setTotalPrice(double price)
    {
        totalPrice = price;
    }
    
    public void setPaid(boolean payment)
    {
        this.isPaid = payment;
    }
    
    public void setReserver(User user)
    {
        this.reserver = user;
    }
    
    public void setCurrentPrice(double currPrice) 
    {
        this.currentPrice = currPrice;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    
    public void setReserveID(int reserveID)
    {
        this.reserveID = reserveID;
    }
    
    //===============================
    //  Accessors
    //===============================
    public Date getStartDate()
    {
        return startDate;
    }
    
    public Date getEndDate()
    {
        return endDate;
    }
    
    public Room getRoom()
    {
        return room;
    }
    
    public double getTotalPrice()
    {
        return totalPrice;
    }
    
    public boolean getIsPaid()
    {
        return isPaid;
    }
    
    public User getReserver()
    {
        return reserver;
    }
    
    public double getCurrentPrice() 
    {
        return currentPrice;
    }
    
    public boolean IsCheckedIn() 
    {
        return checkedIn;
    }   
    
    public int getReserveID()
    {
        return reserveID;
    }
    
    public String toString()
    {
        return "\nGuest Name: " + reserver.getLastName() + ", " + reserver.getFirstName() + "\nStart Date: " + startDate.toString() + "\nEnd Date: " + endDate.toString() + "\nRoom Info:\n" +  room.toString() + "\nTotal Price: " + Double.toString(totalPrice);
    }
}
