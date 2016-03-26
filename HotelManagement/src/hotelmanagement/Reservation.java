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
    
    public Reservation()
    {
        startDate = null;
        endDate = null;
        room = null;
        totalPrice = 0.00;
        isPaid = false;
        reserver = null;
        reserveID = 999999;
    }
    
    public Reservation(Date sDate, Date eDate, Room room, boolean payment, User user, int reserve)
    {
        startDate = sDate;
        endDate = eDate;
        this.room = room;
        totalPrice = (eDate.getDay()-sDate.getDay())*room.getRate();
        isPaid = payment;
        reserver = user;
        reserveID = reserve;
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
        isPaid = payment;
    }
    
    public void setReserver(User user)
    {
        reserver = user;
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
    
    public boolean getPaid()
    {
        return isPaid;
    }
    
    public User getReserver()
    {
        return reserver;
    }
    
}
